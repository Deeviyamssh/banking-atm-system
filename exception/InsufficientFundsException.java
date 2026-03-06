package exception;

/**
 * Thrown when account has insufficient balance for withdrawal/transfer
 */
public class InsufficientFundsException extends RuntimeException {
    private double availableBalance;
    private double requestedAmount;
    
    public InsufficientFundsException(String message, double availableBalance) {
        super(message);
        this.availableBalance = availableBalance;
        this.requestedAmount = 0.0;
    }
    
    public InsufficientFundsException(String message, double availableBalance, double requestedAmount) {
        super(message);
        this.availableBalance = availableBalance;
        this.requestedAmount = requestedAmount;
    }
    
    public double getAvailableBalance() {
        return availableBalance;
    }
    
    public double getRequestedAmount() {
        return requestedAmount;
    }
    
    @Override
    public String getMessage() {
        if (requestedAmount > 0) {
            return String.format("Insufficient funds. Available: ₹%,.2f, Requested: ₹%,.2f", 
                               availableBalance, requestedAmount);
        }
        return super.getMessage() + " Available: ₹" + String.format("%,.2f", availableBalance);
    }
}
