
public class BankController {
    private BankDatabase database;
    private User currentUser;
    private Vigenere vigenereCipher;

    public BankController(BankDatabase database) {
        this.database = database;
        this.vigenereCipher = new Vigenere();
    }

    public boolean registerUser(String username, String password, String name, double balance, String securityQuestion,
                                String securityAnswer) {
        if (!database.isUsernameTaken(username)) {
            String encryptedPassword = vigenereCipher.encrypt(password, "BANKKEY");
            database.addUser(new User(username, encryptedPassword, name, balance, securityQuestion, securityAnswer));
            return true;
        }
        return false;
    }

    public boolean login(String username, String password) {
        User user = database.getUser(username);
        if (user != null && user.authenticate(password, "BANKKEY")) {
            System.out.println("Security Question: " + user.getSecurityQuestion());
            System.out.print("Answer: ");
            String answer = new java.util.Scanner(System.in).nextLine();
            if (user.verifySecurityAnswer(answer)) {
                currentUser = user;
                return true;
            }
            System.out.println("Incorrect answer to security question.");
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
        if (amount <= 0) {
            System.out.println("Error: Amount must be greater than zero.");
            return false;
        }
        if (currentUser.getBalance() >= amount) {
            currentUser.updateBalance(-amount);
            currentUser.addTransaction("Paid $" + amount + " for " + billDetails);
            return true;
        }
        System.out.println("Error: Insufficient balance.");
        return false;
    }

    public boolean transferMoney(String recipientUsername, double amount) {
        if (amount <= 0) {
            System.out.println("Error: Amount must be greater than zero.");
            return false;
        }
        User recipient = database.getUser(recipientUsername);
        if (recipient != null && currentUser.getBalance() >= amount) {
            currentUser.updateBalance(-amount);
            recipient.updateBalance(amount);
            currentUser.addTransaction("Transferred $" + amount + " to " + recipient.getUsername());
            recipient.addTransaction("Received $" + amount + " from " + currentUser.getUsername());
            return true;
        }
        if (recipient == null) {
            System.out.println("Error: Recipient not found.");
        } else {
            System.out.println("Error: Insufficient balance.");
        }
        return false;
    }
}