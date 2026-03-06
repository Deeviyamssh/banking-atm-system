package service;

import model.Customer;
import exception.AccountNotFoundException;
import exception.InvalidPINException;
import util.BankDataStore;

/**
 * Service for authentication operations
 */
public class AuthService {
    private BankDataStore dataStore;
    
    public AuthService() {
        this.dataStore = BankDataStore.getInstance();
    }
    
    // TODO: Authenticate customer with account number and PIN
    public Customer login(String accountNumber, String pin) 
            throws AccountNotFoundException, InvalidPINException {
        // TODO: Find customer by account number
        // TODO: Validate PIN
        // TODO: Return customer if successful
        // TODO: Throw exceptions if account not found or PIN invalid
        return null;
    }
    
    // TODO: Register new customer
    public Customer registerCustomer(String name, String email, String phoneNumber, 
                                    String pin, String accountType) {
        // TODO: Generate customer ID
        // TODO: Create Customer object
        // TODO: Create appropriate Account (Savings/Current)
        // TODO: Link account to customer
        // TODO: Store in dataStore
        // TODO: Return customer
        return null;
    }
}
