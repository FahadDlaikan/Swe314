import java.util.Scanner;

public class BankCLI {
    private BankController controller;
    public Scanner scanner;

    public BankCLI(BankController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\nWelcome to the Bank System");
            System.out.println("1. Login");
            System.out.println("2. Register New User");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    registerUser();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void login() {
        int attempts = 0;
        while (attempts < 3) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (controller.login(username, password)) {
                System.out.println("Login successful!");
                dashboard();
                return;
            } else {
                attempts++;
                System.out.println("Invalid credentials or incorrect answer to the security question. You have " + (3 - attempts) + " attempt(s) remaining.");
            }
        }

        System.out.println("Too many failed attempts. Returning to the main menu.");
    }

    private void registerUser() {
        System.out.print("Enter a new username: ");
        String username = scanner.nextLine();
        String password;
        while (true) {
            System.out.print("Enter a new password: ");
            password = scanner.nextLine();
            if (isValidPassword(password)) {
                break;
            } else {
                System.out.println("Password must be at least 8 characters long and include an uppercase letter, a lowercase letter, a number, and a special character.");
            }
        }
        System.out.print("Enter your full name: ");
        String name = scanner.nextLine();

        double balance = -1;
        while (balance < 0) {
            System.out.print("Enter an initial deposit amount: ");
            try {
                balance = Double.parseDouble(scanner.nextLine());
                if (balance < 0) {
                    System.out.println("Error: Balance cannot be negative. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a numeric value.");
            }
        }

        System.out.print("Choose a security question: ");
        String securityQuestion = scanner.nextLine();
        System.out.print("Enter your answer to the security question: ");
        String securityAnswer = scanner.nextLine();

        if (controller.registerUser(username, password, name, balance, securityQuestion, securityAnswer)) {
            System.out.println("User registered successfully!");
            postRegistrationMenu();
        } else {
            System.out.println("Registration failed. Username might already be taken.");
        }
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*\\d.*") &&
                password.matches(".*[!@#$%^&*()_+\"\\[\\]{}|;:'<>,./?`~-].*");
    }

    private void postRegistrationMenu() {
        while (true) {
            System.out.println("\nWhat would you like to do next?");
            System.out.println("1. Log in with your new account");
            System.out.println("2. Register another user");
            System.out.println("3. Return to the main menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    login();
                    return;
                case 2:
                    registerUser();
                    return;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void dashboard() {
        int choice;
        double amount;
        while (true) {
            System.out.println("\nDashboard");
            System.out.println("1. View Profile");
            System.out.println("2. View Transaction History");
            System.out.println("3. Pay Bill");
            System.out.println("4. Transfer Money");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println(controller.getProfile());
                    break;
                case 2:
                    System.out.println(controller.getTransactionHistory());
                    break;
                case 3:
                    System.out.print("Enter amount: ");
                    amount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter bill details: ");
                    String billDetails = scanner.nextLine();
                    if (controller.payBill(amount, billDetails)) {
                        System.out.println("Bill paid successfully.");
                    } else {
                        System.out.println("Insufficient balance.");
                    }
                    break;
                case 4:
                    System.out.print("Enter recipient username: ");
                    String recipient = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    amount = scanner.nextDouble();
                    scanner.nextLine();
                    if (controller.transferMoney(recipient, amount)) {
                        System.out.println("Money transferred successfully.");
                    } else {
                        System.out.println("Transfer failed. Check balance or recipient details.");
                    }
                    break;
                case 5:
                    controller.logout();
                    System.out.println("Logged out.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}