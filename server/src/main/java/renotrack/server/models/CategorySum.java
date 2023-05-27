package renotrack.server.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class CategorySum {

    private String category;
    private float sum;
    
    //Constructor
    public CategorySum() {
    }

    //Getters
    public String getCategory() {
        return category;
    }
    public float getSum() {
        return sum;
    }

    //Setters
    public void setCategory(String category) {
        this.category = category;
    }
    public void setSum(float sum) {
        this.sum = sum;
    }

    //Create Json Object from CategorySum Object
    public JsonObject toJsonObj() {
        return Json.createObjectBuilder()
                .add("category", getCategory())
                .add("sum", getSum())
                .build();
    }

     //Create CategorySum Object from RowSet Object
     public static CategorySum createCatSumFromRowSet(SqlRowSet rs) {
        CategorySum c = new CategorySum();
        c.setCategory(rs.getString("category"));
        c.setSum(rs.getFloat("total"));
        return c;
     }
    
}