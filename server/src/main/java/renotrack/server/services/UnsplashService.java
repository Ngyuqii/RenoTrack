package renotrack.server.services;

import java.io.StringReader;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import renotrack.server.models.Unsplash;

@Service
public class UnsplashService {

    //URL = "https://api.unsplash.com/search/photos?query=living%20room&client_id={API_KEY}"
	public static final String UNSPLASH_API = "https://api.unsplash.com/search/photos";
    
	@Value("${unsplashapi.key}")
	private String unsplashKey;

    //Method to call on external API and return a list of Unsplash objects
	public List<Unsplash> searchUnsplash(String query) {

		String apiUrl = UriComponentsBuilder.fromUriString(UNSPLASH_API)
                .queryParam("query", query)
                .queryParam("client_id", unsplashKey)
                .toUriString();

        RestTemplate template = new RestTemplate();
        
        RequestEntity<Void> request = RequestEntity.get(apiUrl)
                .accept(MediaType.APPLICATION_JSON)
                .build();

		ResponseEntity<String> response = null;
			try {
				response = template.exchange(request, String.class);
			}
				catch (RestClientException ex) {
					ex.printStackTrace();
					return Collections.emptyList();
				}

		String payload = response.getBody();

		JsonReader reader = Json.createReader(new StringReader(payload));
        JsonObject unsplashResponse = reader.readObject();
        JsonArray jsonArr = unsplashResponse.getJsonArray("results");

		//Convert jsonArray to list of Unsplash objects
        return jsonArr.stream()
            .map(v -> (JsonObject)v)
            .map(v -> Unsplash.createUnsplash(v))
            .toList();
	}
    
}