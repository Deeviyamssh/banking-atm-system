package model;

import exception.InsufficientFundsException;
import exception.DailyLimitExceededException;

/**
 * Current Account with overdraft facility and daily withdrawal limit
 * Demonstrates INHERITANCE and POLYMORPHISM
 */
public class CurrentAccount extends Account {
    private static final double OVERDRAFT_LIMIT = 10000.0;
    private static final double DAILY_WITHDRAWAL_LIMIT = 50000.0;
    private double todayWithdrawn;
    
    public CurrentAccount(String accountNumber, Customer customer) {
        super(accountNumber, AccountType.CURRENT, customer);
        this.todayWithdrawn = 0.0;
    }
    
    // Override withdraw - allow overdraft (POLYMORPHISM)
    @Override
    public boolean withdraw(double amount) throws InsufficientFundsException, DailyLimitExceededException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        
        // Check daily withdrawal limit
        if (todayWithdrawn + amount > DAILY_WITHDRAWAL_LIMIT) {
            logTransaction(amount, TransactionType.WITHDRAWAL, TransactionStatus.FAILED, 
                          "Withdrawal failed - daily limit exceeded");
            throw new DailyLimitExceededException(DAILY_WITHDRAWAL_LIMIT, todayWithdrawn + amount);
        }
        
        // Check if withdrawal exceeds overdraft limit
        if ((balance - amount) < -OVERDRAFT_LIMIT) {
            logTransaction(amount, TransactionType.WITHDRAWAL, TransactionStatus.FAILED, 
                          "Withdrawal failed - overdraft limit exceeded");
            throw new InsufficientFundsException(
                "Cannot withdraw. Overdraft limit of ₹" + String.format("%,.2f", OVERDRAFT_LIMIT) + " exceeded.", 
                balance + OVERDRAFT_LIMIT,
                amount
            );
        }
        
        // Perform withdrawal
        balance -= amount;
        todayWithdrawn += amount;
        logTransaction(amount, TransactionType.WITHDRAWAL, TransactionStatus.SUCCESS, 
                      "Withdrawal of ₹" + String.format("%.2f", amount));
        return true;
    }
    
    // Reset daily withdrawal counter (call this daily)
    public void resetDailyLimit() {
        this.todayWithdrawn = 0.0;
    }
    
    public double getOverdraftLimit() {
        return OVERDRAFT_LIMIT;
    }
    
    public double getDailyWithdrawalLimit() {
        return DAILY_WITHDRAWAL_LIMIT;
    }
    
    public double getTodayWithdrawnAmount() {
        return todayWithdrawn;
    }
}
