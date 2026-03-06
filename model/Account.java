package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class for all account types
 * Demonstrates ABSTRACTION and INHERITANCE
 */
public abstract class Account {
    protected String accountNumber;
    protected double balance;
    protected AccountType accountType;
    protected LocalDateTime createdDate;
    protected List<Transaction> transactions;
    
    public Account(String accountNumber, AccountType accountType) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = 0.0;
        this.createdDate = LocalDateTime.now();
        this.transactions = new ArrayList<>();
    }
    
    // TODO: Abstract method - must be overridden by subclasses (POLYMORPHISM)
    public abstract boolean withdraw(double amount) throws Exception;
    
    // TODO: Implement deposit logic
    public void deposit(double amount) {
        // TODO: Add validation and update balance
    }
    
    // TODO: Add transaction to history
    public void addTransaction(Transaction transaction) {
        // TODO: Add to transactions list
    }
    
    // TODO: Getters and setters
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public AccountType getAccountType() {
        return accountType;
    }
    
    public List<Transaction> getTransactions() {
        return transactions;
    }
    
    protected void setBalance(double balance) {
        this.balance = balance;
    }
}
