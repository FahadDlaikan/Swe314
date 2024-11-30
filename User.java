public class User {
    private String username;
    private String password; // Encrypted password
    private String name;
    private double balance;
    private StringBuilder transactionHistory;

    public User(String username, String password, String name, double balance) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.balance = balance;
        this.transactionHistory = new StringBuilder();
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public String getTransactionHistory() {
        return transactionHistory.toString();
    }

    public void updateBalance(double amount) {
        this.balance += amount;
    }

    public void addTransaction(String transaction) {
        transactionHistory.append(transaction).append("\n");
    }

    // Updated authenticate method
    public boolean authenticate(String enteredPassword, String encryptionKey) {
        Vigenere vigenereCipher = new Vigenere();
        String encryptedEnteredPassword = vigenereCipher.encrypt(enteredPassword, encryptionKey);
        return encryptedEnteredPassword.equals(this.password);
    }
}
