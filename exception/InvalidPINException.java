package exception;

/**
 * Thrown when PIN validation fails
 */
public class InvalidPINException extends RuntimeException {
    private int attemptsRemaining;
    
    public InvalidPINException(int attemptsRemaining) {
        super(attemptsRemaining > 0 
            ? "Incorrect PIN. " + attemptsRemaining + " attempt(s) remaining before account lock."
            : "Account locked due to too many failed attempts. Please contact customer service.");
        this.attemptsRemaining = attemptsRemaining;
    }
    
    public int getAttemptsRemaining() {
        return attemptsRemaining;
    }
}
