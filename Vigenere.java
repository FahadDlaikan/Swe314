public class Vigenere  {

    // Alphabet used for encryption and decryption
    private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // Constructor (optional, for any instance-level initialization)
    public Vigenere() {}

    // Method to repeat the key to match the length of the plaintext/ciphertext
    private String repeatKey(String text, String key) {
        StringBuilder repeatedKey = new StringBuilder();
        key = key.toUpperCase();
        int keyIndex = 0;

        for (int i = 0; i < text.length(); i++) {
            if (Character.isLetter(text.charAt(i))) {
                repeatedKey.append(key.charAt(keyIndex));
                keyIndex = (keyIndex + 1) % key.length();
            }
        }

        return repeatedKey.toString();
    }

    // Encrypt method implementation
   
    public String encrypt(String plaintext, String key) {
        plaintext = plaintext.toUpperCase();
        String repeatedKey = repeatKey(plaintext.replaceAll("\\s", ""), key);
        StringBuilder encryptedText = new StringBuilder();

        int nonSpaceCharIndex = 0; // To track non-space characters
        for (int i = 0; i < plaintext.length(); i++) {
            char currentChar = plaintext.charAt(i);

            if (Character.isLetter(currentChar)) {
                int plaintextCharPos = ALPHABET.indexOf(currentChar);
                int keyCharPos = ALPHABET.indexOf(repeatedKey.charAt(nonSpaceCharIndex));
                int newCharPosition = (plaintextCharPos + keyCharPos) % 26;
                encryptedText.append(ALPHABET.charAt(newCharPosition));
                nonSpaceCharIndex++; // Only increment for letters
            }
        }

        return encryptedText.toString();
    }

    // Decrypt method implementation
    
    public String decrypt(String ciphertext, String key, String originalPlaintext) {
        String repeatedKey = repeatKey(ciphertext, key);
        StringBuilder decryptedText = new StringBuilder();

        int nonSpaceCharIndex = 0; // To track non-space characters
        for (int i = 0; i < originalPlaintext.length(); i++) {
            char currentChar = originalPlaintext.charAt(i);

            if (Character.isLetter(currentChar)) {
                int ciphertextCharPos = ALPHABET.indexOf(ciphertext.charAt(nonSpaceCharIndex));
                int keyCharPos = ALPHABET.indexOf(repeatedKey.charAt(nonSpaceCharIndex));
                int newCharPosition = (ciphertextCharPos - keyCharPos + 26) % 26; // Avoid negative values
                decryptedText.append(ALPHABET.charAt(newCharPosition));
                nonSpaceCharIndex++; // Only increment for letters
            } else {
                decryptedText.append(currentChar); // Add space back
            }
        }

        return decryptedText.toString();
    }
}
