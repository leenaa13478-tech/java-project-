public class Expense {
    private String category;
    private double amount;

    public Expense(String category, double amount) {
        if (!(amount > 0) || Double.isInfinite(amount)) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        this.category = category;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }
}
