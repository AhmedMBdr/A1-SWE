import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        BankAccount account = new BankAccount(name);
        int choice;
        double amount;
        do {
            System.out.println("\n=== BANK ACCOUNT MENU ===");
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");
            choice = scanner.nextInt();
            switch(choice) {
                case 1:
                    System.out.print("Enter amount to deposit: $");
                    amount = scanner.nextDouble();
                    if (amount > 0) {
                        account.deposit(amount);
                    } else {
                        System.out.println("Error: Deposit amount must be positive!");
                    }
                    break;
                    
                case 2:
                    System.out.print("Enter amount to withdraw: $");
                    amount = scanner.nextDouble();
                    account.withdraw(amount);
                    break;
                    
                case 3:
                    System.out.print("Account holder: " + name + " | ");
                    account.checkBalance();
                    break;
                    
                case 4:
                    System.out.println("Thank you for banking with us. Goodbye!");
                    break;
                    
                default:
                    System.out.println("Invalid choice. Please enter 1-4.");
            }
            
        } while (choice != 4);
        
        scanner.close();
    }
}