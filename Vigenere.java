public class Vigenere {
    private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // Encrypt method
    public String encrypt(String plaintext, String key) {
        StringBuilder encryptedText = new StringBuilder();
        int keyIndex = 0;

        plaintext = plaintext.toUpperCase(); // Convert plaintext to uppercase
        key = key.toUpperCase(); // Convert key to uppercase

        for (int i = 0; i < plaintext.length(); i++) {
            char currentChar = plaintext.charAt(i);

            if (Character.isLetter(currentChar)) {
                char keyChar = key.charAt(keyIndex % key.length());
                int textCharPos = ALPHABET.indexOf(currentChar);
                int keyCharPos = ALPHABET.indexOf(keyChar);
                int encryptedCharPos = (textCharPos + keyCharPos) % 26;
                char encryptedChar = ALPHABET.charAt(encryptedCharPos);
                encryptedText.append(encryptedChar);
                keyIndex++;
            } else {
                encryptedText.append(currentChar); // Non-letter characters are appended directly
            }
        }

        return encryptedText.toString();
    }

    // Decrypt method
    public String decrypt(String ciphertext, String key) {
        StringBuilder decryptedText = new StringBuilder();
        int keyIndex = 0;

        ciphertext = ciphertext.toUpperCase(); // Convert ciphertext to uppercase
        key = key.toUpperCase(); // Convert key to uppercase

        for (int i = 0; i < ciphertext.length(); i++) {
            char currentChar = ciphertext.charAt(i);

            if (Character.isLetter(currentChar)) {
                char keyChar = key.charAt(keyIndex % key.length());
                int cipherCharPos = ALPHABET.indexOf(currentChar);
                int keyCharPos = ALPHABET.indexOf(keyChar);
                int decryptedCharPos = (cipherCharPos - keyCharPos + 26) % 26; // Wrap around alphabet
                char decryptedChar = ALPHABET.charAt(decryptedCharPos);
                decryptedText.append(decryptedChar);
                keyIndex++;
            } else {
                decryptedText.append(currentChar); // Non-letter characters are appended directly
            }
        }

        return decryptedText.toString();
    }
}