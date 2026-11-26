import java.util.ArrayList;

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
        // الطالبة الثانية اكتبي كود التصدير هنا
    }
}
