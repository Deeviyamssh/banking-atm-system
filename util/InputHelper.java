package util;

import java.util.Scanner;

/**
 * Utility class for console input validation and reading
 * All methods are static for easy access
 * HARDENED: Never throws exceptions, always re-prompts on invalid input
 */
public class InputHelper {
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * Prompt for string input, re-prompts if blank
     * NEVER throws exception
     */
    public static String promptString(String label) {
        String input;
        do {
            try {
                System.out.print(label);
                input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("⚠ Input cannot be blank. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("⚠ Error reading input. Please try again.");
                input = "";
            }
        } while (input.isEmpty());
        return input;
    }
    
    /**
     * Prompt for integer input, re-prompts on invalid input
     * NEVER throws exception - loops forever until valid integer
     */
    public static int promptInt(String label) {
        while (true) {
            try {
                System.out.print(label);
                String input = scanner.nextLine().trim();
                
                if (input.isEmpty()) {
                    System.out.println("⚠ Input cannot be blank. Please enter a number.");
                    continue;
                }
                
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("⚠ Please enter numbers only.");
            } catch (Exception e) {
                System.out.println("⚠ Error reading input. Please try again.");
            }
        }
    }
    
    /**
     * Prompt for double input, re-prompts on invalid input
     * NEVER throws exception - loops forever until valid double
     */
    public static double promptDouble(String label) {
        while (true) {
            try {
                System.out.print(label);
                String input = scanner.nextLine().trim();
                
                if (input.isEmpty()) {
                    System.out.println("⚠ Input cannot be blank. Please enter a number.");
                    continue;
                }
                
                double value = Double.parseDouble(input);
                
                // Check for negative values
                if (value < 0) {
                    System.out.println("⚠ Amount cannot be negative. Please enter a positive number.");
                    continue;
                }
                
                return value;
            } catch (NumberFormatException e) {
                System.out.println("⚠ Please enter numbers only.");
            } catch (Exception e) {
                System.out.println("⚠ Error reading input. Please try again.");
            }
        }
    }
    
    /**
     * Prompt for 4-digit PIN, validates format
     * NEVER throws exception - rejects anything not exactly 4 digits
     */
    public static int promptPIN(String label) {
        while (true) {
            try {
                System.out.print(label + " (4 digits): ");
                String input = scanner.nextLine().trim();
                
                if (input.isEmpty()) {
                    System.out.println("⚠ PIN cannot be blank. Please enter a 4-digit PIN.");
                    continue;
                }
                
                // Check if input contains only digits
                if (!input.matches("\\d+")) {
                    System.out.println("⚠ Please enter numbers only.");
                    continue;
                }
                
                int pin = Integer.parseInt(input);
                
                // Reject anything not exactly 4 digits (< 1000 or > 9999)
                if (pin < 1000 || pin > 9999) {
                    System.out.println("⚠ PIN must be exactly 4 digits (1000-9999).");
                    continue;
                }
                
                return pin;
            } catch (NumberFormatException e) {
                System.out.println("⚠ Please enter numbers only.");
            } catch (Exception e) {
                System.out.println("⚠ Error reading input. Please try again.");
            }
        }
    }
    
    /**
     * Pause and wait for user to press Enter
     */
    public static void pause() {
        try {
            System.out.print("\nPress Enter to continue...");
            scanner.nextLine();
        } catch (Exception e) {
            // Silently handle any error
        }
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
        try {
            scanner.close();
        } catch (Exception e) {
            // Silently handle any error
        }
    }
}
