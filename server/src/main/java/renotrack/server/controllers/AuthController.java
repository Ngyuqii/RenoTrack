package renotrack.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import renotrack.server.models.User;
import renotrack.server.services.UserService;


@RestController
@CrossOrigin(origins="*")
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    private UserService userSvc;

    //Email verification
    @PostMapping(path="/verify")
    public ResponseEntity<String> emailVerification(@RequestBody String email) {
        
        System.out.printf(">>>User email: %s /n", email);

        try {
            String code = userSvc.verifyEmail(email);
            JsonObject response = Json.createObjectBuilder()
            .add("Msg", code)
            .build();
            return ResponseEntity.ok(response.toString());
        }
        catch (Exception e){
            return ResponseEntity.ok(e.toString());
        }
    }

    //User registration
    @PostMapping(path="/register", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> userRegistration(@RequestBody String user) {
        
        System.out.printf(">>>User details: %s /n", user);

        JsonObject userJO = User.toJson(user);
        User userObj = User.createUser(userJO);

        try {
            String reg = userSvc.registerUser(userObj);
            JsonObject response = Json.createObjectBuilder()
            .add("Msg", reg)
            .build();
            return ResponseEntity.ok(response.toString());
        }
        catch (Exception e){
            return ResponseEntity.ok(e.toString());
        }
    }

    //User login
    @PostMapping(path="/login", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> userLogin(@RequestBody String user) {
        
        System.out.printf(">>>User details: %s /n", user);

        JsonObject userJO = User.toJson(user);
        User userObj = User.createUser(userJO);

        try {
            String login = userSvc.loginUser(userObj);
            JsonObject response = Json.createObjectBuilder()
            .add("Msg", login)
            .build();
            return ResponseEntity.ok(response.toString());
        }
        catch (Exception e){
            return ResponseEntity.ok(e.toString());
        }
    }
  
}