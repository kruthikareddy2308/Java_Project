import java.io.*;
import java.util.*;

class Expense {
    private String category;
    private double amount;
    private String description;
    Expense(String category, double amount, String description) {
        this.category = category;
        this.amount = amount;
        this.description = description;
    }String getCategory() {
        return category;
    }double getAmount() {
        return amount;
    }String getDescription() {
        return description;
    }public String toString() {
        return category + " - Rs." + amount + " - " + description;
    }
}public class MonthlyExpenseAnalyzer {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Expense> expenses = new ArrayList<>();
    static double getTotal() {
        double total = 0;
        for (Expense e : expenses) {
            total = total + e.getAmount();
        }
        return total;
    }static void addExpense() {
        try {
            System.out.println("\n1. Food");
            System.out.println("2. Grocery");
            System.out.println("3. Shopping");
            System.out.println("4. Transport");
            System.out.print("Choose category: ");
            int choice = sc.nextInt();
            String category;
            switch (choice) {
            case 1:
                category = "Food";
                break;
            case 2:
                category = "Grocery";
                break;
            case 3:
                category = "Shopping";
                break;
            case 4:
                category = "Transport";
                break;
            default:
                System.out.println("Invalid category");
                return;
            }System.out.print("Enter amount: ");
            double amount = sc.nextDouble();
            sc.nextLine();
            if (amount <= 0) {
                System.out.println("Invalid amount");
                return;
            }System.out.print("Enter description: ");
            String description = sc.nextLine();
            expenses.add(new Expense(category, amount, description));
            System.out.println("Expense added!");
        } catch (InputMismatchException e) {
            System.out.println("Please enter valid input.");
            sc.nextLine();
        }
    }static void searchExpense() {
        sc.nextLine();
        System.out.print("Enter word to search: ");
        String word = sc.nextLine().toLowerCase();
        boolean found = false;
        for (Expense e : expenses) {
        if (e.getCategory().toLowerCase().contains(word) ||
            e.getDescription().toLowerCase().contains(word)) {
            System.out.println(e);
            found = true;
            }
        }if (!found) {
            System.out.println("Expense not found.");
        }
    }static void saveExpenses() {
        try {
            FileWriter file = new FileWriter("expenses.txt");
        for (Expense e : expenses) {
            file.write(e.getCategory() + "," +
              e.getAmount() + "," +
              e.getDescription() + "\n");
        }
        file.close();
            System.out.println("Expenses saved.");
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }
    public static void main(String[] args) {
        System.out.print("Enter monthly income: ");
        double income = sc.nextDouble();
        System.out.print("Enter monthly budget: ");
        double budget = sc.nextDouble();
        int choice;
            do {
            System.out.println("\n===== MONTHLY EXPENSE ANALYZER =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Search Expense");
            System.out.println("4. Sort Expenses");
            System.out.println("5. Total Expenses");
            System.out.println("6. Check Budget");
            System.out.println("7. Savings");
            System.out.println("8. Save Expenses");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            switch (choice) {
            case 1:
                addExpense();
                break;
            case 2:
            if (expenses.size() == 0) {
                System.out.println("No expenses.");
            } else {
            for (Expense e : expenses) {
                System.out.println(e);
                }
            }
                break;
            case 3:
                searchExpense();
                break;
            case 4:
                expenses.sort((e1, e2) ->
            Double.compare(e2.getAmount(), e1.getAmount()));
                System.out.println("Expenses sorted.");
            for (Expense e : expenses) {
                System.out.println(e);
            }
                break;
            case 5:
                System.out.println("Total Expenses = Rs." + getTotal());
                break;
            case 6:
                double remaining = budget - getTotal();
            if (remaining > 0) {
                System.out.println("Remaining Budget = Rs." + remaining);
            } else if (remaining == 0) {
                System.out.println("Budget fully used.");
            } else {
                System.out.println("Budget exceeded by Rs." + (-remaining));
            }
                break;
            case 7:
            double savings = income - getTotal();
            if (savings > 0) {
                System.out.println("Savings = Rs." + savings);
            } else {
                System.out.println("No savings.");
            }
                break;
            case 8:
                saveExpenses();
                break;
            case 9:
                System.out.println("Thank you!");
                break;
           default:
                System.out.println("Invalid choice.");
            }
        } while (choice != 9);
    }
}