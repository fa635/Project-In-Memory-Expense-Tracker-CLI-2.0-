package com.fa;

import java.time.LocalDate;

import com.fa.model.Expense;
import com.fa.model.ExpenseCategory;
import com.fa.service.ExpenseManager;


public class App 
{
    public static void main( String[] args )
    {
        ExpenseManager manager = new ExpenseManager();
        manager.addExpense(new Expense("Lunch", 15.50, ExpenseCategory.FOOD, LocalDate.of(2025, 4, 19), "Pizza"));
        manager.addExpense(new Expense("Bus Ticket", 3.00, ExpenseCategory.TRANSPORT, LocalDate.of(2025, 4, 19), "Subway"));
        
        // Get all expenses
        System.out.println(manager.getAllExpenses());
        
        // Get total spent
        System.out.println("Total spent: $" + manager.getTotalSpent());
        
        // Filter by category
        System.out.println(manager.filterByCategory(ExpenseCategory.FOOD));
        
        // Category breakdown
        System.out.println(manager.getCategoryBreakdown());
    }
}
