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
            System.out.print("Choice: ");

            String choice = input.nextLine().trim();
              // check if user entered "exit" to terminate the program and makes it not  case sensitive and closes thr program
            if (choice.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                input.close();
                return;
            }

             // check if the user entered a number and if it is 1 or 2, then break the loop and go to the cipher menu
            if (isNumber(choice)) {
                operation = Integer.parseInt(choice);


                if (operation == 1 || operation == 2) {
                    break;      // Go to cipher menu
                }
            }
                // tells the user that they entered an invalid operation and to try again and prompts the user to enter a valid operation
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

            //checks if user wants to quit at this stage and closes the program if they do, also makes it not case sensitive
            if (choice.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                input.close();
                return;
            }
            // checks if the user entered a number and if it is 1, 2, or 3, then break the loop and go to the message (or shift, for Caesar)
            if (isNumber(choice) && Integer.parseInt(choice) >= 1 && Integer.parseInt(choice) <= 3) {
                cipher = Integer.parseInt(choice);
                break;      // Go to message (or shift, for Caesar)
            }

            System.out.println("Invalid cipher. Please try again.");
        }

        // SHIFT (Caesar only)
        if (cipher == 3) {
            while (true) {
                // prompt the user to enter a shift value for the Caesar cipher and checks if it is a whole number, if not it prompts the user to enter a valid shift value
                System.out.print("\nEnter shift value (whole number): ");
                String shiftChoice = input.nextLine().trim();

                //checks if user wants to quit at this stage and closes the program if they do, also makes it not case sensitive
                if (shiftChoice.equalsIgnoreCase("exit")) {
                    System.out.println("Goodbye!");
                    input.close();
                    return;
                }
                // checks if the user entered a number and if it is a whole number, then break the loop and go to the message
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
            // prompt the user to enter a message to encrypt or decrypt and checks if it is not empty, if it is empty it prompts the user to enter a valid message
            System.out.print("\nEnter your message: ");
            message = input.nextLine().trim();

            //  checks if user wants to quit at this stage and closes the program if they do, also makes it not case sensitive
            if (message.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                input.close();
                return;
            }

            // checks if the message is empty, if it is empty it prompts the user to enter a valid message
            if (message.length() == 0) {
                System.out.println("Message cannot be empty. Please try again.");
                continue;
            }

            break;      // Got a valid, non-empty, trimmed message
        }

        // APPLY CIPHER
        String result = "";
        String cipherName = "";

        // checks which cipher the user selected and applies the appropriate encryption or decryption method based on the user's choice of operation
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

    // Checks that a string is made up only of digits (so we can turn it into a number).
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