package model;

import java.time.LocalDate;

public class Transaction {
    private String type;
    private LocalDate date;
    private String category;
    private double amount;

    public Transaction(String type, LocalDate date, String category, double amount) {
        this.type = type.toUpperCase();
        this.date = date;
        this.category = category;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return type + "," + date + "," + category + "," + amount;
    }
}
