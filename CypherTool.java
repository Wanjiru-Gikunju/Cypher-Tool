import java.util.Scanner;
import Ciphers.ROT13;
import Ciphers.Atbash;
import Ciphers.Caesar;

public class CypherTool {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int operation = 0;
        int cipher = 0;
        int shift = 0;

        System.out.println("==============================");
        System.out.println("Welcome to Cypher Tool");
        System.out.println("==============================");

        // OPERATION MENU
        while (true) {

            System.out.println("\nSelect Operation:");
            System.out.println("1. Encrypt");
            System.out.println("2. Decrypt");
            System.out.println("3. Exit");
            System.out.print("Choice: ");

            String choice = input.nextLine().trim();

            if (choice.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                input.close();
                return;
            }

            if (isNumber(choice)) {
                operation = Integer.parseInt(choice);

                if (operation == 3) {
                    System.out.println("Goodbye!");
                    input.close();
                    return;
                }

                if (operation == 1 || operation == 2) {
                    break;      // Go to cipher menu
                }
            }

            System.out.println("Invalid operation. Please try again.");
        }


        // CIPHER MENU
        while (true) {

            System.out.println("\nSelect Cipher:");
            System.out.println("1. ROT13");
            System.out.println("2. Atbash");
            System.out.println("3. Caesar");
            System.out.print("Choice: ");

            String choice = input.nextLine().trim();

            if (choice.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                input.close();
                return;
            }

            if (isNumber(choice) && Integer.parseInt(choice) >= 1 && Integer.parseInt(choice) <= 3) {
                cipher = Integer.parseInt(choice);
                break;      // Go to message (or shift, for Caesar)
            }

            System.out.println("Invalid cipher. Please try again.");
        }

        // SHIFT (Caesar only)
        if (cipher == 3) {
            while (true) {

                System.out.print("\nEnter shift value (whole number): ");
                String shiftChoice = input.nextLine().trim();

                if (shiftChoice.equalsIgnoreCase("exit")) {
                    System.out.println("Goodbye!");
                    input.close();
                    return;
                }

                if (isNumber(shiftChoice)) {
                    shift = Integer.parseInt(shiftChoice);
                    break;
                }

                System.out.println("Invalid shift value. Please enter a whole number.");
            }
        }

        // MESSAGE
        String message;
        while (true) {

            System.out.print("\nEnter your message: ");
            message = input.nextLine().trim();

            if (message.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                input.close();
                return;
            }

            if (message.length() == 0) {
                System.out.println("Message cannot be empty. Please try again.");
                continue;
            }

            break;      // Got a valid, non-empty, trimmed message
        }

        // APPLY CIPHER
        String result = "";
        String cipherName = "";

        if (cipher == 1) {
            cipherName = "ROT13";
            if (operation == 1) {
                result = ROT13.encrypt(message);
            } else {
                result = ROT13.decrypt(message);
            }
        } else if (cipher == 2) {
            cipherName = "Atbash";
            if (operation == 1) {
                result = Atbash.encrypt(message);
            } else {
                result = Atbash.decrypt(message);
            }
        } else {
            cipherName = "Caesar";
            if (operation == 1) {
                result = Caesar.encrypt(message, shift);
            } else {
                result = Caesar.decrypt(message, shift);
            }
        }

        if (operation == 1) {
            System.out.println("\nEncrypted message (" + cipherName + "):");
        } else {
            System.out.println("\nDecrypted message (" + cipherName + "):");
        }
        System.out.println(result);

        input.close();
    }

    // Checks that a string is made up only of digits (so we can safely
    // turn it into a number without needing try/catch).
    public static boolean isNumber(String s) {
        if (s.length() == 0) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }

        return true;
    }
}