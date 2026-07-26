package Ciphers;

public class ROT13 {

    public static String encrypt(String s) {
        String result = "";

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            //Uppercase Letters
            if (c >= 'A' && c <= 'Z') {
                c = (char) (((c - 'A' +13) % 26) + 'A');
            }else if (c >= 'a' && c <= 'z') {
                c = (char) (((c - 'a' +13) % 26) + 'a');
        }
        result += c;
    }
    return result;
 }
 
    public static String decrypt(String s) {
        return encrypt(s);
    }

}