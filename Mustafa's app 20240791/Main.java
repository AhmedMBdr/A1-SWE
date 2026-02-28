import java.io.*;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static HashMap<String, BankAccount> bankDatabase = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final String DATA_FILE = "bankData.txt";

    public static void main(String[] args) {
        loadData();
        
        int mainChoice;

        do {
            System.out.println("\n=== WELCOME TO THE BANK ===");
            System.out.println("1. Create New Account");
            System.out.println("2. Login to Existing Account");
            System.out.println("3. Exit Bank");
            System.out.print("Enter your choice (1-3): ");
            mainChoice = scanner.nextInt();
            scanner.nextLine(); 

            switch(mainChoice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Saving data... Thank you for banking with us. Goodbye!");
                    saveData();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1-3.");
            }
        } while (mainChoice != 3);
        
        scanner.close();
    }

    private static void createAccount() {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        
        System.out.print("Create a 4-digit PIN: ");
        String pin = scanner.nextLine();

        Random rand = new Random();
        String accNum = String.format(java.util.Locale.US, "%06d", rand.nextInt(1000000));
        
        while (bankDatabase.containsKey(accNum)) {
            accNum = String.format(java.util.Locale.US, "%06d", rand.nextInt(1000000));
        }

        BankAccount newAccount = new BankAccount(accNum, name, pin);
        bankDatabase.put(accNum, newAccount);

        System.out.println("\nAccount successfully created!");
        System.out.println("Your Account Number is: " + accNum);
        System.out.println("Please keep this number and your PIN safe.");
        saveData();
    }

    private static void login() {
        System.out.print("Enter Account Number: ");
        String accNum = scanner.nextLine();

        if (bankDatabase.containsKey(accNum)) {
            BankAccount account = bankDatabase.get(accNum);
            
            System.out.print("Enter PIN: ");
            String enteredPin = scanner.nextLine();

            if (account.verifyPin(enteredPin)) {
                System.out.println("\nLogin successful. Welcome, " + account.getCustomerName() + "!");
                accountMenu(account); 
            } else {
                System.out.println("Error: Incorrect PIN.");
            }
        } else {
            System.out.println("Error: Account not found.");
        }
    }

    private static void accountMenu(BankAccount account) {
        int choice;
        double amount;
        
        do {
            System.out.println("\n=== ACCOUNT MENU ===");
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Check Balance");
            System.out.println("4. View Transaction History");
            System.out.println("5. Logout");
            System.out.print("Enter your choice (1-5): ");
            choice = scanner.nextInt();
            
            switch(choice) {
                case 1:
                    System.out.print("Enter amount to deposit: $");
                    amount = scanner.nextDouble();
                    account.deposit(amount);
                    saveData();
                    break;
                    
                case 2:
                    System.out.print("Enter amount to withdraw: $");
                    amount = scanner.nextDouble();
                    account.withdraw(amount);
                    saveData();
                    break;
                    
                case 3:
                    System.out.print("Account holder: " + account.getCustomerName() + " | ");
                    account.checkBalance();
                    break;
                
                case 4:
                    account.printStatement();
                    break;
                    
                case 5:
                    System.out.println("Logging out...");
                    break;
                    
                default:
                    System.out.println("Invalid choice. Please enter 1-5.");
            }
        } while (choice != 5);
    }
    private static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(bankDatabase);
        } catch (IOException e) {
            System.out.println("Error saving bank data: " + e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    private static void loadData() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
                bankDatabase = (HashMap<String, BankAccount>) ois.readObject();
                System.out.println("Bank data loaded successfully.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading bank data: " + e.getMessage());
                bankDatabase = new HashMap<>(); 
            }
        } else {
            System.out.println("No existing bank data found. Starting a fresh database.");
        }
    }
}