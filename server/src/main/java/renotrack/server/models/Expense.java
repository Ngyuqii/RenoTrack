package renotrack.server.models;

import java.io.Reader;
import java.io.StringReader;
import java.time.LocalDate;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Expense {

    private Integer expenseId;
    private LocalDate date;
    private String category;
    private String business;
    private String description;
    private float amount;
    private float payment;
    private float balance;
    
    //Constructor
    public Expense() {
    }

    //Getters
    public Integer getExpenseId() {
        return expenseId;
    }
    public LocalDate getDate() {
        return date;
    }
    public String getCategory() {
        return category;
    }
    public String getBusiness() {
        return business;
    }
    public String getDescription() {
        return description;
    }
    public float getAmount() {
        return amount;
    }
    public float getPayment() {
        return payment;
    }
    public float getBalance() {
        return balance;
    }

    //Setters
    public void setExpenseId(Integer expenseId) {
        this.expenseId = expenseId;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setBusiness(String business) {
        this.business = business;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }
    public void setPayment(float payment) {
        this.payment = payment;
    }
    public void setBalance(float balance) {
        this.balance = balance;
    }

    //Returns JsonObject from Json String
    public static JsonObject toJson(String str) {
        Reader reader = new StringReader(str);
        JsonReader jsonReader = Json.createReader(reader);
        return jsonReader.readObject();
    }

    //Create Expense Object from JsonObject
    public static Expense createExpense(JsonObject json) {
        Expense e = new Expense();
        e.setExpenseId(getValue(json, "expenseId"));
        e.setDate(LocalDate.parse(json.getString("date")));
        e.setCategory(json.getString("category"));
        e.setBusiness(json.getString("business"));
        e.setDescription(json.getString("description"));
        e.setAmount(json.getJsonNumber("amount").numberValue().floatValue());
        e.setPayment(json.getJsonNumber("payment").numberValue().floatValue());
        e.setBalance(json.getJsonNumber("balance").numberValue().floatValue());
        return e;
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

    //Create Json Object from Expense Object
    public JsonObject toJsonObj() {
        return Json.createObjectBuilder()
                .add("expenseId", getExpenseId())
                .add("date", getDate().toString())
                .add("category", getCategory())
                .add("business", getBusiness())
                .add("description", getDescription())
                .add("amount", getAmount())
                .add("payment", getPayment())
                .add("balance", getBalance())
                .build();
    }

     //Create Expense Object from RowSet Object
     public static Expense createExpenseFromRowSet(SqlRowSet rs) {
        Expense e = new Expense();
        e.setExpenseId(rs.getInt("expense_id"));
        e.setDate(rs.getDate("date").toLocalDate());
        e.setCategory(rs.getString("category"));
        e.setBusiness(rs.getString("business"));
        e.setDescription(rs.getString("description"));
        e.setAmount(rs.getFloat("amount"));
        e.setPayment(rs.getFloat("payment"));
        e.setBalance(rs.getFloat("balance"));
        return e;
     }

}