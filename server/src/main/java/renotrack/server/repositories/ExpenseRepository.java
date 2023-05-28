package renotrack.server.repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import renotrack.server.models.CategorySum;
import renotrack.server.models.Expense;

@Repository
public class ExpenseRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //Method to retrieve list of expenses by userId
    //Result is sorted by ascending order of category and date
    public List<Expense> findAllExpenses(String userId) {
        List<Expense> expenses = new LinkedList<>();
        
        String sql = "SELECT * FROM expenses WHERE user_id = ? ORDER BY category ASC, date ASC";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, userId);
        while (rs.next()) {
            expenses.add(Expense.createExpenseFromRowSet(rs));
        }
        return expenses;
    }

    //Method to retrieve events by expenseId
    public Expense findExpenseById(int expenseId) {
        String sql = "SELECT * FROM expenses WHERE expense_id = ?";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, expenseId);
        if (rs.next()) {
            return Expense.createExpenseFromRowSet(rs);
        }
        else {
            return null;
        }
    }

    //Method to insert expense into database
    public int insertExpense(String userId, Expense expense) {
        String sql = "INSERT INTO expenses (user_id, date, category, business, description, amount, payment, balance) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, userId, expense.getDate(), expense.getCategory(), expense.getBusiness(),
        expense.getDescription(), expense.getAmount(), expense.getPayment(), expense.getBalance());
    }

    //Method to update expense in database
    public int updateExpense(int expenseId, Expense expense) {
        String sql = "UPDATE expenses SET date = ?, category = ?, business = ?, description = ?, amount = ?, payment = ?, balance = ? WHERE expense_id = ?";
        return jdbcTemplate.update(sql, expense.getDate(), expense.getCategory(), expense.getBusiness(), 
        expense.getDescription(), expense.getAmount(), expense.getPayment(), expense.getBalance(), expenseId);
    }

    //Method to delete expense from database
    public int deleteExpense(int expenseId) {
        String sql = "DELETE FROM expenses WHERE expense_id = ?";
        return jdbcTemplate.update(sql, expenseId);
    }

    //Method to retrieve total sum of amount by userId
    public Float sumAmount(String userId) {
        String sql = "SELECT SUM(amount) FROM expenses WHERE user_id = ?";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, userId);
        if (rs.next()) {
            return rs.getFloat("SUM(amount)");
        }
        return 0f;
    }

    //Method to retrieve total sum of payment by userId
    public Float sumPayment(String userId) {
        String sql = "SELECT SUM(payment) FROM expenses WHERE user_id = ?";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, userId);
        if (rs.next()) {
            return rs.getFloat("SUM(payment)");
        }
        return 0f;
    }

    //Method to retrieve total sum of balance by userId
    public Float sumBalance(String userId) {
        String sql = "SELECT SUM(balance) FROM expenses WHERE user_id = ?";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, userId);
        if (rs.next()) {
            return rs.getFloat("SUM(balance)");
        }
        return 0f;
    }

    //Method to retrieve list of category and their sum of amount by userId
    public List<CategorySum> sumAmountPerCategory(String userId) {
        List<CategorySum> catSum = new LinkedList<>();

        String sql = "SELECT category, SUM(amount) AS total FROM expenses WHERE user_id = ? GROUP BY category";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, userId);
        while (rs.next()) {
            catSum.add(CategorySum.createCatSumFromRowSet(rs));
        }
        return catSum;
    }

}