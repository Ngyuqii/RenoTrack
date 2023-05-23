package renotrack.server.models;

import java.io.Reader;
import java.io.StringReader;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class User {

    private String userId;
    private String userName;
    private String userEmail;
    private String userPassword;
    
    //Constructor
    public User() {
    }

    //Getters
    public String getUserId() {
        return userId;
    }
    public String getUserName() {
        return userName;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public String getUserPassword() {
        return userPassword;
    }

    //Setters
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    //Returns JsonObject from Json String
    public static JsonObject toJson(String str) {
        Reader reader = new StringReader(str);
        JsonReader jsonReader = Json.createReader(reader);
        return jsonReader.readObject();
    }

    //Create User Object from JsonObject
    public static User createUser(JsonObject json) {
        User u = new User();
        u.setUserId(getValue(json, "userId"));
        u.setUserName(getValue(json, "userName"));
        u.setUserEmail(json.getString("userEmail"));
        u.setUserPassword(json.getString("userPassword"));
        return u;
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

    //Create User Object from RowSet Object
    public static User createUserFromRowSet(SqlRowSet rs) {
        User u = new User();
        u.setUserId(rs.getString("user_id"));
        u.setUserName(rs.getString("user_name"));
        u.setUserEmail(rs.getString("user_email"));
        u.setUserPassword(rs.getString("user_password"));
        return u;
    }   

}