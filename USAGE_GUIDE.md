# JavaBank ATM - Usage Guide

## Quick Start

### 1. Compile the Project
```bash
javac -d bin model/*.java exception/*.java util/*.java service/*.java Main.java
```

### 2. Run the Application
```bash
java -cp bin Main
```

## Application Flow

### Main Menu
```
╔════════════════════════════════════════════════╗
║              BANKING SYSTEM MENU               ║
╠════════════════════════════════════════════════╣
║  1. Login to Account                           ║
║  2. Register New Account                       ║
║  3. Exit                                       ║
╚════════════════════════════════════════════════╝
```

## Demo Walkthrough

### Test Scenario 1: Login with Demo Account

1. Select option `1` (Login to Account)
2. Enter Customer ID: `CUST1001`
3. Enter PIN: `1234`
4. You'll see: `✓ Welcome back, Rajesh Kumar!`

### Test Scenario 2: Register New Account

1. Select option `2` (Register New Account)
2. Enter Full Name: `John Doe`
3. Enter Email: `john@email.com`
4. Create PIN: `5555`
5. Confirm PIN: `5555`
6. Select Account Type: `1` (Savings)
7. You'll receive:
   - Customer ID (save this!)
   - Account Number
   - Opening balance: ₹1,000.00

### Test Scenario 3: Deposit Money

1. Login with your account
2. Select option `2` (Deposit)
3. Enter amount: `5000`
4. You'll see:
```
✅ Deposit successful!
─────────────────────────────────────────────────
Amount Deposited:   ₹ 5,000.00
Previous Balance:   ₹ 1,000.00
Current Balance:    ₹ 6,000.00
─────────────────────────────────────────────────
```

### Test Scenario 4: Withdraw Money

1. Select option `3` (Withdraw)
2. Enter amount: `1000` (must be multiple of 100)
3. Transaction completes if sufficient balance

### Test Scenario 5: Transfer Funds

1. Select option `4` (Transfer Funds)
2. Enter destination account: `ACC100002`
3. Enter amount: `500`
4. Both accounts updated atomically

### Test Scenario 6: View Transaction History

1. Select option `5` (Transaction History)
2. See all transactions in tabular format:
```
─────────────────────────────────────────────────────────────────────────
#    Date                Type            Amount          Status
─────────────────────────────────────────────────────────────────────────
1    06-Mar-26 16:07     WITHDRAWAL      -₹1,000.00      ✓
2    06-Mar-26 16:07     DEPOSIT         +₹2,000.00      ✓
3    06-Mar-26 16:07     DEPOSIT         +₹5,000.00      ✓
─────────────────────────────────────────────────────────────────────────
```

### Test Scenario 7: Mini Statement

1. Select option `6` (Mini Statement)
2. See last 5 transactions with formatted table

## Error Handling Examples

### Invalid PIN
```
✗ Invalid PIN entered. Attempts remaining: 2
```

### Insufficient Funds
```
✗ Withdrawal failed: Cannot withdraw. Minimum balance of ₹500 must be maintained. Available balance: ₹5000.00
```

### Invalid Withdrawal Amount
```
✗ Withdrawal amount must be in multiples of ₹100
```

### Account Locked
```
🔒 Account locked. Please contact customer service.
```

### Daily Limit Exceeded (Current Account)
```
✗ Daily withdrawal limit exceeded. Limit: ₹50,000.00, Attempted: ₹55,000.00
```

## Business Rules Reference

| Operation | Minimum | Maximum | Special Rules |
|-----------|---------|---------|---------------|
| Deposit | ₹1 | ₹100,000 | - |
| Withdrawal | ₹1 | - | Must be multiple of ₹100 |
| Transfer | ₹1 | ₹50,000 | Atomic operation |
| Savings Min Balance | ₹500 | - | Cannot withdraw below this |
| Current Overdraft | - | ₹10,000 | Can go negative |
| Current Daily Limit | - | ₹50,000 | Resets daily |

## Account Type Comparison

### Savings Account
- ✓ Earns interest (3.5% annually)
- ✓ Lower fees
- ✗ Minimum balance required (₹500)
- ✗ No overdraft facility

### Current Account
- ✓ Overdraft facility (₹10,000)
- ✓ Higher transaction limits
- ✓ No minimum balance
- ✗ No interest earned
- ✗ Daily withdrawal limit (₹50,000)

## Tips

1. **Save your Customer ID**: You'll need it to login
2. **Remember your PIN**: Only 3 attempts allowed
3. **Check balance first**: Before large withdrawals
4. **Use mini statement**: For quick transaction review
5. **Logout properly**: Always use option 7 to logout

## Troubleshooting

### "Account locked" message
- Wait or contact admin to reset attempts
- Demo accounts can be reset by restarting the application

### "Account not found"
- Double-check Customer ID spelling
- Ensure account was created successfully

### "Invalid amount" errors
- Check minimum/maximum limits
- For withdrawals, use multiples of ₹100
- Ensure sufficient balance

## Advanced Features

### Polymorphism in Action
- Savings and Current accounts have different withdrawal rules
- Same `withdraw()` method, different behavior
- Demonstrates OOP polymorphism

### Transaction Tracking
- Every operation logged
- Timestamps recorded
- Success/failure status tracked
- Sortable by date (latest first)

### Data Persistence
- In-memory storage (HashMap)
- Data lost on application restart
- Demo accounts reload automatically

## Next Steps

After mastering the basics:
1. Try creating multiple accounts
2. Test transfer between your accounts
3. Experiment with edge cases (minimum balance, overdraft)
4. Review transaction history patterns
5. Test error handling (wrong PIN, invalid amounts)

---

**Note**: This is a demonstration project. In production, you would add:
- Database persistence
- Password encryption
- Session management
- Audit logging
- Multi-threading support
- REST API layer
