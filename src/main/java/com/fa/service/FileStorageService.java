package com.fa.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fa.model.*;

public class FileStorageService {

    public static final String FILE_PATH = "expenses.json";  
    private ObjectMapper objectMapper = new ObjectMapper(); 

    public void saveExpenses(List<Expense> expenses) {
        try {
            objectMapper.writeValue(new File(FILE_PATH), expenses); 
            System.out.println("Expenses saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving expenses: " + e.getMessage());
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
}
// add option to export file in cvs