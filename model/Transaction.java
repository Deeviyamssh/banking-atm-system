package model;

import java.time.LocalDateTime;

/**
 * Transaction entity to track all account operations
 */
public class Transaction {
    private String transactionId;
    private TransactionType type;
    private double amount;
    private LocalDateTime timestamp;
    private TransactionStatus status;
    private String description;
    private double balanceAfter;
    
    public Transaction(String transactionId, TransactionType type, double amount, 
                      TransactionStatus status, String description, double balanceAfter) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.description = description;
        this.balanceAfter = balanceAfter;
    }
    
    // TODO: Getters
    public String getTransactionId() {
        return transactionId;
    }
    
    public TransactionType getType() {
        return type;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public TransactionStatus getStatus() {
        return status;
    }
    
    public String getDescription() {
        return description;
    }
    
    public double getBalanceAfter() {
        return balanceAfter;
    }
}
