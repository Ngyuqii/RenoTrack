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

    @GetMapping("/{userId}")
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

    @GetMapping("/{userId}/{id}")
    public ResponseEntity<String> getExpenseById(@PathVariable String userId, @PathVariable int expenseId) {
        Expense expense = expenseSvc.findExpenseById(expenseId);
        JsonObject response = Json.createObjectBuilder()
        .add("Expense", expense.toJsonObj())
        .build();
        return ResponseEntity.ok(response.toString());
    }

    @PostMapping("/{userId}")
    public ResponseEntity<String> createExpense(@PathVariable String userId, @RequestBody String expense) {
        JsonObject userJO = Expense.toJson(expense);
        Expense expenseObj = Expense.createExpense(userJO);

        int result = expenseSvc.insertOrUpdateExpense(userId, expenseObj);
        if(result == 0){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{userId}/{id}")
    public ResponseEntity<String> updateExpense(@PathVariable String userId, @PathVariable int expenseId, @RequestBody String expense) {
        JsonObject userJO = Expense.toJson(expense);
        Expense expenseObj = Expense.createExpense(userJO);
        
        int result = expenseSvc.insertOrUpdateExpense(userId, expenseObj);
        if(result == 0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{userId}/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable String userId, @PathVariable int expenseId) {
        int result = expenseSvc.deleteExpense(expenseId);
        if(result == 0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/expenses/{userId}/sum/amount")
    public ResponseEntity<String> sumAmount(@PathVariable String userId) {
        float sum = expenseSvc.sumAmount(userId);
        JsonObject response = Json.createObjectBuilder()
        .add("Total Amount", sum)
        .build();
        return ResponseEntity.ok(response.toString());
    }

    @GetMapping("/expenses/{userId}/sum/payment")
    public ResponseEntity<String> sumPayment(@PathVariable String userId) {
        float sum = expenseSvc.sumPayment(userId);
        JsonObject response = Json.createObjectBuilder()
        .add("Total Payment", sum)
        .build();
        return ResponseEntity.ok(response.toString());
    }

    @GetMapping("/expenses/{userId}/sum/balance")
    public ResponseEntity<String> sumBalance(@PathVariable String userId) {
        float sum = expenseSvc.sumBalance(userId);
        JsonObject response = Json.createObjectBuilder()
        .add("Total Balance", sum)
        .build();
        return ResponseEntity.ok(response.toString());
    }

    @GetMapping("/expenses/{userId}/sum/category")
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