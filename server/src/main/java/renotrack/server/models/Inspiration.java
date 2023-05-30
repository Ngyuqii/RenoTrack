package renotrack.server.models;

import java.io.Reader;
import java.io.StringReader;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Inspiration {
    
    private Integer inspoId;
    private String imageUrl;
    
    //Constructor
    public Inspiration() {
    }

    //Getters
    public Integer getInspoId() {
        return inspoId;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    //Setters
    public void setInspoId(Integer inspoId) {
        this.inspoId = inspoId;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    //Returns JsonObject from Json String
    public static JsonObject toJson(String str) {
        Reader reader = new StringReader(str);
        JsonReader jsonReader = Json.createReader(reader);
        return jsonReader.readObject();
    }

    //Create Inspiration Object from JsonObject
    public static Inspiration createInspiration(JsonObject json) {
        Inspiration i = new Inspiration();
        i.setInspoId(getValue(json, "inspoId"));
        i.setImageUrl(json.getString("imageUrl"));
        return i;
    }

    //Method to get value of key in json object if not return 0
    //Else return NA
    private static int getValue(JsonObject json, String k) {
        if (json.containsKey(k) && !json.isNull(k)) {
            return json.getInt(k);
        }
        else {
            return 0;
        }      
    }

    //Create Json Object from Inspiration Object
    public JsonObject toJsonObj() {
        return Json.createObjectBuilder()
                .add("inspoId", getInspoId())
                .add("imageUrl", getImageUrl())
                .build();
    }

    //Create Inspiration Object from RowSet Object
    public static Inspiration createInspirationFromRowSet(SqlRowSet rs) {
        Inspiration i = new Inspiration();
        i.setInspoId(rs.getInt("inspo_id"));
        i.setImageUrl(rs.getString("imageUrl"));
        return i;
    }
    
}
