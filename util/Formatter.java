package util;

import model.Transaction;
import model.Account;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Utility class for formatting output
 */
public class Formatter {
    private static final DateTimeFormatter DATE_FORMATTER = 
        DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
    
    // TODO: Format currency
    public static String formatCurrency(double amount) {
        // TODO: Format as ₹ X,XXX.XX
        return String.format("₹ %.2f", amount);
    }
    
    // TODO: Print account details
    public static void printAccountDetails(Account account) {
        // TODO: Display account number, type, balance
    }
    
    // TODO: Print transaction history
    public static void printTransactionHistory(List<Transaction> transactions) {
        // TODO: Display all transactions in tabular format
    }
    
    // TODO: Print single transaction
    public static void printTransaction(Transaction transaction) {
        // TODO: Display transaction details
    }
    
    // TODO: Print separator line
    public static void printSeparator() {
        System.out.println("=".repeat(60));
    }
    
    // TODO: Format date-time
    public static String formatDateTime(java.time.LocalDateTime dateTime) {
        return dateTime.format(DATE_FORMATTER);
    }
}
