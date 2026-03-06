package exception;

/**
 * Thrown when account number is not found in the system
 */
public class AccountNotFoundException extends RuntimeException {
    private String accountNumber;
    
    public AccountNotFoundException(String accountNumber) {
        super("Account not found: " + accountNumber);
        this.accountNumber = accountNumber;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
}
