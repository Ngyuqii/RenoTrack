package renotrack.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {

    @Autowired
    private JavaMailSender emailSender;

    //Set up email template 1 - Email Verification
    private final String emailSubject1 = "Email Verification - Renotrack";
    private final String emailContent1 = """
    <h1>Hi!</h1>
    <p>Welcome to Renotrack. You are 1 step away from completing your registration.
    <br>Please use the OTP below to verify your email address.</p><br>
    <h2>code</h2>
    <p>Your OTP is valid for 3min</p><br>
    <p>Regards
    <br>Renotrack Team</p>
    """;

    //Set up email template 2 - Account Registered
    private final String emailSubject2 = "Welcome to Renotrack";
    private final String emailContent2 = """
    <h1>Hi user!</h1>
    <p>Welcome to Renotrack. We are glad to be a part of your renovation journey.
    <br>Get started with the following features.</p><br>
    <img src="https://zameenblog.s3.amazonaws.com/blog/wp-content/uploads/2015/01/How-to-renovate-a-bathroom-Cover-13-03.jpg" width=450px>
    <div>
        <p><b>Inspiration</b>
        <br>Save unsplash images that inspire you here! Your inspiration album serves as a visual documentation of your preferences, capturing ideas and concepts for your renovation project.</p>
        <p><b>Scheduler</b>
        <br>Plan, organize, and manage your renovation timeline. A scheduler helps you organize and schedule tasks, collaborate with external parties, and is a reminder of key milestones, ensuring that you stay on track.</br>
        <p><b>Expense Tracker</b>
        <br>Keep track of your renovation expenses. Monitor how much money you are spending and where it is being allocated, so that you may make necessary adjustments to stay within your budget.</br>
    </div><br>
    <h2>Happy Building!</h2>
    <p>Regards
    <br>Renotrack Team</p>
    """;

    //Method to send verification code email to user using emailSender
    public void sendVerEmail(String userEmail, String verificationCode) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");
            
            helper.setTo(userEmail);
            helper.setSubject(emailSubject1);
            // replace 'code' placeholder with verificationCode
            String personalizedEmail1 = emailContent1.replace("code", verificationCode);
            message.setContent(personalizedEmail1, "text/html");

            emailSender.send(message);
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    //Method to send email to registered user using emailSender
    public void sendRegEmail(String userEmail, String userName) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");
            
            helper.setTo(userEmail);
            helper.setSubject(emailSubject2);
            // replace 'user' placeholder with userName
            String personalizedEmail2 = emailContent2.replace("user", userName);
            message.setContent(personalizedEmail2, "text/html");

            emailSender.send(message);
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
    }
   
}