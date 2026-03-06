package service;

import model.Account;
import model.Transaction;
import model.TransactionType;
import model.TransactionStatus;
import util.BankDataStore;
import java.util.List;

/**
 * Service for transaction management
 */
public class TransactionService {
    private BankDataStore dataStore;
    
    public TransactionService() {
        this.dataStore = BankDataStore.getInstance();
    }
    
    // TODO: Create and record transaction
    public Transaction createTransaction(Account account, TransactionType type, 
                                        double amount, TransactionStatus status, 
                                        String description) {
        // TODO: Generate transaction ID
        // TODO: Create Transaction object
        // TODO: Add to account's transaction list
        // TODO: Return transaction
        return null;
    }
    
    // TODO: Get transaction history for account
    public List<Transaction> getTransactionHistory(Account account) {
        // TODO: Return account's transaction list
        return null;
    }
    
    // TODO: Get last N transactions
    public List<Transaction> getRecentTransactions(Account account, int count) {
        // TODO: Return last N transactions
        return null;
    }
}
