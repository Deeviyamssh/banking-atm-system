package service;

import model.Account;
import model.Transaction;
import model.TransactionType;
import exception.AccountNotFoundException;
import util.BankDataStore;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for transaction management
 * Demonstrates extensive use of Java Collections, Generics, and Streams
 */
public class TransactionService {
    private BankDataStore dataStore;
    
    public TransactionService() {
        this.dataStore = BankDataStore.getInstance();
    }
    
    /**
     * Get complete transaction history for an account
     * Uses GENERICS: List<Transaction>
     * @param accountNumber Account number
     * @return List of transactions sorted by timestamp (latest first)
     * @throws AccountNotFoundException if account not found
     */
    public List<Transaction> getHistory(String accountNumber) throws AccountNotFoundException {
        Account account = dataStore.findAccountByNumber(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException(accountNumber);
        }
        
        // COLLECTIONS: Using List interface with ArrayList implementation
        // Returns sorted list (latest first) using Comparable implementation
        return account.getTransactionHistory();
    }
    
    /**
     * Get filtered transaction history by transaction type
     * Uses GENERICS: List<Transaction>
     * Uses STREAMS: filter() operation
     * @param accountNumber Account number
     * @param filter Transaction type to filter by
     * @return Filtered list of transactions
     * @throws AccountNotFoundException if account not found
     */
    public List<Transaction> getHistory(String accountNumber, TransactionType filter) 
            throws AccountNotFoundException {
        Account account = dataStore.findAccountByNumber(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException(accountNumber);
        }
        
        // STREAMS: Using Stream API with filter() and collect()
        // LAMBDA EXPRESSION: t -> t.getTransactionType() == filter
        return account.getTransactionHistory()
                     .stream()
                     .filter(t -> t.getTransactionType() == filter)
                     .collect(Collectors.toList());
    }
    
    /**
     * Calculate total deposits for an account
     * Uses STREAMS: mapToDouble() and sum()
     * @param accountNumber Account number
     * @return Total deposit amount
     * @throws AccountNotFoundException if account not found
     */
    public double getTotalDeposits(String accountNumber) throws AccountNotFoundException {
        Account account = dataStore.findAccountByNumber(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException(accountNumber);
        }
        
        // STREAMS: Using mapToDouble() to convert to DoubleStream, then sum()
        // LAMBDA EXPRESSION: Transaction::getAmount (method reference)
        return account.getTransactionHistory()
                     .stream()
                     .filter(t -> t.getTransactionType() == TransactionType.DEPOSIT || 
                                  t.getTransactionType() == TransactionType.TRANSFER_IN)
                     .mapToDouble(Transaction::getAmount)
                     .sum();
    }
    
    /**
     * Calculate total withdrawals for an account
     * Uses STREAMS: mapToDouble() and sum()
     * @param accountNumber Account number
     * @return Total withdrawal amount
     * @throws AccountNotFoundException if account not found
     */
    public double getTotalWithdrawals(String accountNumber) throws AccountNotFoundException {
        Account account = dataStore.findAccountByNumber(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException(accountNumber);
        }
        
        // STREAMS: Using mapToDouble() to convert to DoubleStream, then sum()
        // LAMBDA EXPRESSION: Transaction::getAmount (method reference)
        return account.getTransactionHistory()
                     .stream()
                     .filter(t -> t.getTransactionType() == TransactionType.WITHDRAWAL || 
                                  t.getTransactionType() == TransactionType.TRANSFER_OUT)
                     .mapToDouble(Transaction::getAmount)
                     .sum();
    }
    
    /**
     * Print mini statement showing last 5 transactions
     * Uses STREAMS: limit() operation
     * @param accountNumber Account number
     * @throws AccountNotFoundException if account not found
     */
    public void printMiniStatement(String accountNumber) throws AccountNotFoundException {
        Account account = dataStore.findAccountByNumber(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException(accountNumber);
        }
        
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                           MINI STATEMENT                                   ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════╣");
        System.out.printf("║ Account: %-30s Balance: ₹%15.2f ║%n", 
                         accountNumber, account.getBalance());
        System.out.println("╠════════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║ Transaction ID  │ Type          │ Amount        │ Date & Time            ║");
        System.out.println("╠═════════════════╪═══════════════╪═══════════════╪════════════════════════╣");
        
        // STREAMS: Using limit() to get last 5 transactions
        // COLLECTIONS: List interface with stream operations
        List<Transaction> recentTransactions = account.getTransactionHistory()
                                                      .stream()
                                                      .limit(5)
                                                      .collect(Collectors.toList());
        
        if (recentTransactions.isEmpty()) {
            System.out.println("║                         No transactions found                              ║");
        } else {
            // ENHANCED FOR LOOP: Iterating over Collection
            for (Transaction txn : recentTransactions) {
                String type = String.format("%-13s", txn.getTransactionType());
                String amount = String.format("₹%12.2f", txn.getAmount());
                String dateTime = txn.getTimestamp().toString().substring(0, 19).replace('T', ' ');
                
                System.out.printf("║ %-15s │ %s │ %s │ %-22s ║%n", 
                                txn.getTransactionId(), type, amount, dateTime);
            }
        }
        
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
    }
    
    /**
     * Get transaction count by type
     * Uses STREAMS: filter() and count()
     * @param accountNumber Account number
     * @param type Transaction type
     * @return Count of transactions
     * @throws AccountNotFoundException if account not found
     */
    public long getTransactionCount(String accountNumber, TransactionType type) 
            throws AccountNotFoundException {
        Account account = dataStore.findAccountByNumber(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException(accountNumber);
        }
        
        // STREAMS: Using filter() and count()
        return account.getTransactionHistory()
                     .stream()
                     .filter(t -> t.getTransactionType() == type)
                     .count();
    }
}
