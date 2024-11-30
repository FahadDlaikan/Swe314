
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

//    public static void main(String[] args) {
//        Vigenere vigenereCipher = new Vigenere();
//        String plaintext = "Hello World!";
//        String key = "KEY";
//
//        // Encrypt
//        String encrypted = vigenereCipher.encrypt(plaintext, key);
//        System.out.println("Encrypted: " + encrypted);
//
//        // Decrypt
//        String decrypted = vigenereCipher.decrypt(encrypted, key);
//        System.out.println("Decrypted: " + decrypted);
//
//        // Assert
//        System.out.println("Decryption Successful: " + plaintext.equalsIgnoreCase(decrypted));
//    }
//}


//    public static void main(String[] args) {
//        Vigenere vigenereCipher = new Vigenere();
//
//        String plaintext = "Hello World!";
//        String key = "KEY";
//
//        // Encrypt the plaintext
//        String encryptedText = vigenereCipher.encrypt(plaintext, key);
//        System.out.println("Encrypted Text: " + encryptedText);
//
//        // Decrypt the ciphertext
//        String decryptedText = vigenereCipher.decrypt(encryptedText, key);
//        System.out.println("Decrypted Text: " + decryptedText);
//
//        // Compare the decrypted text with the original plaintext (ignoring case)
//        if (plaintext.equalsIgnoreCase(decryptedText)) {
//            System.out.println("Decryption successful!");
//        } else {
//            System.out.println("Decryption failed.");
//        }
//    }



