public class MonoalphabeticSubstitution {

    // Alphabet used for encryption and decryption
    private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // Constructor (optional, for any instance-level initialization)
    public MonoalphabeticSubstitution() {}

    // Encrypt method implementation
    public String encrypt(String plaintext, int key) {
        plaintext = plaintext.toUpperCase().replaceAll("\\s", ""); // Remove spaces from plaintext
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i++) {
            char currentChar = plaintext.charAt(i);

            if (Character.isLetter(currentChar)) {
                int charPosition = ALPHABET.indexOf(currentChar);
                int newCharPosition = (charPosition + key) % 26;
                encryptedText.append(ALPHABET.charAt(newCharPosition));
            }
        }

        return encryptedText.toString();
    }

    // Decrypt method implementation
    public String decrypt(String ciphertext, int key, String originalPlaintext) {
        StringBuilder decryptedText = new StringBuilder();

        int nonSpaceCharIndex = 0; // To track non-space characters
        for (int i = 0; i < originalPlaintext.length(); i++) {
            char currentChar = originalPlaintext.charAt(i);

            if (Character.isLetter(currentChar)) {
                int ciphertextCharPos = ALPHABET.indexOf(ciphertext.charAt(nonSpaceCharIndex));
                int newCharPosition = (ciphertextCharPos - key + 26) % 26; // Avoid negative indices
                decryptedText.append(ALPHABET.charAt(newCharPosition));
                nonSpaceCharIndex++; // Only increment for letters
            } else {
                decryptedText.append(currentChar); // Add space back
            }
        }

        return decryptedText.toString();
    }
}
