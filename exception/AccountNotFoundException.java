package exception;

/**
 * Thrown when account number is not found in the system
 */
public class AccountNotFoundException extends RuntimeException {
    private String accountId;
    
    public AccountNotFoundException(String accountId) {
        super("No account found with ID: " + accountId);
        this.accountId = accountId;
    }
    
    public String getAccountId() {
        return accountId;
    }
}
