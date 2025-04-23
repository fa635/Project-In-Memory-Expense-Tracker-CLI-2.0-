package com.fa.service;

import com.fa.model.Expense;
import com.fa.model.ExpenseCategory;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class ExpenseManagerTest {

    private ExpenseManager expenseManager;
    private Expense expense;

    @Before
    public void setup() {
        expenseManager = new ExpenseManager();
        expense = new Expense("Lunch", 12.5, ExpenseCategory.FOOD, "Burger");
        expenseManager.addExpense(expense);
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



}
