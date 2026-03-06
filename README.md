# JavaBank ATM System

A complete, production-ready Banking/ATM System built in Core Java (JDK 17+) demonstrating fundamental OOP principles, design patterns, and enterprise-level exception handling. No external frameworks or databases - pure Java with console I/O.

## Description

JavaBank ATM is a comprehensive banking simulation that provides a realistic ATM experience through a console interface. The system implements industry-standard banking operations including account management, secure authentication, fund transfers, and transaction tracking. Built with clean architecture principles, the application showcases professional Java development practices including inheritance hierarchies, polymorphic behavior, custom exception handling, and robust input validation.

## Features

### Core Banking Operations
1. **Account Registration** - Create new Savings or Current accounts with email validation and 4-digit PIN
2. **Secure Login** - PIN-based authentication with attempt tracking (max 3 attempts before account lock)
3. **Check Balance** - View current balance with account-specific details and interest information
4. **Deposit Money** - Add funds with validation (₹1 - ₹100,000 per transaction)
5. **Withdraw Money** - Withdraw cash in multiples of ₹100 with account-specific rules
6. **Transfer Funds** - Atomic fund transfers between accounts (₹1 - ₹50,000 per transaction)
7. **Transaction History** - Complete audit trail with timestamps and status tracking
8. **Mini Statement** - Quick view of last 5 transactions

### Bonus Features
- **Account Summary on Login** - Shows last login, current balance, and pending alerts
- **Interest Notice** - Savings accounts display monthly interest rate and credit date
- **Masked Account Numbers** - Security-enhanced display (ACC****01)
- **Indian Currency Format** - Proper formatting with lakhs and crores (₹1,23,456.00)

## OOP Concepts Demonstrated

### Core Principles
- **Abstraction** - Abstract `Account` class defining common banking operations
- **Inheritance** - `SavingsAccount` and `CurrentAccount` extend `Account` with specialized behavior
- **Polymorphism** - Overridden `withdraw()` method with different rules per account type
- **Encapsulation** - Private fields with controlled access through getters/setters

### Design Patterns
- **Singleton Pattern** - `BankDataStore` ensures single instance for data management
- **Template Method** - Abstract methods in base class implemented by subclasses
- **Strategy Pattern** - Different account types with different business rules

### Advanced Java Features
- **Custom Exceptions** - `InsufficientFundsException`, `InvalidPINException`, `AccountNotFoundException`, `DailyLimitExceededException`
- **Collections & Generics** - `List<Transaction>`, `Map<String, Account>`, `HashMap`, `ArrayList`
- **Streams API** - `filter()`, `mapToDouble()`, `sum()`, `collect()`, `limit()`
- **Lambda Expressions** - Method references and predicates for functional programming
- **Comparable Interface** - Transaction sorting by timestamp
- **Enums** - Type-safe constants for AccountType, TransactionType, TransactionStatus

## Project Structure

```
JavaBankATM/
├── model/                      # Domain models
│   ├── Account.java           # Abstract base class
│   ├── SavingsAccount.java    # Inheritance + Polymorphism
│   ├── CurrentAccount.java    # Inheritance + Polymorphism
│   ├── Customer.java          # Customer entity
│   ├── Transaction.java       # Comparable implementation
│   ├── AccountType.java       # Enum
│   ├── TransactionType.java   # Enum
│   └── TransactionStatus.java # Enum
├── service/                    # Business logic layer
│   ├── AuthService.java       # Authentication & authorization
│   ├── AccountService.java    # Account operations
│   └── TransactionService.java # Transaction management
├── exception/                  # Custom exceptions
│   ├── InsufficientFundsException.java
│   ├── AccountNotFoundException.java
│   ├── InvalidPINException.java
│   └── DailyLimitExceededException.java
├── util/                       # Utility classes
│   ├── BankDataStore.java     # Singleton data store
│   ├── InputHelper.java       # Input validation
│   └── Formatter.java         # Output formatting
└── Main.java                   # Entry point with menu system
```

## Compile & Run

### Compilation
```bash
javac -d bin model/*.java exception/*.java util/*.java service/*.java Main.java
```

### Execution
```bash
java -cp bin Main
```

### Clean Build
```bash
rm -rf bin/*
javac -d bin model/*.java exception/*.java util/*.java service/*.java Main.java
java -cp bin Main
```

## Demo Credentials

```
╔══════════════════════════════════════════════╗
║           DEMO CREDENTIALS                   ║
╠══════════════════════════════════════════════╣
║  Savings:  CUST0001  PIN: 1234               ║
║            Balance: ₹15,000.00               ║
║                                              ║
║  Current:  CUST0002  PIN: 5678               ║
║            Balance: ₹50,000.00               ║
╚══════════════════════════════════════════════╝
```

## Sample Console Output

