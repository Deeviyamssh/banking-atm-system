package exception;

/**
 * Thrown when PIN validation fails
 */
public class InvalidPINException extends RuntimeException {
    private int attemptsRemaining;
    
    public InvalidPINException(int attemptsRemaining) {
        super("Invalid PIN entered. Attempts remaining: " + attemptsRemaining);
        this.attemptsRemaining = attemptsRemaining;
    }
    
    public int getAttemptsRemaining() {
        return attemptsRemaining;
    }
}
