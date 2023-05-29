package renotrack.server.models;

import java.io.Reader;
import java.io.StringReader;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Event {

    private Integer eventId;
    private String subject;
    private String startTime;
    private String endTime;
    private String description;
    private String location;
    
    //Constructor
    public Event() {
    }

    //Getters
    public Integer getEventId() {
        return eventId;
    }
    public String getSubject() {
        return subject;
    }
    public String getStartTime() {
        return startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public String getDescription() {
        return description;
    }
    public String getLocation() {
        return location;
    }

    //Setters
    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    //Returns JsonObject from Json String
    public static JsonObject toJson(String str) {
        Reader reader = new StringReader(str);
        JsonReader jsonReader = Json.createReader(reader);
        return jsonReader.readObject();
    }

    //Create Event Object from JsonObject
    public static Event createEvent(JsonObject json) {
        Event e = new Event();
        e.setEventId(json.getInt("Id"));
        e.setSubject(json.getString("Subject"));
        e.setStartTime(json.getString("StartTime"));
        e.setEndTime(json.getString("EndTime"));
        e.setDescription(getValue(json, "Description"));
        e.setLocation(getValue(json, "Location"));
        return e;
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

    //Create Json Object from Event Object
    public JsonObject toJsonObj() {
        return Json.createObjectBuilder()
                .add("id", getEventId())
                .add("Subject", getSubject())
                .add("StartTime", getStartTime())
                .add("EndTime", getEndTime())
                .add("Description",getDescription())
                .add("Location",getLocation())
                .build();
    }

    //Create Event Object from RowSet Object
    public static Event createEventFromRowSet(SqlRowSet rs) {
        Event e = new Event();
        e.setEventId(rs.getInt("event_id"));
        e.setSubject(rs.getString("subject"));
        e.setStartTime(rs.getString("start_time"));
        e.setEndTime(rs.getString("end_time"));
        e.setDescription(rs.getString("description"));
        e.setLocation(rs.getString("location"));
        return e;
    }
   
}