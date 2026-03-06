# JavaBank ATM System - 3-Minute Demo Script

## Preparation (Before Demo)

```bash
# Compile the project
javac -d bin model/*.java exception/*.java util/*.java service/*.java Main.java

# Run the application
java -cp bin Main
```

---

## Demo Flow (10 Steps - Under 3 Minutes)

### Step 1: Show Startup & Demo Credentials (15 seconds)
**Action**: Application starts and displays ASCII logo + demo credentials box

**Say**: "Welcome to JavaBank ATM - a complete banking system built in pure Java. Notice the demo credentials displayed for easy testing."

**What to show**:
- ASCII art logo
- Demo credentials box with CUST0001 and CUST0002

---

### Step 2: Login with Savings Account (20 seconds)
**Action**: 
- Select option `1` (Login)
- Enter Customer ID: `CUST0001`
- Enter PIN: `1234`

**Say**: "Let's login with our savings account. The system validates the PIN and shows an account summary immediately after login."

**What to show**:
- ✓ Welcome message
- Account summary box showing balance and alerts
- Masked account number in ATM menu (ACC****01)

---

### Step 3: Check Balance with Interest Notice (15 seconds)
**Action**: Select option `1` (Check Balance)

**Say**: "Checking balance shows detailed account information. Notice the interest notice for savings accounts - a bonus feature showing when interest will be credited."

**What to show**:
- Current balance in Indian format (₹15,000.00)
- Account type and status
- 💰 Interest notice: "3.5% — Interest will be credited on 1st of next month"

---

### Step 4: Deposit Money (20 seconds)
**Action**: 
- Select option `2` (Deposit)
- Enter amount: `5000`

**Say**: "Making a deposit is simple. The system validates the amount and shows before/after balances with proper Indian currency formatting."

**What to show**:
- ✅ Success message
- Previous balance: ₹15,000.00
- Current balance: ₹20,000.00

---

### Step 5: Withdraw Money (20 seconds)
**Action**:
- Select option `3` (Withdraw)
- Enter amount: `2000`

**Say**: "Withdrawals must be in multiples of ₹100, just like a real ATM. The system enforces this rule automatically."

**What to show**:
- ✅ Success message
- Updated balance: ₹18,000.00

---

### Step 6: Demonstrate Input Validation (20 seconds)
**Action**:
- Select option `3` (Withdraw)
- Enter amount: `250` (not multiple of 100)

**Say**: "Watch what happens when we try to withdraw an invalid amount. The system catches the error and shows a helpful message - no crashes!"

**What to show**:
- ❌ Error: "Withdrawal amount must be in multiples of ₹100"
- Returns to menu gracefully

---

### Step 7: Transfer Funds (25 seconds)
**Action**:
- Select option `4` (Transfer)
- Enter destination: `ACC100002`
- Enter amount: `1000`

**Say**: "Fund transfers are atomic - both accounts update together or not at all. Notice the masked account number for security."

**What to show**:
- ✅ Transfer successful
- Destination shown as ACC****02
- Updated balance: ₹17,000.00

---

### Step 8: View Transaction History (20 seconds)
**Action**: Select option `5` (Transaction History)

**Say**: "Every operation is logged with timestamps. The history shows deposits with + and withdrawals with -, all in proper Indian currency format."

**What to show**:
- Complete transaction table
- Formatted dates and amounts
- Success/failure status (✓/✗)
- Total transaction count

---

### Step 9: Mini Statement (15 seconds)
**Action**: Select option `6` (Mini Statement)

**Say**: "The mini statement shows the last 5 transactions in a beautifully formatted table - perfect for quick reviews."

**What to show**:
- Boxed mini statement
- Last 5 transactions
- Current balance at top

---

### Step 10: Demonstrate Polymorphism (30 seconds)
**Action**:
- Logout (option 7)
- Login with CUST0002 / PIN 5678 (Current Account)
- Try to withdraw ₹60,000

**Say**: "This is polymorphism in action! Current accounts have different rules - they allow overdraft but have daily limits. Watch what happens when we exceed the ₹50,000 daily limit."

