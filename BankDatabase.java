
import java.util.HashMap;

public class BankDatabase {
    private HashMap<String, User> users = new HashMap<>();

    public BankDatabase() {
        this.users = new HashMap<>();
        addUser(new User("john_doe", "password123", "John Doe", 1000.00));
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public User getUser(String username) {
        return users.get(username);
    }
    public boolean  isUsernameTaken(String username) {
        return users.containsKey(username);
    }
}