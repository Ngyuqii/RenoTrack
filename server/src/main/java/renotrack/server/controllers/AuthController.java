package renotrack.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.JsonObject;
import renotrack.server.models.User;
import renotrack.server.services.MailService;


@RestController
@CrossOrigin(origins="*")
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    private MailService mailSvc;

    @PostMapping(path="/register", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerUser(@RequestBody String user) {
        
        System.out.printf(">>>User details: %s /n", user);

        JsonObject userJO = User.toJson(user);

        try {
            mailSvc.sendEmail(userJO.getString("email"), userJO.getString("name"));
            return ResponseEntity.ok("Email sent");
        }
        catch (Exception e){
            return ResponseEntity.ok(e.toString());
        }
    }
   
}