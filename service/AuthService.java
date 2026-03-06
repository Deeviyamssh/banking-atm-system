package service;

import model.Customer;
import exception.AccountNotFoundException;
import exception.InvalidPINException;
import util.BankDataStore;
import java.util.HashMap;
import java.util.Map;

/**
 * Service for authentication operations
 * Handles login, logout, and PIN attempt tracking
 */
public class AuthService {
    private static final int MAX_ATTEMPTS = 3;
    
    private BankDataStore dataStore;
    private Map<String, Integer> loginAttempts; // Track failed PIN attempts per customerId
    
    public AuthService() {
        this.dataStore = BankDataStore.getInstance();
        this.loginAttempts = new HashMap<>();
    }
    
    /**
     * Authenticate customer with customerId and PIN
     * @param customerId Customer ID
     * @param pin 4-digit PIN
     * @return Customer object if authentication successful
     * @throws AccountNotFoundException if customer not found
     * @throws InvalidPINException if PIN is incorrect
     */
    public Customer login(String customerId, int pin) 
            throws AccountNotFoundException, InvalidPINException {
        
        // Check if account is locked
        if (isLocked(customerId)) {
            throw new InvalidPINException(0);
        }
        
        // Look up customer in BankDataStore
        Customer customer = dataStore.findCustomerById(customerId);
        
        // If not found, throw AccountNotFoundException
        if (customer == null) {
            throw new AccountNotFoundException(customerId);
        }
        
        // Validate PIN
        if (customer.getPin() != pin) {
            // Increment failed attempts
            int attempts = loginAttempts.getOrDefault(customerId, 0) + 1;
            loginAttempts.put(customerId, attempts);
            
            // Check if account should be locked
            if (attempts >= MAX_ATTEMPTS) {
                throw new InvalidPINException(0);
            } else {
                int remaining = MAX_ATTEMPTS - attempts;
                throw new InvalidPINException(remaining);
            }
        }
        
        // PIN correct - reset attempts and return customer
        loginAttempts.put(customerId, 0);
        return customer;
    }
    
    /**
     * Logout customer and reset login attempts
     * @param customerId Customer ID
     */
    public void logout(String customerId) {
        loginAttempts.put(customerId, 0);
    }
    
    /**
     * Check if customer account is locked due to failed attempts
     * @param customerId Customer ID
     * @return true if account is locked
     */
    public boolean isLocked(String customerId) {
        return loginAttempts.getOrDefault(customerId, 0) >= MAX_ATTEMPTS;
    }
    
    /**
     * Reset login attempts for a customer (admin function)
     * @param customerId Customer ID
     */
    public void resetAttempts(String customerId) {
        loginAttempts.put(customerId, 0);
    }
}
