# JavaBank ATM System

A complete Banking/ATM System built in Core Java (JDK 17+) with no external dependencies.

## Features

### Account Types
- **Savings Account**: Minimum balance ₹500, Interest rate 3.5%
- **Current Account**: Overdraft limit ₹10,000, Daily withdrawal limit ₹50,000

### Operations
1. **Account Registration**: Create new customer accounts with email and 4-digit PIN
2. **Login/Logout**: Secure authentication with PIN attempt tracking (max 3 attempts)
3. **Deposit**: Add funds (₹1 - ₹100,000 per transaction)
4. **Withdrawal**: Withdraw in multiples of ₹100
5. **Transfer**: Transfer funds between accounts (₹1 - ₹50,000 per transaction)
6. **Transaction History**: View complete transaction log
7. **Mini Statement**: View last 5 transactions

## Project Structure

```
JavaBankATM/
├── model/              # Domain models
│   ├── Account.java           (Abstract base class)
│   ├── SavingsAccount.java    (Inheritance)
│   ├── CurrentAccount.java    (Inheritance)
│   ├── Customer.java
│   ├── Transaction.java       (Comparable)
│   ├── AccountType.java       (Enum)
│   ├── TransactionType.java   (Enum)
│   └── TransactionStatus.java (Enum)
├── service/            # Business logic
│   ├── AuthService.java
│   ├── AccountService.java
│   └── TransactionService.java
├── exception/          # Custom exceptions
│   ├── InsufficientFundsException.java
│   ├── AccountNotFoundException.java
│   ├── InvalidPINException.java
│   └── DailyLimitExceededException.java
├── util/               # Utilities
│   ├── BankDataStore.java     (Singleton)
│   ├── InputHelper.java
│   └── Formatter.java
└── Main.java           # Entry point

```

## OOP Concepts Demonstrated

- **Abstraction**: Abstract Account class
- **Inheritance**: SavingsAccount and CurrentAccount extend Account
- **Polymorphism**: Overridden withdraw() methods with different behaviors
- **Encapsulation**: Private fields with getters/setters
- **Singleton Pattern**: BankDataStore
- **Generics**: List<Transaction>, Map<String, Account>
- **Collections**: ArrayList, HashMap
- **Streams**: filter(), mapToDouble(), sum(), collect()
- **Exception Handling**: Custom exceptions with meaningful messages

## How to Run

### Compile
```bash
javac -d bin model/*.java exception/*.java util/*.java service/*.java Main.java
```

### Run
```bash
java -cp bin Main
```

## Demo Accounts

Two pre-loaded accounts for testing:

1. **Savings Account**
   - Customer ID: `CUST1001`
   - PIN: `1234`
   - Balance: ₹5,000.00

2. **Current Account**
   - Customer ID: `CUST1002`
   - PIN: `5678`
   - Balance: ₹15,000.00

## Business Rules

### Deposits
- Minimum: ₹1.00
- Maximum: ₹100,000.00 per transaction

### Withdrawals
- Minimum: ₹1.00
- Must be in multiples of ₹100
- Savings: Cannot go below ₹500 minimum balance
- Current: Can use overdraft up to ₹10,000

### Transfers
- Minimum: ₹1.00
- Maximum: ₹50,000.00 per transaction
- Atomic operation (both debit and credit succeed or fail together)

### Security
- 4-digit PIN (1000-9999)
- Maximum 3 login attempts
- Account locks after 3 failed attempts

## Technologies

- Java 17+
- No external libraries
- Console I/O using Scanner
- In-memory data storage (HashMap)

## Author

Built as a demonstration of Core Java concepts and OOP principles.
