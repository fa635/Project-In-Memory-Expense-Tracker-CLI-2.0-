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

    @Before
    public void setup() {
        expenseManager = new ExpenseManager();
    }

    @Test
    public void testAddExpense_increasesListSize() {
        int initialSize = expenseManager.getExpensesList().size();

        Expense expense = new Expense("Lunch", 12.5, ExpenseCategory.FOOD, "Burger");
        expenseManager.addExpense(expense);

        int newSize = expenseManager.getExpensesList().size();

        assertEquals(initialSize + 1, newSize);
    }

}
