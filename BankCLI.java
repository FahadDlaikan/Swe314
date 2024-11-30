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
                    break; // Add break to avoid fall-through
                case 2:
                    registerUser();
                    break; // Add break to avoid fall-through
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
                return; // Exit the loop after successful login
            } else {
                attempts++;
                System.out.println("Invalid credentials. You have " + (3 - attempts) + " attempt(s) remaining.");
            }
        }

        System.out.println("Too many failed attempts. Returning to the main menu.");
    }

    private void registerUser() {
        System.out.print("Enter a new username: ");
        String username = scanner.nextLine();
        System.out.print("Enter a new password: ");
        String password = scanner.nextLine();
        System.out.print("Enter your full name: ");
        String name = scanner.nextLine();
        System.out.print("Enter an initial deposit amount: ");
        double balance = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        if (controller.registerUser(username, password, name, balance)) {
            System.out.println("User registered successfully!");
            postRegistrationMenu();
        } else {
            System.out.println("Registration failed. Username might already be taken.");
        }
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
                    return; // Exit the loop after login

                case 2:
                    registerUser();
                    return; // Exit the loop to avoid nested menus

                case 3:
                    return; // Exit the loop and return to the main menu

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
                    break; // Add break to avoid fall-through
                case 2:
                    System.out.println(controller.getTransactionHistory());
                    break; // Add break to avoid fall-through
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
                    break; // Add break to avoid fall-through
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
                    break; // Add break to avoid fall-through
                case 5:
                    controller.logout();
                    System.out.println("Logged out.");
                    return; // Exit the loop after logout
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
