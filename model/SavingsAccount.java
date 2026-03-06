package model;

import exception.InsufficientFundsException;

/**
 * Savings Account with minimum balance requirement
 * Demonstrates INHERITANCE and POLYMORPHISM
 */
public class SavingsAccount extends Account {
    private static final double MINIMUM_BALANCE = 500.0;
    private static final double INTEREST_RATE = 3.5; // 3.5% annual interest
    
    public SavingsAccount(String accountNumber, Customer customer) {
        super(accountNumber, AccountType.SAVINGS, customer);
    }
    
    // Override withdraw - enforce minimum balance (POLYMORPHISM)
    @Override
    public boolean withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        
        // Check if withdrawal would violate minimum balance
        if ((balance - amount) < MINIMUM_BALANCE) {
            logTransaction(amount, TransactionType.WITHDRAWAL, TransactionStatus.FAILED, 
                          "Withdrawal failed - minimum balance requirement");
            throw new InsufficientFundsException(
                "Cannot withdraw. Minimum balance of ₹" + MINIMUM_BALANCE + " must be maintained.", 
                balance
            );
        }
        
        // Perform withdrawal
        balance -= amount;
        logTransaction(amount, TransactionType.WITHDRAWAL, TransactionStatus.SUCCESS, 
                      "Withdrawal of ₹" + String.format("%.2f", amount));
        return true;
    }
    
    // Apply monthly interest
    public void applyMonthlyInterest() {
        double interest = (balance * INTEREST_RATE / 100) / 12; // Monthly interest
        balance += interest;
        logTransaction(interest, TransactionType.DEPOSIT, TransactionStatus.SUCCESS, 
                      "Monthly interest credited @ " + INTEREST_RATE + "%");
    }
    
    public double getMinimumBalance() {
        return MINIMUM_BALANCE;
    }
    
    public double getInterestRate() {
        return INTEREST_RATE;
    }
}
