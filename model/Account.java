package model;

import exception.InsufficientFundsException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract base class for all account types
 * Demonstrates ABSTRACTION and INHERITANCE
 */
public abstract class Account {
    protected String accountNumber;
    protected double balance;
    protected AccountType accountType;
    protected Customer customer;
    protected List<Transaction> transactions;
    protected LocalDate createdAt;
    protected boolean isActive;
    
    public Account(String accountNumber, AccountType accountType, Customer customer) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.customer = customer;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
        this.createdAt = LocalDate.now();
        this.isActive = true;
    }
    
    // Abstract method - must be overridden by subclasses (POLYMORPHISM)
    public abstract boolean withdraw(double amount) throws InsufficientFundsException;
    
    // Concrete method: deposit
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
        logTransaction(amount, TransactionType.DEPOSIT, TransactionStatus.SUCCESS, 
                      "Deposit of ₹" + String.format("%.2f", amount));
    }
    
    // Get transaction history sorted by timestamp (latest first)
    public List<Transaction> getTransactionHistory() {
        List<Transaction> sortedTransactions = new ArrayList<>(transactions);
        Collections.sort(sortedTransactions);
        return sortedTransactions;
    }
    
    // Protected method to log transactions
    protected void logTransaction(double amount, TransactionType type, 
                                 TransactionStatus status, String description) {
        String txnId = "TXN" + System.currentTimeMillis();
        Transaction transaction = new Transaction(
            txnId, 
            accountNumber, 
            amount, 
            type, 
            status, 
            LocalDateTime.now(), 
            description
        );
        transactions.add(transaction);
    }
    
    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public AccountType getAccountType() {
        return accountType;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public List<Transaction> getTransactions() {
        return transactions;
    }
    
    public LocalDate getCreatedAt() {
        return createdAt;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean active) {
        isActive = active;
    }
    
    protected void setBalance(double balance) {
        this.balance = balance;
    }
    
    // toString showing account number, type, balance formatted to 2 decimal places
    @Override
    public String toString() {
        return String.format("Account{accountNumber='%s', type=%s, balance=₹%.2f}", 
                           accountNumber, accountType, balance);
    }
}
