package util;

import java.util.Scanner;

/**
 * Utility class for console input validation and reading
 * All methods are static for easy access
 */
public class InputHelper {
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * Prompt for string input, re-prompts if blank
     */
    public static String promptString(String label) {
        String input;
        do {
            System.out.print(label);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("⚠ Input cannot be blank. Please try again.");
            }
        } while (input.isEmpty());
        return input;
    }
    
    /**
     * Prompt for integer input, re-prompts on invalid input
     */
    public static int promptInt(String label) {
        while (true) {
            try {
                System.out.print(label);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("⚠ Invalid number. Please enter a valid integer.");
            }
        }
    }
    
    /**
     * Prompt for double input, re-prompts on invalid input
     */
    public static double promptDouble(String label) {
        while (true) {
            try {
                System.out.print(label);
                String input = scanner.nextLine().trim();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("⚠ Invalid number. Please enter a valid amount.");
            }
        }
    }
    
    /**
     * Prompt for 4-digit PIN, validates format
     */
    public static int promptPIN(String label) {
        while (true) {
            System.out.print(label + " (4 digits): ");
            String input = scanner.nextLine().trim();
            
            try {
                int pin = Integer.parseInt(input);
                if (pin >= 1000 && pin <= 9999) {
                    return pin;
                } else {
                    System.out.println("⚠ PIN must be exactly 4 digits (1000-9999).");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠ PIN must contain only digits.");
            }
        }
    }
    
    /**
     * Pause and wait for user to press Enter
     */
    public static void pause() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    /**
     * Clear screen by printing blank lines
     */
    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    
    /**
     * Print separator line
     */
    public static void printSeparator() {
        System.out.println("═".repeat(52));
    }
    
    /**
     * Print boxed header with title
     */
    public static void printHeader(String title) {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.printf("║  %-44s  ║%n", title);
        System.out.println("╚════════════════════════════════════════════════╝");
    }
    
    /**
     * Close scanner (call on program exit)
     */
    public static void closeScanner() {
        scanner.close();
    }
}
