package exception;

/**
 * Thrown when PIN validation fails
 */
public class InvalidPINException extends Exception {
    public InvalidPINException(String message) {
        super(message);
    }
}
