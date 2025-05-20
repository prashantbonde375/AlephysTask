package service;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import model.Transaction;

public class TransactionManager {
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public void loadFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 4)
                    continue;
                Transaction t = new Transaction(
                        parts[0],
                        LocalDate.parse(parts[1], formatter),
                        parts[2],
                        Double.parseDouble(parts[3]));
                addTransaction(t);
            }
            System.out.println("File loaded successfully.");
        } catch (IOException e) {
            System.out.println("Failed to load file: " + e.getMessage());
        }
    }

    public void saveToFile(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Transaction t : transactions) {
                bw.write(t.toString());
                bw.newLine();
            }
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            System.out.println("Failed to save file: " + e.getMessage());
        }
    }

    public void printMonthlySummary(int year, int month) {
        double totalIncome = 0, totalExpense = 0;

        System.out.println("\n--- Monthly Summary for " + year + "-" + String.format("%02d", month) + " ---");
        for (Transaction t : transactions) {
            if (t.getDate().getYear() == year && t.getDate().getMonthValue() == month) {
                if (t.getType().equals("INCOME")) {
                    totalIncome += t.getAmount();
                } else {
                    totalExpense += t.getAmount();
                }
                System.out.println(t);
            }
        }

        System.out.println("Total Income: $" + totalIncome);
        System.out.println("Total Expense: $" + totalExpense);
        System.out.println("Net Balance: $" + (totalIncome - totalExpense));
    }
}
