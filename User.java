
public class User {
    private String username;
    private String password; // Stored securely (hashed in real scenarios)
    private String name;
    private double balance;
    private String transactionHistory;

    public User(String username, String password, String name, double balance) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.balance = balance;
        this.transactionHistory = "No transactions yet.";
    }

    public String getUsername() {
        return username;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public String getTransactionHistory() {
        return transactionHistory;
    }

    public void updateBalance(double amount) {
        this.balance += amount;
    }

    public void addTransaction(String transaction) {
        this.transactionHistory += "\n" + transaction;
    }
}


