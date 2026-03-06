package model;

import java.util.Objects;

/**
 * Customer entity - HAS-A relationship with Account
 */
public class Customer {
    private String customerId;
    private String fullName;
    private String email;
    private int pin; // 4-digit PIN for authentication
    
    // No-arg constructor
    public Customer() {
    }
    
    // All-args constructor
    public Customer(String customerId, String fullName, String email, int pin) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.email = email;
        this.pin = pin;
    }
    
    // Getters and setters
    public String getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public int getPin() {
        return pin;
    }
    
    public void setPin(int pin) {
        this.pin = pin;
    }
    
    // toString - never expose PIN
    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
    
    // equals and hashCode based on customerId
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }
}
