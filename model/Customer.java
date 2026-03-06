package model;

/**
 * Customer entity - HAS-A relationship with Account
 */
public class Customer {
    private String customerId;
    private String name;
    private String email;
    private String phoneNumber;
    private String pin; // 4-digit PIN for authentication
    private Account account; // HAS-A relationship
    
    public Customer(String customerId, String name, String email, String phoneNumber, String pin) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.pin = pin;
    }
    
    // TODO: Validate PIN
    public boolean validatePin(String inputPin) {
        // TODO: Compare inputPin with stored pin
        return false;
    }
    
    // TODO: Getters and setters
    public String getCustomerId() {
        return customerId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public Account getAccount() {
        return account;
    }
    
    public void setAccount(Account account) {
        this.account = account;
    }
    
    public void setPin(String pin) {
        this.pin = pin;
    }
}
