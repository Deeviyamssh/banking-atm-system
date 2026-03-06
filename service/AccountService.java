package service;

import model.*;
import exception.InsufficientFundsException;
import exception.AccountNotFoundException;
import util.BankDataStore;

/**
 * Service for account operations (registration, deposit, withdraw, transfer)
 * Enforces business rules and validation
 */
public class AccountService {
    private static final double MIN_DEPOSIT = 1.0;
    private static final double MAX_DEPOSIT = 100000.0;
    private static final double MIN_WITHDRAWAL = 1.0;
    private static final double ATM_WITHDRAWAL_MULTIPLE = 100.0;
    private static final double MIN_TRANSFER = 1.0;
    private static final double MAX_TRANSFER = 50000.0;
    private static final double OPENING_BALANCE = 1000.0;
    
    private BankDataStore dataStore;
    
    public AccountService() {
        this.dataStore = BankDataStore.getInstance();
    }
    
    /**
     * Register a new account with customer details
     * @param fullName Customer's full name
     * @param email Customer's email
     * @param pin 4-digit PIN
     * @param type Account type (SAVINGS or CURRENT)
     * @return Created Account object
     * @throws IllegalArgumentException if validation fails
     */
    public Account registerAccount(String fullName, String email, int pin, AccountType type) {
        // Validate fullName not blank
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Full name cannot be blank");
        }
        
