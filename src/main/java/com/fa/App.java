package com.fa;

import java.time.LocalDate;

import com.fa.model.Expense;
import com.fa.model.ExpenseCategory;
import com.fa.service.ExpenseManager;


public class App 
{
    public static void main( String[] args )
    {

        ExpenseManager expenseManager = new ExpenseManager();

        if (args.length < 1) {
            System.out.println("Usage: java App <command>");
            return;
        }


        switch (args[0]) {

            case "all":
                expenseManager.getAllExpenses();
                break;

            case "add":
                if (args.length != 4) { 
                    System.out.println("Usage: java Main add <title> <amount> <category> <description>(optional)");
                    break;
                }
                
                String title = args[1];
                double amount = Double.valueOf(args[2]);
                ExpenseCategory category = ExpenseCategory.OTHER; 
                String description = args.length >= 5 ? args[4] : null;
                
                try {
                    category = ExpenseCategory.valueOf(args[3].toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid category. Defaulting to 'OTHER'.");
                }

                Expense expense = new Expense(title, amount, category, description);

                expenseManager.addExpense(expense);
                System.out.println("Expense added successfully.");
                break;

            case "remove":
                if (args.length != 2) {
                    System.out.println("Usage: java Main remove <index>");
                    break;
                }
                int indexToDelete = Integer.parseInt(args[1]);
                if (expenseManager.removeExpenseByIndex(indexToDelete - 1)) {
                    System.out.println("Expense removed successfully.");
                } else {
                    System.out.println("Invalid index.");
                }
                break;


            case "byCategory":
                if (args.length != 2) {
                    System.out.println("Usage: java Main byCategory <category>");
                    break;
                }

                category = ExpenseCategory.OTHER; 

                try {
                    category = ExpenseCategory.valueOf(args[2].toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid category. Defaulting to 'OTHER'.");
                }
                expenseManager.filterByCategory(category);
                break;

            case "byDateRange":
                if (args.length != 3) {
                    System.out.println("Usage: java Main byDateRange <startDate> <endDtae> (YYYY-MM-DD)");
                    break;
                }
                LocalDate startDate = LocalDate.parse(args[1]);
                LocalDate endDtae = LocalDate.parse(args[2]);
                expenseManager.filterByDateRange(startDate, endDtae);
                break;

            case "categoryBreakdown":
                expenseManager.getCategoryBreakdown();
                break;

            case "total":
                expenseManager.getTotalSpent();
                break;

            case "exportCSV":
                expenseManager.exportToCSV();
                break;

            default:
                System.out.println("""
                    Usage: java Main <command>
                    \nall
                    \nadd
                    \nremove
                    \nbyCategory
                    \nbyDateRange
                    \ncategoryBreakdown
                    \ntotal
                    """);
                break;
        }
      

    }
}


