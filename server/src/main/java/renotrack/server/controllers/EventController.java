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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import renotrack.server.models.Event;
import renotrack.server.repositories.EventRepository;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(path="/api/events", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventController {

    @Autowired
    private EventRepository eventRepo;

    //Retrieve all events matching the userId
    @GetMapping("/{userId}")
    public ResponseEntity<String> getAllEvents(@PathVariable String userId) {

        List<Event> events = eventRepo.getAllEvents(userId);

        //Convert list of event objects to json objects and add to arrbuilder
        //Return as json string to client side
        if(events.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
            for (Event e : events) {
            arrBuilder.add(e.toJsonObj());
            }
            return ResponseEntity.ok(Json.createObjectBuilder()
                                    .add("Events", arrBuilder)
                                    .build().toString());
        }   
    }

    //Insert new event into database
    @PostMapping("/{userId}")
    public ResponseEntity<String> createEvent(@PathVariable String userId, @RequestBody String event) {

        JsonObject userJO = Event.toJson(event);
        Event eventObj = Event.createEvent(userJO);

        int result = eventRepo.createEvent(userId, eventObj);
        if(result == 0){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Update event in database matching userId and eventId
    @PutMapping("/{userId}/{eventId}")
    public ResponseEntity<String> updateEvent(@PathVariable String userId, @PathVariable int eventId, @RequestBody String event) {
        
        JsonObject userJO = Event.toJson(event);
        Event eventObj = Event.createEvent(userJO);
        
        int result = eventRepo.updateEvent(userId, eventId, eventObj);
        if(result == 0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Delete event in database matching userId and eventId
    @DeleteMapping("/{userId}/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable String userId, @PathVariable int eventId) {
        int result = eventRepo.deleteEvent(userId, eventId);
        if(result == 0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}