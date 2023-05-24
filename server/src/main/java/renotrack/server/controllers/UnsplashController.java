package renotrack.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import renotrack.server.models.Unsplash;
import renotrack.server.services.UnsplashService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UnsplashController {

    @Autowired
    private UnsplashService unsplashSvc;

    @GetMapping(path="/unsplash")
    @ResponseBody
    public ResponseEntity<String> callAPI(@RequestParam String query) {

        //Check variable passed from client side
        System.out.printf(">>>Search Keyword: %s", query);
        
        //Call on external API and return a list of Unsplash objects
        List<Unsplash> imagesRetrieved = unsplashSvc.searchUnsplash(query);

		//Convert unsplash objects to json objects and add to arrbuilder
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        imagesRetrieved.stream()
                .map(v -> v.toJsonObj())
                .forEach(v -> {
                    arrBuilder.add(v);
                });

		//Return jsonArray > stringify
        return ResponseEntity.ok(arrBuilder.build().toString());
    }
    
}