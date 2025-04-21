package com.fa.model;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Expense {
    UUID id;
    String title;
    double amount;
    ExpenseCategory category;
    LocalDate date;
    String description;

    public Expense(String title, double amount, ExpenseCategory category, String description) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.date = LocalDate.now();
        this.description = (description != null) ? description : "";
    }

    // for jackson
    public Expense() {}
    

    @Override
    public String toString() {
        return String.format(
            "%s | %s | $%.2f | %s | %s",
            id.toString().substring(0, 8),
            date,
            amount,
            category,
            title
        );
    }
}
