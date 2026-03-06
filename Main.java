import model.*;
import service.*;
import util.*;
import exception.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Main entry point for Banking/ATM System
 * Complete console-based menu-driven application
 */
public class Main {
    private static AuthService authService = new AuthService();
    private static AccountService accountService = new AccountService();
    private static TransactionService transactionService = new TransactionService();
    private static String loggedInCustomerId = null;
    
    public static void main(String[] args) {
        printBankLogo();
        System.out.println("Loading accounts...");
        
        boolean running = true;
        
        // GLOBAL EXCEPTION HANDLER - wrap entire menu loop
        while (running) {
            try {
                if (loggedInCustomerId == null) {
                    int choice = showMainMenu();
                    
                    switch (choice) {
                        case 1:
                            loginToAccount();
                            break;
                        case 2:
                            registerNewAccount();
                            break;
                        case 3:
                            System.out.println("\n✓ Thank you for using JavaBank ATM. Goodbye!");
                            running = false;
                            break;
                        default:
                            System.out.println("⚠ Invalid option. Please try again.");
                    }
                } else {
                    int choice = showATMMenu();
                    
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
                            viewMiniStatement();
                            break;
                        case 7:
                            logout();
                            break;
                        default:
                            System.out.println("⚠ Invalid option. Please try again.");
                    }
                }
            } catch (Exception e) {
                // Global exception handler - catch any unexpected errors
                System.out.println("\n⚠ System error: " + e.getMessage());
                System.out.println("Restarting menu...");
                InputHelper.pause();
                // Don't exit - continue to next iteration
            }
        }
        
