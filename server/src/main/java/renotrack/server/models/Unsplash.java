package renotrack.server.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Unsplash {
    
    private String url; //urls.raw
    private String description; //alt_description

    //Constructor
    public Unsplash() {
    }

    //Getters
    public String getUrl() {
        return url;
    }
    public String getDescription() {
        return description;
    }

    //Setters
    public void setUrl(String url) {
        this.url = url;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    //Method to convert Json Object to Unsplash Object
    public static Unsplash createUnsplash(JsonObject json) {
        Unsplash unsplash = new Unsplash();
        unsplash.setUrl(getNestedValue(json, "urls", "raw"));
        unsplash.setDescription(getValue(json, "alt_description"));
        return unsplash;
    }

    //Method to get value of key in json object if not null
    //Else return NA
    private static String getValue(JsonObject json, String k) {
        if (json.containsKey(k) && !json.isNull(k)) {
            return json.getString(k);
        }
        else {
            return "NA";
        }      
    }

    //Method to get nested value in Json Object and return NA if error
	private static String getNestedValue(JsonObject json, String outer, String inner) {
		try {
			return json.getJsonObject(outer).getString(inner);
		}
		catch (Exception e) {
			return "NA";
		}
	}

    //Method to build Json Object
    public JsonObject toJsonObj() {
        return Json.createObjectBuilder()
            .add("imageUrl", getUrl())
            .add("imageDes", getDescription())
            .build();
    }

}