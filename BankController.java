
public class BankController {
    private BankDatabase database;
    private User currentUser;

    public BankController(BankDatabase database) {
        this.database = database;
    }

    public boolean login(String username, String password) {
        User user = database.getUser(username);
        if (user != null && user.authenticate(password)) {
            this.currentUser = user;
            return true;
        }
        return false;
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