        // Validate email contains '@'
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address. Must contain '@'");
        }
        
        // Validate PIN is exactly 4 digits (1000-9999)
        if (pin < 1000 || pin > 9999) {
            throw new IllegalArgumentException("PIN must be exactly 4 digits (1000-9999)");
        }
        
        // Generate unique IDs
        String customerId = dataStore.generateCustomerId();
        String accountNumber = dataStore.generateAccountNumber();
        
        // Create Customer
        Customer customer = new Customer(customerId, fullName, email, pin);
        
        // Create Account based on type (POLYMORPHISM)
        Account account;
        if (type == AccountType.SAVINGS) {
            account = new SavingsAccount(accountNumber, customer);
        } else {
            account = new CurrentAccount(accountNumber, customer);
        }
        
        // Store customer and account in BankDataStore
        dataStore.addCustomer(customer);
        dataStore.addAccount(account);
        
        // Initial deposit of ₹1000 (opening balance)
        account.deposit(OPENING_BALANCE);
        
        return account;
    }
    
    /**
     * Get account by customer ID
     * @param customerId Customer ID
     * @return Account object
     * @throws AccountNotFoundException if account not found
     */
    public Account getAccountByCustomerId(String customerId) throws AccountNotFoundException {
        Account account = dataStore.findAccountByCustomerId(customerId);
        if (account == null) {
            throw new AccountNotFoundException(customerId);
        }
        return account;
    }
    
    /**
     * Deposit money into account
     * @param accountNumber Account number
     * @param amount Amount to deposit
     * @throws IllegalArgumentException if validation fails
     * @throws AccountNotFoundException if account not found
     */
    public void deposit(String accountNumber, double amount) 
            throws IllegalArgumentException, AccountNotFoundException {
        
        // Validate amount > 0
        if (amount < MIN_DEPOSIT) {
            throw new IllegalArgumentException(
                String.format("Minimum deposit amount is ₹%.2f", MIN_DEPOSIT)
            );
        }
        
        // Validate maximum single deposit
        if (amount > MAX_DEPOSIT) {
            throw new IllegalArgumentException(
                String.format("Maximum single deposit is ₹%.2f", MAX_DEPOSIT)
            );
        }
        
        // Find account
        Account account = dataStore.findAccountByNumber(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException(accountNumber);
        }
        
        // Perform deposit
        account.deposit(amount);
    }
    
    /**
     * Withdraw money from account
     * @param accountNumber Account number
     * @param amount Amount to withdraw
     * @throws IllegalArgumentException if validation fails
     * @throws InsufficientFundsException if insufficient balance
     * @throws AccountNotFoundException if account not found
     */
    public void withdraw(String accountNumber, double amount) 
            throws IllegalArgumentException, InsufficientFundsException, AccountNotFoundException {
        
        // Validate amount > 0
        if (amount < MIN_WITHDRAWAL) {
            throw new IllegalArgumentException(
                String.format("Minimum withdrawal amount is ₹%.2f", MIN_WITHDRAWAL)
            );
        }
        
        // Validate ATM withdrawal must be in multiples of ₹100
        if (amount % ATM_WITHDRAWAL_MULTIPLE != 0) {
            throw new IllegalArgumentException(
                String.format("Withdrawal amount must be in multiples of ₹%.0f", ATM_WITHDRAWAL_MULTIPLE)
            );
        }
        
        // Find account
        Account account = dataStore.findAccountByNumber(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException(accountNumber);
        }
        
        // Perform withdrawal - polymorphic method handles account-specific rules
        try {
            account.withdraw(amount);
        } catch (InsufficientFundsException e) {
            // Re-throw with enhanced message
            throw new InsufficientFundsException(
                "Withdrawal failed: " + e.getMessage(), 
                e.getAvailableBalance()
            );
        }
    }
    
    /**
     * Transfer money between accounts
     * @param fromAccountNumber Source account number
     * @param toAccountNumber Destination account number
     * @param amount Amount to transfer
     * @throws IllegalArgumentException if validation fails
     * @throws InsufficientFundsException if insufficient balance
     * @throws AccountNotFoundException if either account not found
     */
    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) 
            throws IllegalArgumentException, InsufficientFundsException, AccountNotFoundException {
        
        // Validate amount
        if (amount < MIN_TRANSFER) {
            throw new IllegalArgumentException(
                String.format("Minimum transfer amount is ₹%.2f", MIN_TRANSFER)
            );
        }
        
        if (amount > MAX_TRANSFER) {
            throw new IllegalArgumentException(
                String.format("Maximum transfer amount per transaction is ₹%.2f", MAX_TRANSFER)
            );
        }
        
        // Validate fromAccount != toAccount
        if (fromAccountNumber.equals(toAccountNumber)) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }
        
        // Find both accounts
        Account fromAccount = dataStore.findAccountByNumber(fromAccountNumber);
        if (fromAccount == null) {
            throw new AccountNotFoundException(fromAccountNumber);
        }
        
        Account toAccount = dataStore.findAccountByNumber(toAccountNumber);
        if (toAccount == null) {
            throw new AccountNotFoundException(toAccountNumber);
        }
        
        // Perform atomic transfer
        try {
            // Withdraw from source
            fromAccount.withdraw(amount);
            
            // Log TRANSFER_OUT on source
            fromAccount.logTransaction(amount, TransactionType.TRANSFER_OUT, 
                TransactionStatus.SUCCESS, 
                "Transfer to " + toAccountNumber);
            
            // Deposit to destination
            toAccount.deposit(amount);
            
            // Log TRANSFER_IN on destination
            toAccount.logTransaction(amount, TransactionType.TRANSFER_IN, 
                TransactionStatus.SUCCESS, 
                "Transfer from " + fromAccountNumber);
            
        } catch (InsufficientFundsException e) {
            // If withdraw fails, ensure destination is NOT credited (atomic behavior)
            throw new InsufficientFundsException(
                "Transfer failed: " + e.getMessage(), 
                e.getAvailableBalance()
            );
        }
    }
    
    /**
     * Get account by account number
     * @param accountNumber Account number
     * @return Account object
     * @throws AccountNotFoundException if account not found
     */
    public Account getAccountByNumber(String accountNumber) throws AccountNotFoundException {
        Account account = dataStore.findAccountByNumber(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException(accountNumber);
        }
        return account;
    }
}
