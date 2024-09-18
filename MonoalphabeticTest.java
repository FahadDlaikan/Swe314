import java.util.Scanner;

public class MonoalphabeticTest {

    public static void main(String[] args) {
        // Create an instance of MonoalphabeticSubstitution
        MonoalphabeticSubstitution cipher = new MonoalphabeticSubstitution();
        Scanner input = new Scanner(System.in);

        // Test data
        System.out.println("Enter plain text :");
        String plaintext = input.nextLine();
        System.out.println("Enter key :");
        int key = input.nextInt();

        // Encrypt the plaintext
        String encryptedText = cipher.encrypt(plaintext, key);
        System.out.println("Encrypted Text: " + encryptedText);

        // Decrypt the ciphertext
        String decryptedText = cipher.decrypt(encryptedText, key, plaintext);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
