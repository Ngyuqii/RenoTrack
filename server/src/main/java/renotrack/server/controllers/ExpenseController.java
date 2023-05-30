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
import renotrack.server.models.CategorySum;
import renotrack.server.models.Expense;
import renotrack.server.services.ExpenseService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(path="/api/expenses", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExpenseController {

    @Autowired
    private ExpenseService expenseSvc;

    //Retrieve list of expenses for a userId
    @GetMapping(path="/{userId}")
    public ResponseEntity<String> getAllExpenses(@PathVariable String userId) {
        List<Expense> expenses = expenseSvc.findAllExpenses(userId);
        if(expenses.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
            for (Expense e : expenses) {
            arrBuilder.add(e.toJsonObj());
            }
            return ResponseEntity.ok(Json.createObjectBuilder()
                                    .add("Expenses", arrBuilder)
                                    .build().toString());
        }   
    }

    //Retrieve an expense of expenseId
    @GetMapping(path="/{userId}/{expenseId}")
    public ResponseEntity<String> getExpenseById(@PathVariable String userId, @PathVariable int expenseId) {
        Expense expense = expenseSvc.findExpenseById(expenseId);
        JsonObject response = Json.createObjectBuilder()
        .add("Expense", expense.toJsonObj())
        .build();
        return ResponseEntity.ok(response.toString());
    }

    //Create a new expense for a userId
    @PostMapping(path="/create/{userId}")
    public ResponseEntity<String> createExpense(@PathVariable String userId, @RequestBody String expense) {
        JsonObject expenseJO = Expense.toJson(expense);
        Expense expenseObj = Expense.createExpense(expenseJO);

        int result = expenseSvc.insertExpense(userId, expenseObj);
        if(result == 0){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Update an existing expense of a expenseId
    @PutMapping(path="/update/{expenseId}")
    public ResponseEntity<String> updateExpense(@PathVariable int expenseId, @RequestBody String expense) {
        JsonObject expenseJO = Expense.toJson(expense);
        Expense expenseObj = Expense.createExpense(expenseJO);
        
        int result = expenseSvc.updateExpense(expenseId, expenseObj);
        if(result == 0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Delete an existing expense of a expenseId
    @DeleteMapping(path="/delete/{expenseId}")
    public ResponseEntity<String> deleteExpense(@PathVariable int expenseId) {
        int result = expenseSvc.deleteExpense(expenseId);
        if(result == 0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Get sum of amount for a userId
    @GetMapping(path="/{userId}/amountsum")
    public ResponseEntity<String> sumAmount(@PathVariable String userId) {
        float sum = expenseSvc.sumAmount(userId);
        JsonObject response = Json.createObjectBuilder()
        .add("Total Amount", sum)
        .build();
        return ResponseEntity.ok(response.toString());
    }

    //Get sum of payment for a userId
    @GetMapping(path="/{userId}/paymentsum")
    public ResponseEntity<String> sumPayment(@PathVariable String userId) {
        float sum = expenseSvc.sumPayment(userId);
        JsonObject response = Json.createObjectBuilder()
        .add("Total Payment", sum)
        .build();
        return ResponseEntity.ok(response.toString());
    }

    //Get sum of balance for a userId
    @GetMapping(path="/{userId}/balancesum")
    public ResponseEntity<String> sumBalance(@PathVariable String userId) {
        float sum = expenseSvc.sumBalance(userId);
        JsonObject response = Json.createObjectBuilder()
        .add("Total Balance", sum)
        .build();
        return ResponseEntity.ok(response.toString());
    }
    
    //Get sum of amount per category for a userId
    @GetMapping(path="/{userId}/amountsum/category")
    public ResponseEntity<String> sumAmountPerCategory(@PathVariable String userId) {
        List<CategorySum> catSum = expenseSvc.sumAmountPerCategory(userId);
        if(catSum.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
            for (CategorySum cs : catSum) {
            arrBuilder.add(cs.toJsonObj());
            }
            return ResponseEntity.ok(Json.createObjectBuilder()
                                    .add("Expenses", arrBuilder)
                                    .build().toString());
        }
    }
    
}