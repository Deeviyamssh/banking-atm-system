package service;

import model.Account;
import model.Transaction;
import model.TransactionType;
import model.TransactionStatus;
import exception.InsufficientFundsException;
import exception.AccountNotFoundException;
import util.BankDataStore;

/**
 * Service for account operations (deposit, withdraw, balance inquiry)
 */
public class AccountService {
    private BankDataStore dataStore;
    private TransactionService transactionService;
    
    public AccountService() {
        this.dataStore = BankDataStore.getInstance();
        this.transactionService = new TransactionService();
    }
    
    // TODO: Deposit money
    public void deposit(Account account, double amount) {
        // TODO: Validate amount > 0
        // TODO: Call account.deposit()
        // TODO: Create transaction record
        // TODO: Display success message
    }
    
    // TODO: Withdraw money
    public void withdraw(Account account, double amount) throws Exception {
        // TODO: Validate amount > 0
        // TODO: Call account.withdraw() - polymorphic behavior
        // TODO: Create transaction record
        // TODO: Handle exceptions
    }
    
    // TODO: Check balance
    public double checkBalance(Account account) {
        // TODO: Return account balance
        return 0.0;
    }
    
    // TODO: Transfer funds between accounts
    public void transfer(Account fromAccount, String toAccountNumber, double amount) 
            throws Exception {
        // TODO: Find destination account
        // TODO: Validate amount
        // TODO: Withdraw from source
        // TODO: Deposit to destination
        // TODO: Create transaction records for both accounts
    }
}
