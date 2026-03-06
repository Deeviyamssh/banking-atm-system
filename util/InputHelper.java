package util;

import java.util.Scanner;

/**
 * Utility class for console input validation and reading
 */
public class InputHelper {
    private static Scanner scanner = new Scanner(System.in);
    
    // TODO: Read integer with validation
    public static int readInt(String prompt) {
        // TODO: Display prompt, read int, handle InputMismatchException
        return 0;
    }
    
    // TODO: Read double with validation
    public static double readDouble(String prompt) {
        // TODO: Display prompt, read double, handle InputMismatchException
        return 0.0;
    }
    
    // TODO: Read string
    public static String readString(String prompt) {
        // TODO: Display prompt and read line
        return null;
    }
    
    // TODO: Read 4-digit PIN with validation
    public static String readPIN(String prompt) {
        // TODO: Validate PIN is exactly 4 digits
        return null;
    }
    
    // TODO: Read email with basic validation
    public static String readEmail(String prompt) {
        // TODO: Basic email format validation
        return null;
    }
    
    // TODO: Read phone number with validation
    public static String readPhoneNumber(String prompt) {
        // TODO: Validate 10 digits
        return null;
    }
    
    // TODO: Close scanner
    public static void closeScanner() {
        scanner.close();
    }
}
