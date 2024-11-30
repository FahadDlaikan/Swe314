
// BankCLI.java
import java.util.Scanner;

public class BankCLI {
    private BankController controller;
    private Scanner scanner;

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
                case 1 :
                    login();
                case 2 :
                    registerUser();
                case 3 :
                {
                    System.out.println("Goodbye!");
                    return;
                }
                default :
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (controller.login(username, password)) {
            System.out.println("Login successful!");
            dashboard();
        } else {
            System.out.println("Invalid credentials.");
        }
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
        scanner.nextLine();

        if (controller.registerUser(username, password, name, balance)) {
            System.out.println("User registered successfully! You can now log in.");
        } else {
            System.out.println("Registration failed. Username might already be taken.");
        }
    }

    private void dashboard() {
        while (true) {
            System.out.println("\nDashboard");
            System.out.println("1. View Profile");
            System.out.println("2. View Transaction History");
            System.out.println("3. Pay Bill");
            System.out.println("4. Transfer Money");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 :
                    System.out.println(controller.getProfile());
                case 2 :
                    System.out.println(controller.getTransactionHistory());
                case 3 :
                {
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter bill details: ");
                    String billDetails = scanner.nextLine();
                    if (controller.payBill(amount, billDetails)) {
                        System.out.println("Bill paid successfully.");
                    } else {
                        System.out.println("Insufficient balance.");
                    }
                }
                case 4 :
                {
                    System.out.print("Enter recipient username: ");
                    String recipient = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    if (controller.transferMoney(recipient, amount)) {
                        System.out.println("Money transferred successfully.");
                    } else {
                        System.out.println("Transfer failed. Check balance or recipient details.");
                    }
                }
                case 5 :
                {
                    controller.logout();
                    System.out.println("Logged out.");
                    return;
                }
                default :
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

