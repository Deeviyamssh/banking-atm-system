package exception;

/**
 * Thrown when daily withdrawal limit is exceeded (CurrentAccount)
 */
public class DailyLimitExceededException extends Exception {
    public DailyLimitExceededException(String message) {
        super(message);
    }
}
