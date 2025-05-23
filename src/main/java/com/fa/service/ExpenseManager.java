package com.fa.service;

import java.io.File;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.fa.model.*;

public class ExpenseManager {

    private FileStorageService storage = new FileStorageService();
    private List<Expense> expenses;


    // for testing purpose
    public ExpenseManager() {
        this(true); 
    }

    public ExpenseManager(boolean loadFromFile) {
        File file = new File(FileStorageService.FILE_PATH);
        if (loadFromFile && file.exists()) {
            System.out.println("Found existing data file. Loading expenses...");
            expenses = storage.loadExpenses();
        } else {
            System.out.println("No data file found. Starting fresh.");
            expenses = new ArrayList<>();
        }
    }

    public String getAllExpenses() {
        return IntStream.range(0, expenses.size())
                   .mapToObj(i -> (i + 1) + ". " + expenses.get(i).toString())
                   .collect(Collectors.joining("\n")); 
    }

    // for testing purpose
    public List<Expense> getExpensesList() {
        return expenses;
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
        storage.saveExpenses(expenses);
    }

    public String filterByCategory(ExpenseCategory category) {
        return expenses.stream()
                    .filter(expense -> expense.getCategory() == category)
                    .map(expense -> expense.toString())
                    .collect(Collectors.joining("\n"));
    }

    public String filterByDateRange(LocalDate start, LocalDate end) {
        return expenses.stream()
                    .filter(expense -> (expense.getDate().isEqual(start) || expense.getDate().isAfter(start)) &&
                                       (expense.getDate().isEqual(end) || expense.getDate().isBefore(end)))
                                       .map(expense -> expense.toString())
                                       .collect(Collectors.joining("\n"));
    }


    public boolean removeExpenseByIndex(int index) {
        if (index >= 0 && index < expenses.size()) {
            Expense toRemove = expenses.get(index);
            removeExpense(toRemove.getId());
            storage.saveExpenses(expenses);
            return true;
        } else {
            return false;
        }
    }

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
    
    public void exportToCSV() {
        storage.exportToCSV(expenses);
    }

}

