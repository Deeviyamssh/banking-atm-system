package util;

import model.Transaction;
import model.Account;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

/**
 * Utility class for formatting output
 * Provides Indian number format and consistent date/time formatting
 */
public class Formatter {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = 
        DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm a");
    
    private static final DateTimeFormatter DATE_FORMATTER = 
        DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    
    // Indian number format with lakhs and crores
    private static final DecimalFormat INDIAN_FORMAT;
    
    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("en", "IN"));
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');
        INDIAN_FORMAT = new DecimalFormat("##,##,##0.00", symbols);
    }
    
    /**
     * Format amount in Indian currency format
     * @param amount Amount to format
     * @return Formatted string like "₹1,23,456.00"
     */
    public static String formatAmount(double amount) {
        return "₹" + INDIAN_FORMAT.format(amount);
    }
    
    /**
     * Format date-time in readable format
     * @param dateTime LocalDateTime to format
     * @return Formatted string like "06-Mar-2024 10:30 AM"
     */
    public static String formatDate(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "N/A";
        }
        return dateTime.format(DATE_TIME_FORMATTER);
    }
    
    /**
     * Format date only (no time)
     * @param dateTime LocalDateTime to format
     * @return Formatted string like "06-Mar-2024"
     */
    public static String formatDateOnly(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "N/A";
        }
        return dateTime.format(DATE_FORMATTER);
    }
    
    /**
     * Mask account number for security
     * @param accountNumber Full account number
     * @return Masked string like "ACC****34"
     */
    public static String maskAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.length() < 4) {
            return accountNumber;
        }
        String prefix = accountNumber.substring(0, 3);
        String lastTwo = accountNumber.substring(accountNumber.length() - 2);
        return prefix + "****" + lastTwo;
    }
    
    /**
     * Print separator line
     */
    public static void printSeparator() {
        System.out.println("═".repeat(60));
    }
    
    /**
     * Print thin separator line
     */
    public static void printThinSeparator() {
        System.out.println("─".repeat(60));
    }
}
