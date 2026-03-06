package util;

import model.Customer;
import java.util.HashMap;
import java.util.Map;

/**
 * In-memory data store for customers and accounts
 * Simulates database functionality
 */
public class BankDataStore {
    private static BankDataStore instance;
    private Map<String, Customer> customersByAccountNumber;
    private Map<String, Customer> customersById;
    private int accountCounter;
    private int customerCounter;
    private int transactionCounter;
    
    private BankDataStore() {
        this.customersByAccountNumber = new HashMap<>();
        this.customersById = new HashMap<>();
        this.accountCounter = 1000;
        this.customerCounter = 1000;
        this.transactionCounter = 1000;
    }
    
    // TODO: Singleton pattern
    public static BankDataStore getInstance() {
        if (instance == null) {
            instance = new BankDataStore();
        }
        return instance;
    }
    
    // TODO: Generate unique account number
    public String generateAccountNumber() {
        return "ACC" + (++accountCounter);
    }
    
    // TODO: Generate unique customer ID
    public String generateCustomerId() {
        return "CUST" + (++customerCounter);
    }
    
    // TODO: Generate unique transaction ID
    public String generateTransactionId() {
        return "TXN" + (++transactionCounter);
    }
    
    // TODO: Add customer to store
    public void addCustomer(Customer customer) {
        // TODO: Store by customerId and accountNumber
    }
    
    // TODO: Find customer by account number
    public Customer findByAccountNumber(String accountNumber) {
        return customersByAccountNumber.get(accountNumber);
    }
    
    // TODO: Find customer by customer ID
    public Customer findByCustomerId(String customerId) {
        return customersById.get(customerId);
    }
    
    // TODO: Check if account exists
    public boolean accountExists(String accountNumber) {
        return customersByAccountNumber.containsKey(accountNumber);
    }
}
