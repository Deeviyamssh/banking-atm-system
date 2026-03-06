package exception;

/**
 * Thrown when account has insufficient balance for withdrawal/transfer
 */
public class InsufficientFundsException extends RuntimeException {
    private double availableBalance;
    
    public InsufficientFundsException(String message, double availableBalance) {
        super(message);
        this.availableBalance = availableBalance;
    }
    
    public double getAvailableBalance() {
        return availableBalance;
    }
    
    @Override
    public String getMessage() {
        return super.getMessage() + " Available balance: ₹" + String.format("%.2f", availableBalance);
    }
}
