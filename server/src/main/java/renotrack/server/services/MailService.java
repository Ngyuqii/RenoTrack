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

    private final String emailSubject = "Welcome to Renotrack";
    private final String emailContent = """
    <h1>Hi user!</h1>
    <p>Welcome to Renotrack. We are glad to be a part of your renovation journey.
    <br>Get started with the following features.</p><br>
    <img src="https://zameenblog.s3.amazonaws.com/blog/wp-content/uploads/2015/01/How-to-renovate-a-bathroom-Cover-13-03.jpg" width=450px>
    <div>
        <p><b>Photo Journal</b>
        <br>Upload photos of your renovation progress here. Your photo journal serves as a visual documentation of your renovation progress and experiences, capturing moments and memories through photographs.</p>
        <p><b>Scheduler</b>
        <br>Plan, organize, and manage your renovation timeline. A scheduler helps you organize and schedule tasks, collaborate with external parties, and is a reminder of key milestones, ensuring that you stay on track.</br>
        <p><b>Expense Tracker</b>
        <br>Keep track of your renovation expenses. Monitor how much money you are spending and where it is being allocated, so that you may make necessary adjustments to stay within your budget.</br>
    </div><br>
    <h2>Happy Building!</h2>
    <p>Regards
    <br>Renotrack Team</p>
    """;

    public void sendEmail(String userEmail, String userName) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");
            
            helper.setTo(userEmail);
            helper.setSubject(emailSubject);
            // replace 'user' placeholder with userName
            String personalizedEmail = emailContent.replace("user", userName);
            message.setContent(personalizedEmail, "text/html");

            emailSender.send(message);
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
    }
   
}