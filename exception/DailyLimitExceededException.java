package exception;

/**
 * Thrown when daily withdrawal limit is exceeded (CurrentAccount)
 */
public class DailyLimitExceededException extends RuntimeException {
    private double dailyLimit;
    private double attempted;
    
    public DailyLimitExceededException(double dailyLimit, double attempted) {
        super(String.format("Daily limit of ₹%,.2f exceeded. You attempted ₹%,.2f today.", 
              dailyLimit, attempted));
        this.dailyLimit = dailyLimit;
        this.attempted = attempted;
    }
    
    public double getDailyLimit() {
        return dailyLimit;
    }
    
    public double getAttempted() {
        return attempted;
    }
}
