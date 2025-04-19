package com.fa.service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import com.fa.model.*;

public class ExpenseManager {

    private List<Expense> expenses = new ArrayList<>();



    public String getAllExpenses() {
        return expenses.stream()
                   .map(Expense::toString)
                   .collect(Collectors.joining("\n"));
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public List<Expense> filterByCategory(ExpenseCategory category) {
        return expenses.stream()
                    .filter(expense -> expense.getCategory() == category)
                    .collect(Collectors.toList());
    }

    public List<Expense> filterByDateRange(LocalDate start, LocalDate end) {
        return expenses.stream()
                    .filter(expense -> (expense.getDate().isEqual(start) || expense.getDate().isAfter(start)) &&
                                       (expense.getDate().isEqual(end) || expense.getDate().isBefore(end)))
                    .collect(Collectors.toList());
    }

    // add some better way later to make it user-friendly
    // and ask confirmation before delete
    public void removeExpense(UUID id) {
        expenses.removeIf(expense -> expense.getId().equals(id));
    }
    
    public double getTotalSpent() {
        return expenses.stream()
                       .mapToDouble(Expense::getAmount)
                       .sum();
    }
    
    public Map<ExpenseCategory, Double> getCategoryBreakdown() {
        return expenses.stream()
                       .collect(Collectors.groupingBy(
                           Expense::getCategory, 
                           Collectors.summingDouble(Expense::getAmount)
                       ));
    }
    

}

