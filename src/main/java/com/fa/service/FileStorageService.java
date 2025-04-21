package com.fa.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.fa.model.*;

public class FileStorageService {

    public static final String FILE_PATH = "expenses.json";  
    private ObjectMapper objectMapper;

    public FileStorageService() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Allow serialization of LocalDate
    }

    public void saveExpenses(List<Expense> expenses) {
        try {
            objectMapper.writeValue(new File(FILE_PATH), expenses); 
            System.out.println("Storage updated!");
        } catch (IOException e) {
            System.err.println("Error updating storage: " + e.getMessage());
        }
    }

    public List<Expense> loadExpenses() {
        try {
            return objectMapper.readValue(new File(FILE_PATH), new TypeReference<List<Expense>>() {});
        } catch (IOException e) {
            System.err.println("Error loading expenses: " + e.getMessage());
            return List.of();
        }
    }

    

    public void exportToCSV(List<Expense> expenses) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("expenses.csv"))) {
            writer.println("ID,Date,Amount,Category,Title,Description"); 
            for (Expense e : expenses) {
                writer.printf("%s,%s,%.2f,%s,%s,%s%n",
                    e.getId(),
                    e.getDate(),
                    e.getAmount(),
                    e.getCategory(),
                    e.getTitle().replace(",", " "),       
                    e.getDescription().replace(",", " ") 
                );
            }
            System.out.println("Expenses exported to expenses.csv");
        } catch (IOException e) {
            System.err.println("Error exporting to CSV: " + e.getMessage());
        }
    }

}