```
     ██╗ █████╗ ██╗   ██╗ █████╗ ██████╗  █████╗ ███╗   ██╗██╗  ██╗
     ██║██╔══██╗██║   ██║██╔══██╗██╔══██╗██╔══██╗████╗  ██║██║ ██╔╝
     ██║███████║██║   ██║███████║██████╔╝███████║██╔██╗ ██║█████╔╝ 
██   ██║██╔══██║╚██╗ ██╔╝██╔══██║██╔══██╗██╔══██║██║╚██╗██║██╔═██╗ 
╚█████╔╝██║  ██║ ╚████╔╝ ██║  ██║██████╔╝██║  ██║██║ ╚████║██║  ██╗
 ╚════╝ ╚═╝  ╚═╝  ╚═══╝  ╚═╝  ╚═╝╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝

          Welcome to JavaBank ATM - Your Trusted Banking Partner
════════════════════════════════════════════════════════════════

╔════════════════════════════════════════════════╗
║              BANKING SYSTEM MENU               ║
╠════════════════════════════════════════════════╣
║  1. Login to Account                           ║
║  2. Register New Account                       ║
║  3. Exit                                       ║
╚════════════════════════════════════════════════╝

Enter your choice: 1

╔════════════════════════════════════════════════╗
║  LOGIN TO ACCOUNT                              ║
╚════════════════════════════════════════════════╝

Customer ID: CUST0001
Enter PIN (4 digits): 1234

✓ Welcome back, Rahul Sharma!

╔══════════════════════════════════════════════╗
║           ACCOUNT SUMMARY                    ║
╠══════════════════════════════════════════════╣
║  Last login: Not available (first login)     ║
║  Current Balance: ₹15,000.00                 ║
║  Pending alerts: None                        ║
╚══════════════════════════════════════════════╝

Press Enter to continue...

╔════════════════════════════════════════════════╗
║       ATM MENU — CUST0001                      ║
╠════════════════════════════════════════════════╣
║  Welcome, Rahul Sharma                         ║
║  Account: ACC****01                            ║
║  Balance: ₹15,000.00                           ║
╠════════════════════════════════════════════════╣
║  1. Check Balance                              ║
║  2. Deposit                                    ║
║  3. Withdraw                                   ║
║  4. Transfer Funds                             ║
║  5. Transaction History                        ║
║  6. Mini Statement                             ║
║  7. Logout                                     ║
╚════════════════════════════════════════════════╝
```

## Business Rules

### Account Types

#### Savings Account
- Minimum balance: ₹500.00
- Interest rate: 3.5% annually
- No overdraft facility
- Interest credited monthly

#### Current Account
- No minimum balance
- Overdraft limit: ₹10,000.00
- Daily withdrawal limit: ₹50,000.00
- No interest earned

### Transaction Limits

| Operation | Minimum | Maximum | Special Rules |
|-----------|---------|---------|---------------|
| Deposit | ₹1.00 | ₹100,000.00 | - |
| Withdrawal | ₹1.00 | - | Must be multiple of ₹100 |
| Transfer | ₹1.00 | ₹50,000.00 | Atomic operation |

### Security
- 4-digit PIN (1000-9999)
- Maximum 3 login attempts
- Account locks after failed attempts
- Masked account numbers in display

## Error Handling

The system is completely crash-proof with comprehensive exception handling:

- ✅ Invalid input types (letters for numbers) - Re-prompts with helpful message
- ✅ Negative amounts - Rejected with validation message
- ✅ Non-existent accounts - Clear error message
- ✅ Insufficient funds - Shows available vs requested
- ✅ Business rule violations - Specific error messages
- ✅ Unexpected errors - Global exception handler catches and recovers

## Testing

### Quick Test Scenarios

1. **Login**: Use CUST0001 / PIN 1234
2. **Check Balance**: View account details with interest notice
3. **Deposit**: Add ₹5,000
4. **Withdraw**: Take out ₹1,000 (multiple of 100)
5. **Transfer**: Send ₹500 to ACC100002
6. **View History**: See all transactions
7. **Invalid PIN**: Try wrong PIN 3 times to see account lock

### Edge Cases Handled
- Invalid PIN format (letters, too short/long)
- Negative amounts
- Withdrawal not multiple of 100
- Transfer to same account
- Exceeding daily limits
- Below minimum balance
- Non-existent accounts

## Technical Highlights

### Code Quality
- ✅ Comprehensive Javadoc comments
- ✅ Named constants (no magic numbers)
- ✅ Proper exception handling
- ✅ Input validation at all layers
- ✅ Separation of concerns (MVC-like)
- ✅ DRY principle (utility classes)
- ✅ Single Responsibility Principle

### Performance
- In-memory data storage (HashMap)
- O(1) account lookups
- Efficient stream operations
- Minimal object creation

### Security
- PIN validation
- Attempt tracking
- Account locking
- Masked sensitive data
- No password storage in plain text

## Future Enhancements

Potential additions for production deployment:
- Database integration (JDBC/JPA)
- Password encryption (BCrypt)
- Session management
- Multi-threading support
- REST API layer (Spring Boot)
- PDF statement generation
- Email/SMS notifications
- Admin panel
- Audit logging
- Interest calculation automation

## Requirements

- Java Development Kit (JDK) 17 or higher
- No external dependencies
- Works on Windows, macOS, and Linux

## Author

**JavaBank ATM System**  
Developed as a demonstration of Core Java and OOP principles  
March 2026

## License

This project is created for educational purposes.

---

## Quick Start Guide

1. Clone or download the project
2. Navigate to project directory
3. Compile: `javac -d bin model/*.java exception/*.java util/*.java service/*.java Main.java`
4. Run: `java -cp bin Main`
5. Login with demo credentials: CUST0001 / PIN 1234
6. Explore all ATM operations

**Enjoy your banking experience with JavaBank ATM!** 🏦
