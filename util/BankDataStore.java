package util;

import model.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

/**
 * In-memory data store for customers and accounts (Singleton pattern)
 * Simulates database functionality
 */
public class BankDataStore {
    private static BankDataStore instance;
    
    private Map<String, Customer> customers; // key: customerId
    private Map<String, Account> accounts; // key: accountNumber
    private Map<String, String> customerToAccount; // key: customerId -> accountNumber
    
    private int customerCounter = 1000;
    private int accountCounter = 100000;
    
    // Private constructor for Singleton
    private BankDataStore() {
        this.customers = new HashMap<>();
        this.accounts = new HashMap<>();
        this.customerToAccount = new HashMap<>();
        
        // Pre-load 2 demo accounts for testing
        initializeDemoData();
    }
    
    // Singleton getInstance method
    public static BankDataStore getInstance() {
        if (instance == null) {
            instance = new BankDataStore();
        }
        return instance;
    }
    
    // Initialize demo data
    private void initializeDemoData() {
        // Demo Customer 1 - Savings Account
        Customer customer1 = new Customer("CUST1001", "Rajesh Kumar", "rajesh@email.com", 1234);
        SavingsAccount savingsAccount = new SavingsAccount("ACC100001", customer1);
        savingsAccount.deposit(5000.0); // Initial deposit
        
        customers.put(customer1.getCustomerId(), customer1);
        accounts.put(savingsAccount.getAccountNumber(), savingsAccount);
        customerToAccount.put(customer1.getCustomerId(), savingsAccount.getAccountNumber());
        
        // Demo Customer 2 - Current Account
        Customer customer2 = new Customer("CUST1002", "Priya Sharma", "priya@email.com", 5678);
        CurrentAccount currentAccount = new CurrentAccount("ACC100002", customer2);
        currentAccount.deposit(15000.0); // Initial deposit
        
        customers.put(customer2.getCustomerId(), customer2);
        accounts.put(currentAccount.getAccountNumber(), currentAccount);
        customerToAccount.put(customer2.getCustomerId(), currentAccount.getAccountNumber());
        
        System.out.println("Demo accounts loaded:");
        System.out.println("1. Savings Account - ACC100001, PIN: 1234, Balance: ₹5000.00");
        System.out.println("2. Current Account - ACC100002, PIN: 5678, Balance: ₹15000.00");
    }
    
    // Add customer
    public void addCustomer(Customer customer) {
        customers.put(customer.getCustomerId(), customer);
    }
    
    // Add account
    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
        customerToAccount.put(account.getCustomer().getCustomerId(), account.getAccountNumber());
    }
    
    // Find customer by ID
    public Customer findCustomerById(String customerId) {
        return customers.get(customerId);
    }
    
    // Find account by account number
    public Account findAccountByNumber(String accountNumber) {
        return accounts.get(accountNumber);
    }
    
    // Find account by customer ID
    public Account findAccountByCustomerId(String customerId) {
        String accountNumber = customerToAccount.get(customerId);
        return accountNumber != null ? accounts.get(accountNumber) : null;
    }
    
    // Get all accounts
    public Collection<Account> getAllAccounts() {
        return accounts.values();
    }
    
    // Generate unique customer ID
    public String generateCustomerId() {
        return "CUST" + (++customerCounter);
    }
    
    // Generate unique account number
    public String generateAccountNumber() {
        return "ACC" + (++accountCounter);
    }
    
    // Check if account exists
    public boolean accountExists(String accountNumber) {
        return accounts.containsKey(accountNumber);
    }
    
    // Check if customer exists
    public boolean customerExists(String customerId) {
        return customers.containsKey(customerId);
    }
}
