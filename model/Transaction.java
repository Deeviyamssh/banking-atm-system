package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Transaction entity to track all account operations
 * Implements Comparable for sorting by timestamp
 */
public class Transaction implements Comparable<Transaction> {
    private String transactionId;
    private String accountNumber;
    private double amount;
    private TransactionType transactionType;
    private TransactionStatus status;
    private LocalDateTime timestamp;
    private String description;
    
    // All-args constructor
    public Transaction(String transactionId, String accountNumber, double amount, 
                      TransactionType transactionType, TransactionStatus status, 
                      LocalDateTime timestamp, String description) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.transactionType = transactionType;
        this.status = status;
        this.timestamp = timestamp;
        this.description = description;
    }
    
    // Getters
    public String getTransactionId() {
        return transactionId;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public TransactionType getTransactionType() {
        return transactionType;
    }
    
    public TransactionStatus getStatus() {
        return status;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public String getDescription() {
        return description;
    }
    
    // toString formatted as: [TXN123456] DEPOSIT ₹5000.00 on 2024-03-06 10:30 — SUCCESS
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("[%s] %s ₹%.2f on %s — %s", 
                           transactionId, 
                           transactionType, 
                           amount, 
                           timestamp.format(formatter), 
                           status);
    }
    
    // Comparable implementation - sort by timestamp descending (latest first)
    @Override
    public int compareTo(Transaction other) {
        return other.timestamp.compareTo(this.timestamp); // Reverse order for descending
    }
}
