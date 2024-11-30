
// BankController.java
public class BankController {
    private BankDatabase dataBase;
    private User currentUser;

    public BankController(BankDatabase database) {
        this.dataBase = database;
    }

    public boolean login(String username, String password) {
        User user = dataBase.getUser(username);
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
        User recipient = dataBase.getUser(recipientUsername);
        if (recipient != null && currentUser.getBalance() >= amount) {
            currentUser.updateBalance(-amount);
            recipient.updateBalance(amount);
            currentUser.addTransaction("Transferred $" + amount + " to " + recipient.getUsername());
            recipient.addTransaction("Received $" + amount + " from " + currentUser.getUsername());
            return true;
        }
        return false;
    }


    public boolean registerUser(String username, String password, String name, double balance) {
        if (!dataBase.isUsernameTaken(username)) { // Check if username is unique
            dataBase.addUser(new User(username, password, name, balance));
            return true;
        }
        return false; // Username is already taken
    }

}
