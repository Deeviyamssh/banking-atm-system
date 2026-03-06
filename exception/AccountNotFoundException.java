package exception;

/**
 * Thrown when account number is not found in the system
 */
public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
