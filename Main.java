import java.util.Scanner;

class BankAccount {
    private double balance;
    private String pin;

    public BankAccount(double initialBalance, String pin) {
        this.balance = initialBalance;
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean verifyPin(String enteredPin) {
        return this.pin.equals(enteredPin);
    }
}

class ATM {
    private BankAccount account;
    private boolean isAuthenticated;

    public ATM(BankAccount account) {
        this.account = account;
        this.isAuthenticated = false;
    }

    public void authenticate() {
        Scanner Scanner = new Scanner(System.in);
        System.out.print("Enter your ATM pin: ");
        String enteredPin = Scanner.nextLine();

        if (account.verifyPin(enteredPin)) {
            this.isAuthenticated = true;
            System.out.println("Authentication successful!");
        } else {
            System.out.println("Invalid pin. Please try again.");
        }
    }

    public void displayMenu() {
        System.out.println("Welcome to the ATM!");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void withdraw(double amount) {
        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful. Your new balance is: $" + account.getBalance());
        } else {
            System.out.println("Withdrawal failed. Please check the amount and your balance.");
        }
    }

    public void deposit(double amount) {
        if (account.deposit(amount)) {
            System.out.println("Deposit successful. Your new balance is: $" + account.getBalance());
        } else {
            System.out.println("Deposit failed. Please enter a valid amount.");
        }
    }

    public void checkBalance() {
        System.out.println("Your current balance is: $" + account.getBalance());
    }

    public void run() {
        authenticate();
        if (!isAuthenticated) {
            return;
        }

        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            displayMenu();
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to withdraw: $");
                    double withdrawAmount = sc.nextDouble();
                    withdraw(withdrawAmount);
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: $");
                    double depositAmount = sc.nextDouble();
                    deposit(depositAmount);
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }

        sc.close();
    }
}

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(3000, "2003"); // Initial balance is $1000 and pin is "1234"
        ATM atm = new ATM(account);
        atm.run();
    }
}