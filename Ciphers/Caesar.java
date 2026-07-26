package Ciphers;

public class Caesar {

    // Caesar shifts every letter by a chosen amount. Decrypting just
    // shifts back the other way (negative of the same amount).
    public static String encrypt(String s, int shift) {
        String result = "";

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            //Uppercase Letters
            if (c >= 'A' && c <= 'Z') {
                c = (char) (((c - 'A' + shift) % 26 + 26) % 26 + 'A');
                //Lowercase Letters
            } else if (c >= 'a' && c <= 'z') {
                c = (char) (((c - 'a' + shift) % 26 + 26) % 26 + 'a');
            }

            result = result + c;
        }

        return result;
    }

    public static String decrypt(String s, int shift) {
        return encrypt(s, -shift);
    }
}