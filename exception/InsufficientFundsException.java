package exception;

/**
 * Thrown when account has insufficient balance for withdrawal/transfer
 */
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
