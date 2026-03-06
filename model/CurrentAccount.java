package model;

import exception.InsufficientFundsException;
import exception.DailyLimitExceededException;

/**
 * Current Account with overdraft facility and daily withdrawal limit
 * Demonstrates INHERITANCE and POLYMORPHISM
 */
public class CurrentAccount extends Account {
    private double overdraftLimit;
    private double dailyWithdrawalLimit;
    private double todayWithdrawnAmount;
    
    public CurrentAccount(String accountNumber) {
        super(accountNumber, AccountType.CURRENT);
        this.overdraftLimit = 5000.0;
        this.dailyWithdrawalLimit = 50000.0;
        this.todayWithdrawnAmount = 0.0;
    }
    
    // TODO: Override withdraw - allow overdraft (POLYMORPHISM)
    @Override
    public boolean withdraw(double amount) throws InsufficientFundsException, DailyLimitExceededException {
        // TODO: Check daily limit
        // TODO: Check if (balance - amount) >= -overdraftLimit
        // TODO: If yes, deduct and update todayWithdrawnAmount
        // TODO: If no, throw appropriate exception
        return false;
    }
    
    // TODO: Reset daily withdrawal counter (call this daily)
    public void resetDailyLimit() {
        this.todayWithdrawnAmount = 0.0;
    }
    
    public double getOverdraftLimit() {
        return overdraftLimit;
    }
    
    public double getDailyWithdrawalLimit() {
        return dailyWithdrawalLimit;
    }
    
    public double getTodayWithdrawnAmount() {
        return todayWithdrawnAmount;
    }
}
