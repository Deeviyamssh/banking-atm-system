import model.Customer;
import model.Account;
import service.AuthService;
import service.AccountService;
import service.TransactionService;
import util.InputHelper;
import util.Formatter;
import exception.*;

/**
 * Main entry point for Banking/ATM System
 * Console-based menu-driven application
 */
public class Main {
    private static AuthService authService = new AuthService();
    private static AccountService accountService = new AccountService();
    private static TransactionService transactionService = new TransactionService();
    private static Customer loggedInCustomer = null;
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║     WELCOME TO JAVA BANKING & ATM SYSTEM              ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        
        boolean running = true;
        
        do {
            if (loggedInCustomer == null) {
                showMainMenu();
                int choice = InputHelper.readInt("Enter your choice: ");
                
                switch (choice) {
                    case 1:
                        registerNewCustomer();
                        break;
                    case 2:
                        loginToAccount();
                        break;
                    case 3:
                        System.out.println("\nThank you for using our Banking System!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                showAccountMenu();
                int choice = InputHelper.readInt("Enter your choice: ");
                
                switch (choice) {
                    case 1:
                        checkBalance();
                        break;
                    case 2:
                        depositMoney();
                        break;
                    case 3:
                        withdrawMoney();
                        break;
                    case 4:
                        transferFunds();
                        break;
                    case 5:
                        viewTransactionHistory();
                        break;
                    case 6:
                        viewAccountDetails();
                        break;
                    case 7:
                        logout();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
            
        } while (running);
        
        InputHelper.closeScanner();
    }
    
    private static void showMainMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("           BANKING SYSTEM MENU");
        System.out.println("=".repeat(60));
        System.out.println("1. Register New Customer");
        System.out.println("2. Login to Account");
        System.out.println("3. Exit");
        System.out.println("=".repeat(60));
    }
    
    private static void showAccountMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("           ATM OPERATIONS MENU");
        System.out.println("=".repeat(60));
        System.out.println("Welcome, " + loggedInCustomer.getName() + "!");
        System.out.println("Account: " + loggedInCustomer.getAccount().getAccountNumber());
        System.out.println("=".repeat(60));
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Transfer Funds");
        System.out.println("5. View Transaction History");
        System.out.println("6. View Account Details");
        System.out.println("7. Logout");
        System.out.println("=".repeat(60));
    }
    
    // TODO: Implement registration flow
    private static void registerNewCustomer() {
        System.out.println("\n--- CUSTOMER REGISTRATION ---");
        // TODO: Collect name, email, phone, PIN, account type
        // TODO: Call authService.registerCustomer()
        // TODO: Display success message with account number
    }
    
    // TODO: Implement login flow
    private static void loginToAccount() {
        System.out.println("\n--- LOGIN ---");
        // TODO: Read account number and PIN
        // TODO: Call authService.login()
        // TODO: Set loggedInCustomer if successful
        // TODO: Handle exceptions
    }
    
    // TODO: Implement balance check
    private static void checkBalance() {
        // TODO: Call accountService.checkBalance()
        // TODO: Display formatted balance
    }
    
    // TODO: Implement deposit
    private static void depositMoney() {
        System.out.println("\n--- DEPOSIT MONEY ---");
        // TODO: Read amount
        // TODO: Call accountService.deposit()
        // TODO: Display success message
    }
    
    // TODO: Implement withdrawal
    private static void withdrawMoney() {
        System.out.println("\n--- WITHDRAW MONEY ---");
        // TODO: Read amount
        // TODO: Call accountService.withdraw()
        // TODO: Handle exceptions (InsufficientFunds, DailyLimit)
    }
    
    // TODO: Implement fund transfer
    private static void transferFunds() {
        System.out.println("\n--- TRANSFER FUNDS ---");
        // TODO: Read destination account number and amount
        // TODO: Call accountService.transfer()
        // TODO: Handle exceptions
    }
    
    // TODO: Implement transaction history display
    private static void viewTransactionHistory() {
        System.out.println("\n--- TRANSACTION HISTORY ---");
        // TODO: Get transactions from transactionService
        // TODO: Use Formatter.printTransactionHistory()
    }
    
    // TODO: Implement account details display
    private static void viewAccountDetails() {
        System.out.println("\n--- ACCOUNT DETAILS ---");
        // TODO: Display customer and account information
        // TODO: Show account-specific details (min balance, overdraft, etc.)
    }
    
    // TODO: Implement logout
    private static void logout() {
        System.out.println("\nLogging out... Goodbye, " + loggedInCustomer.getName() + "!");
        loggedInCustomer = null;
    }
}