        InputHelper.closeScanner();
    }
    
    /**
     * Print ASCII art bank logo
     */
    private static void printBankLogo() {
        System.out.println("\n");
        System.out.println("     ██╗ █████╗ ██╗   ██╗ █████╗ ██████╗  █████╗ ███╗   ██╗██╗  ██╗");
        System.out.println("     ██║██╔══██╗██║   ██║██╔══██╗██╔══██╗██╔══██╗████╗  ██║██║ ██╔╝");
        System.out.println("     ██║███████║██║   ██║███████║██████╔╝███████║██╔██╗ ██║█████╔╝ ");
        System.out.println("██   ██║██╔══██║╚██╗ ██╔╝██╔══██║██╔══██╗██╔══██║██║╚██╗██║██╔═██╗ ");
        System.out.println("╚█████╔╝██║  ██║ ╚████╔╝ ██║  ██║██████╔╝██║  ██║██║ ╚████║██║  ██╗");
        System.out.println(" ╚════╝ ╚═╝  ╚═╝  ╚═══╝  ╚═╝  ╚═╝╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝");
        System.out.println("\n          Welcome to JavaBank ATM - Your Trusted Banking Partner");
        InputHelper.printSeparator();
    }
    
    /**
     * Show main menu and get choice
     */
    private static int showMainMenu() {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║              BANKING SYSTEM MENU               ║");
        System.out.println("╠════════════════════════════════════════════════╣");
        System.out.println("║  1. Login to Account                           ║");
        System.out.println("║  2. Register New Account                       ║");
        System.out.println("║  3. Exit                                       ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        return InputHelper.promptInt("\nEnter your choice: ");
    }
    
    /**
     * Show ATM menu for logged-in user
     */
    private static int showATMMenu() {
        try {
            Account account = accountService.getAccountByCustomerId(loggedInCustomerId);
            Customer customer = account.getCustomer();
            
            System.out.println("\n╔════════════════════════════════════════════════╗");
            System.out.printf("║       ATM MENU — %-30s ║%n", loggedInCustomerId);
            System.out.println("╠════════════════════════════════════════════════╣");
            System.out.printf("║  Welcome, %-37s║%n", customer.getFullName());
            System.out.printf("║  Account: %-37s║%n", account.getAccountNumber());
            System.out.printf("║  Balance: ₹%-35.2f║%n", account.getBalance());
            System.out.println("╠════════════════════════════════════════════════╣");
            System.out.println("║  1. Check Balance                              ║");
            System.out.println("║  2. Deposit                                    ║");
            System.out.println("║  3. Withdraw                                   ║");
            System.out.println("║  4. Transfer Funds                             ║");
            System.out.println("║  5. Transaction History                        ║");
            System.out.println("║  6. Mini Statement                             ║");
            System.out.println("║  7. Logout                                     ║");
            System.out.println("╚════════════════════════════════════════════════╝");
        } catch (Exception e) {
            System.out.println("Error loading menu: " + e.getMessage());
        }
        
        return InputHelper.promptInt("\nEnter your choice: ");
    }
    
    /**
     * Login flow
     */
    private static void loginToAccount() {
        InputHelper.printHeader("LOGIN TO ACCOUNT");
        
        try {
            String customerId = InputHelper.promptString("Customer ID: ");
            int pin = InputHelper.promptPIN("Enter PIN");
            
            Customer customer = authService.login(customerId, pin);
            loggedInCustomerId = customerId;
            
            System.out.println("\n✓ Welcome back, " + customer.getFullName() + "!");
            InputHelper.pause();
            
        } catch (AccountNotFoundException e) {
            System.out.println("\n❌ " + e.getMessage());
            InputHelper.pause();
        } catch (InvalidPINException e) {
            System.out.println("\n❌ " + e.getMessage());
            InputHelper.pause();
        } catch (Exception e) {
            System.out.println("\n❌ Login error: " + e.getMessage());
            InputHelper.pause();
        }
    }
    
    /**
     * Registration flow
     */
    private static void registerNewAccount() {
        InputHelper.printHeader("REGISTER NEW ACCOUNT");
        
        try {
            String fullName = InputHelper.promptString("Full Name: ");
            String email = InputHelper.promptString("Email: ");
            
            int pin = InputHelper.promptPIN("Create PIN");
            int confirmPin = InputHelper.promptPIN("Confirm PIN");
            
            if (pin != confirmPin) {
                System.out.println("\n❌ PINs do not match. Registration cancelled.");
                InputHelper.pause();
                return;
            }
            
            System.out.println("\nAccount Type:");
            System.out.println("  1. Savings Account (Min Balance: ₹500, Interest: 3.5%)");
            System.out.println("  2. Current Account (Overdraft: ₹10,000, Daily Limit: ₹50,000)");
            int typeChoice = InputHelper.promptInt("Select account type (1 or 2): ");
            
            AccountType accountType;
            if (typeChoice == 1) {
                accountType = AccountType.SAVINGS;
            } else if (typeChoice == 2) {
                accountType = AccountType.CURRENT;
            } else {
                System.out.println("\n❌ Invalid account type. Registration cancelled.");
                InputHelper.pause();
                return;
            }
            
            Account account = accountService.registerAccount(fullName, email, pin, accountType);
            
            System.out.println("\n✅ Account created successfully!");
            System.out.println("─────────────────────────────────────────────────");
            System.out.println("Customer ID:    " + account.getCustomer().getCustomerId());
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Account Type:   " + accountType);
            System.out.println("Opening Balance: ₹1,000.00 (credited)");
            System.out.println("─────────────────────────────────────────────────");
            System.out.println("Please save your Customer ID and PIN for login.");
            InputHelper.pause();
            
        } catch (IllegalArgumentException e) {
            System.out.println("\n❌ Registration failed: " + e.getMessage());
            InputHelper.pause();
        } catch (Exception e) {
            System.out.println("\n❌ Unexpected error: " + e.getMessage());
            InputHelper.pause();
        }
    }
    
    /**
     * Check balance
     */
    private static void checkBalance() {
        InputHelper.printHeader("CHECK BALANCE");
        
        try {
            Account account = accountService.getAccountByCustomerId(loggedInCustomerId);
            
            System.out.println("\nAccount: " + account.getAccountNumber() + "  [" + account.getAccountType() + "]");
            System.out.println("─────────────────────────────────────────────────");
            System.out.printf("Available Balance:  ₹ %,.2f%n", account.getBalance());
            System.out.println("Account Holder:     " + account.getCustomer().getFullName());
            System.out.println("Status:             " + (account.isActive() ? "ACTIVE" : "INACTIVE"));
            
            if (account instanceof SavingsAccount) {
                SavingsAccount sa = (SavingsAccount) account;
                System.out.println("Minimum Balance:    ₹ " + sa.getMinimumBalance());
                System.out.println("Interest Rate:      " + sa.getInterestRate() + "%");
            } else if (account instanceof CurrentAccount) {
                CurrentAccount ca = (CurrentAccount) account;
                System.out.println("Overdraft Limit:    ₹ " + ca.getOverdraftLimit());
                System.out.println("Daily Limit:        ₹ " + ca.getDailyWithdrawalLimit());
                System.out.println("Today Withdrawn:    ₹ " + ca.getTodayWithdrawnAmount());
            }
            
            System.out.println("─────────────────────────────────────────────────");
            InputHelper.pause();
            
        } catch (Exception e) {
            System.out.println("\n✗ Error: " + e.getMessage());
            InputHelper.pause();
        }
    }
    
    /**
     * Deposit money
     */
    private static void depositMoney() {
        InputHelper.printHeader("DEPOSIT MONEY");
        
        try {
            Account account = accountService.getAccountByCustomerId(loggedInCustomerId);
            double currentBalance = account.getBalance();
            
            double amount = InputHelper.promptDouble("Enter amount to deposit: ₹ ");
            
            // Additional validation for negative amounts
            if (amount <= 0) {
                System.out.println("\n❌ Amount must be positive.");
                InputHelper.pause();
                return;
            }
            
            accountService.deposit(account.getAccountNumber(), amount);
            
            Account updatedAccount = accountService.getAccountByCustomerId(loggedInCustomerId);
            
            System.out.println("\n✅ Deposit successful!");
            System.out.println("─────────────────────────────────────────────────");
            System.out.printf("Amount Deposited:   ₹ %,.2f%n", amount);
            System.out.printf("Previous Balance:   ₹ %,.2f%n", currentBalance);
            System.out.printf("Current Balance:    ₹ %,.2f%n", updatedAccount.getBalance());
            System.out.println("─────────────────────────────────────────────────");
            InputHelper.pause();
            
        } catch (IllegalArgumentException e) {
            System.out.println("\n❌ " + e.getMessage());
            InputHelper.pause();
        } catch (Exception e) {
            System.out.println("\n❌ Error: " + e.getMessage());
            InputHelper.pause();
        }
    }
    
    /**
     * Withdraw money
     */
    private static void withdrawMoney() {
        InputHelper.printHeader("WITHDRAW MONEY");
        
        try {
            Account account = accountService.getAccountByCustomerId(loggedInCustomerId);
            double currentBalance = account.getBalance();
            
            System.out.printf("Available Balance: ₹ %,.2f%n", currentBalance);
            double amount = InputHelper.promptDouble("Enter amount to withdraw (multiples of ₹100): ₹ ");
            
            accountService.withdraw(account.getAccountNumber(), amount);
            
            Account updatedAccount = accountService.getAccountByCustomerId(loggedInCustomerId);
            
            System.out.println("\n✅ Withdrawal successful!");
            System.out.println("─────────────────────────────────────────────────");
            System.out.printf("Amount Withdrawn:   ₹ %,.2f%n", amount);
            System.out.printf("Previous Balance:   ₹ %,.2f%n", currentBalance);
            System.out.printf("Current Balance:    ₹ %,.2f%n", updatedAccount.getBalance());
            System.out.println("─────────────────────────────────────────────────");
            InputHelper.pause();
            
        } catch (InsufficientFundsException e) {
            System.out.println("\n❌ " + e.getMessage());
            InputHelper.pause();
        } catch (exception.DailyLimitExceededException e) {
            System.out.println("\n❌ " + e.getMessage());
            InputHelper.pause();
        } catch (IllegalArgumentException e) {
            System.out.println("\n❌ " + e.getMessage());
            InputHelper.pause();
        } catch (Exception e) {
            System.out.println("\n❌ Error: " + e.getMessage());
            InputHelper.pause();
        }
    }
    
    /**
     * Transfer funds
     */
    private static void transferFunds() {
        InputHelper.printHeader("TRANSFER FUNDS");
        
        try {
            Account account = accountService.getAccountByCustomerId(loggedInCustomerId);
            double currentBalance = account.getBalance();
            
            System.out.printf("Available Balance: ₹ %,.2f%n", currentBalance);
            String toAccountNumber = InputHelper.promptString("Enter destination account number: ");
            double amount = InputHelper.promptDouble("Enter amount to transfer: ₹ ");
            
            accountService.transfer(account.getAccountNumber(), toAccountNumber, amount);
            
            Account updatedAccount = accountService.getAccountByCustomerId(loggedInCustomerId);
            
            System.out.println("\n✅ Transfer successful!");
            System.out.println("─────────────────────────────────────────────────");
            System.out.printf("Amount Transferred: ₹ %,.2f%n", amount);
            System.out.println("To Account:         " + toAccountNumber);
            System.out.printf("Previous Balance:   ₹ %,.2f%n", currentBalance);
            System.out.printf("Current Balance:    ₹ %,.2f%n", updatedAccount.getBalance());
            System.out.println("─────────────────────────────────────────────────");
            InputHelper.pause();
            
        } catch (InsufficientFundsException e) {
            System.out.println("\n❌ " + e.getMessage());
            InputHelper.pause();
        } catch (AccountNotFoundException e) {
            System.out.println("\n❌ " + e.getMessage());
            InputHelper.pause();
        } catch (IllegalArgumentException e) {
            System.out.println("\n❌ " + e.getMessage());
            InputHelper.pause();
        } catch (Exception e) {
            System.out.println("\n❌ Error: " + e.getMessage());
            InputHelper.pause();
        }
    }
    
    /**
     * View transaction history
     */
    private static void viewTransactionHistory() {
        InputHelper.printHeader("TRANSACTION HISTORY");
        
        try {
            Account account = accountService.getAccountByCustomerId(loggedInCustomerId);
            List<Transaction> transactions = transactionService.getHistory(account.getAccountNumber());
            
            if (transactions.isEmpty()) {
                System.out.println("\nNo transactions found.");
            } else {
                System.out.println("\n─────────────────────────────────────────────────────────────────────────");
                System.out.printf("%-4s %-19s %-15s %-15s %-10s%n", 
                                "#", "Date", "Type", "Amount", "Status");
                System.out.println("─────────────────────────────────────────────────────────────────────────");
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy HH:mm");
                int count = 1;
                
                for (Transaction txn : transactions) {
                    String dateStr = txn.getTimestamp().format(formatter);
                    String type = txn.getTransactionType().toString();
                    String amountStr;
                    
                    if (txn.getTransactionType() == TransactionType.DEPOSIT || 
                        txn.getTransactionType() == TransactionType.TRANSFER_IN) {
                        amountStr = String.format("+₹%,.2f", txn.getAmount());
                    } else {
                        amountStr = String.format("-₹%,.2f", txn.getAmount());
                    }
                    
                    String status = txn.getStatus() == TransactionStatus.SUCCESS ? "✓" : "✗";
                    
                    System.out.printf("%-4d %-19s %-15s %-15s %-10s%n", 
                                    count++, dateStr, type, amountStr, status);
                }
                
                System.out.println("─────────────────────────────────────────────────────────────────────────");
                System.out.println("Total Transactions: " + transactions.size());
            }
            
            InputHelper.pause();
            
        } catch (Exception e) {
            System.out.println("\n✗ Error: " + e.getMessage());
            InputHelper.pause();
        }
    }
    
    /**
     * View mini statement (last 5 transactions)
     */
    private static void viewMiniStatement() {
        try {
            Account account = accountService.getAccountByCustomerId(loggedInCustomerId);
            transactionService.printMiniStatement(account.getAccountNumber());
            InputHelper.pause();
        } catch (Exception e) {
            System.out.println("\n✗ Error: " + e.getMessage());
            InputHelper.pause();
        }
    }
    
    /**
     * Logout
     */
    private static void logout() {
        try {
            Account account = accountService.getAccountByCustomerId(loggedInCustomerId);
            System.out.println("\n✓ Logging out... Goodbye, " + account.getCustomer().getFullName() + "!");
            authService.logout(loggedInCustomerId);
            loggedInCustomerId = null;
            InputHelper.pause();
        } catch (Exception e) {
            loggedInCustomerId = null;
        }
    }
}
