public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world"); 
        BankAccount account = new BankAccount("John");
        account.deposit(1000);
        account.checkBalance();
        account.withdraw(500);
        account.checkBalance();
    }
}