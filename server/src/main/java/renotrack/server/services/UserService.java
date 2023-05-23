package renotrack.server.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import renotrack.server.models.User;
import renotrack.server.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private MailService mailSvc;

    //Method to register new user if not already existing
    //1. Set userId and insert new user record in database
    //2. Send email to new user
    public String registerUser(User user) {
        User registeredUser = userRepo.findUserByEmail(user.getUserEmail());
        if(registeredUser != null) {
            return "Email already exists";
        }
        else {
            String newUserId = UUID.randomUUID().toString().substring(0, 8);
            user.setUserId(newUserId);
            userRepo.registerUser(user);
            mailSvc.sendEmail(user.getUserEmail(), user.getUserName());
            return "New account has been registered";
        }
    }

    //Method to login user
    //1. Check if user exists in database
    //2. Retrieve user's encrypted password from database and match the user input password
    public String loginUser(User user) {
        User registeredUser = userRepo.findUserByEmail(user.getUserEmail());
        if(registeredUser == null) {
            return "Email not found. Please register your account.";
        }
        else {
            String encryptedPassword = userRepo.findPassword(user.getUserEmail());

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();            
            if(passwordEncoder.matches(user.getUserPassword(), encryptedPassword)) {
                return registeredUser.getUserId();
            }
            else {
                return "Authentication failed. Please try again.";
            }
        }
    }
}