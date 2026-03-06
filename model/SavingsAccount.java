package model;

import exception.InsufficientFundsException;

/**
 * Savings Account with minimum balance requirement
 * Demonstrates INHERITANCE and POLYMORPHISM
 */
public class SavingsAccount extends Account {
    private static final double MINIMUM_BALANCE = 500.0;
    private double interestRate;
    
    public SavingsAccount(String accountNumber) {
        super(accountNumber, AccountType.SAVINGS);
        this.interestRate = 4.5; // 4.5% annual interest
    }
    
    // TODO: Override withdraw - enforce minimum balance (POLYMORPHISM)
    @Override
    public boolean withdraw(double amount) throws InsufficientFundsException {
        // TODO: Check if (balance - amount) >= MINIMUM_BALANCE
        // TODO: If yes, deduct and return true
        // TODO: If no, throw InsufficientFundsException
        return false;
    }
    
    // TODO: Calculate and add interest
    public void applyInterest() {
        // TODO: Calculate interest and add to balance
    }
    
    public double getMinimumBalance() {
        return MINIMUM_BALANCE;
    }
    
    public double getInterestRate() {
        return interestRate;
    }
}
