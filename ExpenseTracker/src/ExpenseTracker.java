import java.util.*;

import model.Transaction;
import service.TransactionManager;

import java.time.LocalDate;

public class ExpenseTracker {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TransactionManager manager = new TransactionManager();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Expense Tracker ---\n");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Monthly Summary");
            System.out.println("4. Load Transactions from File");
            System.out.println("5. Save Transactions to File");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addTransaction("INCOME");
                case 2 -> addTransaction("EXPENSE");
                case 3 -> viewMonthlySummary();
                case 4 -> loadFromFile();
                case 5 -> saveToFile();
                case 6 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void addTransaction(String type) {
        scanner.nextLine();
        System.out.print("Enter Date (yyyy-MM-dd): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter Category (e.g., Salary/Business/Food/Rent/Travel): ");
        String category = scanner.nextLine();

        System.out.print("Enter Amount: ");
        double amount = scanner.nextDouble();

        Transaction t = new Transaction(type, date, category, amount);
        manager.addTransaction(t);
        System.out.println("Transaction added.");
    }

    private static void viewMonthlySummary() {
        System.out.print("Enter Year (e.g., 2025): ");
        int year = scanner.nextInt();
        System.out.print("Enter Month (1-12): ");
        int month = scanner.nextInt();
        manager.printMonthlySummary(year, month);
    }

    private static void loadFromFile() {
        scanner.nextLine();
        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();
        manager.loadFromFile(filePath);
    }

    private static void saveToFile() {
        scanner.nextLine();
        System.out.print("Enter file path to save: ");
        String filePath = scanner.nextLine();
        manager.saveToFile(filePath);
    }
}
