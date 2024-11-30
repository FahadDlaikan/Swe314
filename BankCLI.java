
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
            System.out.println("Welcome to the Bank System");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                login();
            } else if (choice == 2) {
                System.out.println("Goodbye!");
                break;
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

    private void dashboard() {
        while (true) {
            System.out.println("1. View Profile");
            System.out.println("2. View Transaction History");
            System.out.println("3. Pay Bill");
            System.out.println("4. Transfer Money");
            System.out.println("5. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> System.out.println(controller.getProfile());
                case 2 -> System.out.println(controller.getTransactionHistory());
                case 3 -> {
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
                case 4 -> {
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
                case 5 -> {
                    controller.logout();
                    System.out.println("Logged out.");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    public static void main(String[] args) {
        BankDatabase database = new BankDatabase();
        BankController controller = new BankController(database);
        BankCLI cli = new BankCLI(controller);
        cli.start();
    }
}
