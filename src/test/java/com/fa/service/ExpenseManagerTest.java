package com.fa.service;

import com.fa.model.Expense;
import com.fa.model.ExpenseCategory;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class ExpenseManagerTest {

    private ExpenseManager expenseManager;
    private Expense expense;
    private Expense food1;
    private Expense transport;
    private Expense food2;

    @Before
    public void setup() {
        expenseManager = new ExpenseManager(false);
        expense = new Expense("Lunch", 12.5, ExpenseCategory.FOOD, "Burger");
        food1 = new Expense("Pizza", 10.0, ExpenseCategory.FOOD, "Dinner");
        transport = new Expense("Bus", 2.5, ExpenseCategory.TRANSPORT, "Bus fare");
        food2 = new Expense("Coffee", 3.0, ExpenseCategory.FOOD, "Morning coffee");

        expense.setDate(LocalDate.of(2025, 4, 21));
        food1.setDate(LocalDate.of(2025, 4, 22));
        transport.setDate(LocalDate.of(2025, 4, 23));
        food2.setDate(LocalDate.of(2025, 4, 24));

        expenseManager.addExpense(expense);
        expenseManager.addExpense(food1);
        expenseManager.addExpense(transport);
        expenseManager.addExpense(food2);
    }   

    @Test
    public void testAddExpense_increasesListSize() {
        int initialSize = expenseManager.getExpensesList().size();

        Expense newExpense = new Expense("Dinner", 15.0, ExpenseCategory.FOOD, "Pizza");
        expenseManager.addExpense(newExpense);

        int newSize = expenseManager.getExpensesList().size();

        assertEquals(initialSize + 1, newSize);
    }

    @Test
    public void testRemoveExpenseByIndex_decreasesListSize() {

        int initialSize = expenseManager.getExpensesList().size();

        expenseManager.removeExpenseByIndex(1); 

        int newSize = expenseManager.getExpensesList().size();
        assertEquals(initialSize - 1, newSize);
    }


    @Test
    public void testFilterByCategory_returnsOnlyMatchingExpensesAsString() {

        String result = expenseManager.filterByCategory(ExpenseCategory.FOOD);

        assertTrue(result.contains("Pizza"));
        assertTrue(result.contains("Coffee"));
        assertTrue(result.contains("Lunch"));

        assertFalse(result.contains("Bus"));
    }


    @Test
    public void testGetTotalSpent_returnsCorrectSum() {
        double expectedTotal = expense.getAmount() + food1.getAmount() + transport.getAmount() + food2.getAmount();

        double actualTotal = expenseManager.getTotalSpent();

        assertEquals(expectedTotal, actualTotal, 0.001);
    }

    
    @Test
    public void testGetCategoryBreakdown_returnsCorrectSumsPerCategory() {
        var breakdown = expenseManager.getCategoryBreakdown();

        assertEquals(25.5, breakdown.get(ExpenseCategory.FOOD), 0.001);
        assertEquals(2.5, breakdown.get(ExpenseCategory.TRANSPORT), 0.001);
    }


    @Test
    public void testFilterByDateRange_returnsOnlyExpensesWithinRange() {

        LocalDate startDate = LocalDate.of(2025, 4, 22);
        LocalDate endDate = LocalDate.of(2025, 4, 23);

        String result = expenseManager.filterByDateRange(startDate, endDate);

        assertTrue(result.contains("Pizza"));
        assertTrue(result.contains("Bus"));
        assertFalse(result.contains("Lunch"));
        assertFalse(result.contains("Coffee"));
    }


}
