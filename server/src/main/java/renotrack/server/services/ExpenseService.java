package renotrack.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import renotrack.server.models.CategorySum;
import renotrack.server.models.Expense;
import renotrack.server.repositories.ExpenseRepository;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepo;

    //Method to retrieve list of expenses by userId sorted by ascending order of category and date
    public List<Expense> findAllExpenses(String userId) {
        return expenseRepo.findAllExpenses(userId);
    }

    //Method to retrieve expense by expenseId
    public Expense findExpenseById(int expenseId) {
        return expenseRepo.findExpenseById(expenseId);
    }

    //Method to insert userId expense
    public int insertExpense(String userId, Expense expense) {
        return expenseRepo.insertExpense(userId, expense);
    }

    //Method to update expenseId expense
    public int updateExpense(int expenseId, Expense expense) {
        return expenseRepo.updateExpense(expenseId, expense);
    }

    //Method to delete expenseId expense
    public int deleteExpense(int expenseId) {
        return expenseRepo.deleteExpense(expenseId);
    }

    //Method to retrieve total sum of amount by userId
    public Float sumAmount(String userId) {
        return expenseRepo.sumAmount(userId);
    }

    //Method to retrieve total sum of payment by userId
    public Float sumPayment(String userId) {
        return expenseRepo.sumPayment(userId);
    }

    //Method to retrieve total sum of balance by userId
    public Float sumBalance(String userId) {
        return expenseRepo.sumBalance(userId);
    }

    //Method to retrieve list of category and their sum of amount by userId
    public List<CategorySum> sumAmountPerCategory(String userId) {
        return expenseRepo.sumAmountPerCategory(userId);
    }
    
}