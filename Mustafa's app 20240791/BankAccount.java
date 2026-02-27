public class BankAccount {
    private String customerName;
    private double balance;

    public BankAccount(String name) {
        this.customerName = name;
        this.balance = 0.0;
    }

    public void deposit(double amount) {
            // the amount will be availabe to enter in the screen must be positive if we suppose this is a bank system
            /*
            i can create an "if else" statement to check if the entered number is positive or negative . 
             */
            balance = balance + amount;
            System.out.println("Successfully deposited: " + amount);
       
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance = balance - amount;
            System.out.println("Successfully withdrew: " + amount);
        } else {
            System.out.println("Invalid amound to withdraw");
            /*to avoid any more invalid attempts */
            System.out.println("The maximum amount you can withdraw is: " + balance); 
        }
    }
    
    public void checkBalance() {
        System.out.println("Current Balance: " + balance);
    }
}