
// BankController.java
public class BankController {
    private BankDatabase database;
    private User currentUser;
    private Vigenere vigenereCipher;

    public BankController(BankDatabase database) {
        this.database = database;
        this.vigenereCipher = new Vigenere();
    }

    // When registering, encrypt the password
    public boolean registerUser(String username, String password, String name, double balance) {
        if (!database.isUsernameTaken(username)) {
            // Encrypt password
            String encryptedPassword = vigenereCipher.encrypt(password, "BANKKEY");
            System.out.println("Encrypted Password (Debug): " + encryptedPassword); // Debug
            database.addUser(new User(username, encryptedPassword, name, balance));
            return true;
        }
        return false;// means the username is taken
    }

    // When authenticating, decrypt the password for comparison
    public boolean login(String username, String password) {
        User user = database.getUser(username);
        if (user != null && user.authenticate(password)) {
            currentUser = user;
            return true;
        }
        return false; // issue
    }

    public void logout() {
        currentUser = null;
    }

    public String getProfile() {
        return "Name: " + currentUser.getName() + "\nBalance: $" + currentUser.getBalance();
    }

    public String getTransactionHistory() {
        return currentUser.getTransactionHistory();
    }

    public boolean payBill(double amount, String billDetails) {
        if (currentUser.getBalance() >= amount) {
            currentUser.updateBalance(-amount);
            currentUser.addTransaction("Paid $" + amount + " for " + billDetails);
            return true;
        }
        return false;
    }

    public boolean transferMoney(String recipientUsername, double amount) {
        User recipient = database.getUser(recipientUsername);
        if (recipient != null && currentUser.getBalance() >= amount) {
            currentUser.updateBalance(-amount);
            recipient.updateBalance(amount);
            currentUser.addTransaction("Transferred $" + amount + " to " + recipient.getUsername());
            recipient.addTransaction("Received $" + amount + " from " + currentUser.getUsername());
            return true;
        }
        return false;
    }

}
