import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.LinkedHashMap;

public class ExpenseManager implements Reportable {
    private ArrayList<Expense> expenses = new ArrayList<>();

    public void addExpense(String category, double amount) {
        if (!category.equals("Food") && !category.equals("Shopping")
                && !category.equals("Transport")) {
            throw new IllegalArgumentException("Invalid category.");
        }
        expenses.add(new Expense(category, amount));
    }

    public double getCategoryTotal(String category) {
        double total = 0;
        for (Expense e : expenses) {
            if (e.getCategory().equals(category)) {
                total += e.getAmount();
            }
        }
        return total;
    }

    public double getOverallTotal() {
        double total = 0;
        for (Expense e : expenses) {
            total += e.getAmount();
        }
        return total;
    }

    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }
        System.out.println("===== Your Expenses =====");
        String[] categories = {"Food", "Shopping", "Transport"};
        for (String c : categories) {
            double total = getCategoryTotal(c);
            if (total > 0) {
                System.out.println(c + " : " + total + " SAR");
            }
        }
        System.out.println("-------------------------");
        System.out.println("Total: " + getOverallTotal() + " SAR");
    }

    @Override
    public void exportCSV() {
Map<String, Double> totals = new LinkedHashMap<>();

for (Expense e : expenses) {
    String category = e.getCategory();
    double amount = e.getAmount();

    totals.put(category,
            totals.getOrDefault(category, 0.0) + amount);
}

try (FileWriter writer = new FileWriter("expenses.csv")) {

    writer.write("Category,Amount\n");

    for (Map.Entry<String, Double> entry : totals.entrySet()) {
        writer.write(entry.getKey() + ","
                + entry.getValue() + "\n");
    }

    System.out.println("CSV report exported successfully!");

} catch (IOException | SecurityException e) {
    System.out.println("Error exporting CSV file!");
}   
    }
}
