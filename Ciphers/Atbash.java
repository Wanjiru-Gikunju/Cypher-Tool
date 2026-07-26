package Ciphers;

public class Atbash {

        public static String encrypt(String s) {
            //store encrpted letters
            String result = "";

            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                //Uppercase Letters
                if(c >= 'A' && c <= 'Z') {
                    c = (char) ('Z' - (c - 'A'));
                    //Lowercase Letters
                }else if (c >= 'a' && c <= 'z') {
                    c = (char) ('z' - (c - 'a'));
                }

                result += c;
            }

            return result;
    }

    public static String decrypt(String s) {
        return encrypt(s);
    }

}