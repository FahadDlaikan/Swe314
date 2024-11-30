
public class Main {
public static void main(String[] args) {
    // Initialize the database
    BankDatabase database = new BankDatabase();

    // Initialize the controller with the database
    BankController controller = new BankController(database);

    // Initialize the CLI (command-line interface) with the controller
    BankCLI cli = new BankCLI(controller);

    // Start the CLI
    cli.start();
 }
}