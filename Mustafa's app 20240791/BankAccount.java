import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BankAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String accountNumber;
    private String customerName;
    private String pin;
    private double balance;
    private List<String> transactionHistory;

    public BankAccount(String accountNumber, String name, String pin) {
        this.accountNumber = accountNumber;
        this.customerName = name;
        this.pin = pin;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
        
        transactionHistory.add("Account created with balance: $0.0");
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public boolean verifyPin(String inputPin) {
        return this.pin.equals(inputPin);
    }
    
    public boolean changePin(String oldPin, String newPin) {
        if (verifyPin(oldPin)) {
            this.pin = newPin;
            transactionHistory.add("PIN successfully changed.");
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: $" + amount);
            transactionHistory.add("Deposited: +$" + amount + " | New Balance: $" + balance);
        } else {
            System.out.println("Error: Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew: $" + amount);
            transactionHistory.add("Withdrew: -$" + amount + " | New Balance: $" + balance);
        } else if (amount <= 0) {
            System.out.println("Error: Withdrawal amount must be positive.");
        } else {
            System.out.println("Invalid amount. Insufficient funds.");
            System.out.println("The maximum amount you can withdraw is: $" + balance); 
        }
    }
    
    public void transferTo(BankAccount targetAccount, double amount) {
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
            targetAccount.balance += amount;
            
            System.out.println("Successfully transferred $" + amount + " to " + targetAccount.getCustomerName());
            
            this.transactionHistory.add("Transferred: -$" + amount + " to Account: " + targetAccount.getAccountNumber() + " | New Balance: $" + balance);
            targetAccount.transactionHistory.add("Received: +$" + amount + " from Account: " + this.accountNumber + " | New Balance: $" + targetAccount.balance);
        } else if (amount <= 0) {
            System.out.println("Error: Transfer amount must be positive.");
        } else {
            System.out.println("Invalid amount. Insufficient funds.");
        }
    }
    
    public void checkBalance() {
        System.out.println("Current Balance: $" + balance);
    }

    public void printStatement() {
        System.out.println("\n--- Transaction History for " + customerName + " ---");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
        System.out.println("----------------------------------------");
    }
}