**What to show**:
- Login to current account
- Different account details (overdraft limit shown)
- ❌ Error: "Daily limit of ₹50,000.00 exceeded"
- System doesn't crash - returns to menu

---

## Closing Statement (10 seconds)

**Say**: "JavaBank ATM demonstrates enterprise-level Java development with OOP principles, design patterns, comprehensive exception handling, and a crash-proof architecture. All in pure Java with no external dependencies!"

---

## Bonus Demonstrations (If Time Permits)

### Bonus 1: Invalid PIN Attempts
- Try logging in with wrong PIN 3 times
- Show account lock message
- Demonstrates security features

### Bonus 2: Edge Case Handling
- Try entering letters for PIN → Shows "⚠ Please enter numbers only"
- Try negative amount → Shows "⚠ Amount cannot be negative"
- Try transfer to same account → Shows "❌ Cannot transfer to the same account"

### Bonus 3: Code Walkthrough
- Show the abstract Account class
- Show SavingsAccount and CurrentAccount with overridden withdraw()
- Explain polymorphism: same method, different behavior

---

## Key Points to Emphasize

1. **OOP Mastery**: Inheritance, Polymorphism, Abstraction, Encapsulation
2. **Design Patterns**: Singleton (BankDataStore), Template Method (Account)
3. **Crash-Proof**: Comprehensive exception handling, never exits unexpectedly
4. **Professional UI**: Indian currency format, masked account numbers, boxed displays
5. **Business Logic**: Different rules for different account types (polymorphism)
6. **Clean Code**: No magic numbers, proper separation of concerns, Javadoc comments
7. **Real-World Features**: Transaction history, mini statements, interest notices

---

## Troubleshooting During Demo

### If something goes wrong:
1. **Compilation error**: Check Java version (needs JDK 17+)
2. **ClassNotFoundException**: Ensure you're in the correct directory
3. **Demo accounts not showing**: Restart the application
4. **Input stuck**: Press Enter to continue

### Recovery Commands:
```bash
# Quick recompile
javac -d bin model/*.java exception/*.java util/*.java service/*.java Main.java

# Quick restart
java -cp bin Main
```

---

## Time Breakdown

| Step | Duration | Cumulative |
|------|----------|------------|
| 1. Startup | 15s | 0:15 |
| 2. Login | 20s | 0:35 |
| 3. Check Balance | 15s | 0:50 |
| 4. Deposit | 20s | 1:10 |
| 5. Withdraw | 20s | 1:30 |
| 6. Validation | 20s | 1:50 |
| 7. Transfer | 25s | 2:15 |
| 8. History | 20s | 2:35 |
| 9. Mini Statement | 15s | 2:50 |
| 10. Polymorphism | 30s | 3:20 |
| **Total** | **3:20** | **(with buffer)** |

---

## Practice Tips

1. **Run through the demo 2-3 times** before presenting
2. **Have the demo credentials visible** (CUST0001/1234, CUST0002/5678)
3. **Keep the terminal window large** for better visibility
4. **Speak clearly and at a moderate pace**
5. **Pause after each operation** to let the audience see the output
6. **Emphasize the "no crash" aspect** when showing error handling

---

## Audience Questions - Prepared Answers

**Q: "Does it use a database?"**  
A: No, it uses in-memory HashMap storage to keep it simple and portable. In production, you'd add JDBC/JPA.

**Q: "How is it crash-proof?"**  
A: Three layers: (1) Input validation that never throws, (2) Business logic exceptions, (3) Global exception handler in main loop.

**Q: "What OOP concepts does it demonstrate?"**  
A: All four pillars - Abstraction (Account class), Inheritance (SavingsAccount/CurrentAccount), Polymorphism (withdraw method), Encapsulation (private fields).

**Q: "Can I see the code?"**  
A: Absolutely! The project is well-documented with Javadoc comments. Let me show you the polymorphism in the Account classes.

**Q: "How long did it take to build?"**  
A: The complete system with all features, hardening, and documentation was built in 6 phases following professional development practices.

---

**Good luck with your demo! 🎯**
