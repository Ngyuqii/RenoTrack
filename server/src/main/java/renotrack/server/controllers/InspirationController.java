package renotrack.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import renotrack.server.models.Inspiration;
import renotrack.server.repositories.InspirationRepository;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(path="/api/inspirations", produces = MediaType.APPLICATION_JSON_VALUE)
public class InspirationController {

    @Autowired
    private InspirationRepository inspirationRepo;

    //Retrieve list of inspo for a userId
    @GetMapping(path="/{userId}")
    public ResponseEntity<String> getAllInspo(@PathVariable String userId) {
        List<Inspiration> inspo = inspirationRepo.findAllInspo(userId);
        if(inspo.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
            for (Inspiration i : inspo) {
            arrBuilder.add(i.toJsonObj());
            }
            return ResponseEntity.ok(Json.createObjectBuilder()
                                    .add("Inspo", arrBuilder)
                                    .build().toString());
        }   
    }

    //Create a new inspo for a userId
    @PostMapping(path="/like/{userId}")
    public ResponseEntity<String> createInspo(@PathVariable String userId, @RequestBody String inspo) {
        JsonObject inspoJO = Inspiration.toJson(inspo);
        Inspiration inspoObj = Inspiration.createInspiration(inspoJO);

        int result = inspirationRepo.insertInspo(userId, inspoObj.getImageUrl());
        if(result == 0){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Delete an existing inspo of inspoId
    @DeleteMapping(path="/unlike/{inspoId}")
    public ResponseEntity<String> deleteInspo(@PathVariable int inspoId) {
        int result = inspirationRepo.deleteInspo(inspoId);
        if(result == 0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
