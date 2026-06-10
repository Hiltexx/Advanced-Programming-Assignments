import java.util.*;

// ---------------- BASE CLASS ----------------
class Account {
    private String accountNumber;
    private String ownerName;
    private double balance;

    // Constructor 1 (Default)
    public Account() {
        this("0000", "Unknown", 0.0); // chaining
    }

    // Constructor 2 (Parameterized)
    public Account(String accountNumber, String ownerName, double balance) {
        if (accountNumber == null || accountNumber.isBlank())
            throw new IllegalArgumentException("Invalid account number");

        if (ownerName == null || ownerName.isBlank())
            throw new IllegalArgumentException("Invalid owner name");

        if (balance < 0)
            throw new IllegalArgumentException("Balance cannot be negative");

        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    // Getters
    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public double getBalance() { return balance; }

    // Setter
    public void setOwnerName(String ownerName) {
        if (ownerName == null || ownerName.isBlank())
            throw new IllegalArgumentException("Invalid name");
        this.ownerName = ownerName;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    // Deposit
    public void deposit(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Deposit must be positive");

        balance += amount;
        System.out.println("Deposited: Rs. " + amount);
    }

    // Withdraw
    public void withdraw(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Invalid withdrawal");

        if (amount > balance)
            throw new IllegalStateException("Insufficient balance");

        balance -= amount;
        System.out.println("Withdrawn: Rs. " + amount);
    }

    // Display
    public void display() {
        System.out.println("Account No : " + accountNumber);
        System.out.println("Owner      : " + ownerName);
        System.out.println("Balance    : Rs. " + balance);
    }
}

// ---------------- SAVINGS ACCOUNT ----------------
class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount() {
        this("0000", "Unknown", 0.0, 0.0);
    }

    public SavingsAccount(String accNo, String name, double bal, double rate) {
        super(accNo, name, bal);
        this.interestRate = rate;
    }

    public double calculateInterest() {
        return getBalance() * interestRate / 100;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Type       : Savings");
        System.out.println("Interest % : " + interestRate);
        System.out.println("Interest   : Rs. " + calculateInterest());
    }
}

// ---------------- CURRENT ACCOUNT ----------------
class CurrentAccount extends Account {
    private double overdraftLimit;

    public CurrentAccount() {
        this("0000", "Unknown", 0.0, 0.0);
    }

    public CurrentAccount(String accNo, String name, double bal, double limit) {
        super(accNo, name, bal);
        this.overdraftLimit = limit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Invalid withdrawal");

        if (amount > getBalance() + overdraftLimit)
            throw new IllegalStateException("Overdraft limit exceeded");

        setBalance(getBalance() - amount);
        System.out.println("Withdrawn (OD): Rs. " + amount);
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Type       : Current");
        System.out.println("OD Limit   : Rs. " + overdraftLimit);
    }
}

// ---------------- MAIN CLASS ----------------
public class assignment_9 {

    static Scanner sc = new Scanner(System.in);
    static List<Account> accounts = new ArrayList<>();

    // Preload data
    static void preload() {
        accounts.add(new SavingsAccount("SA101", "Hilton", 10000, 5));
        accounts.add(new SavingsAccount("SA102", "Alice", 8000, 4));
        accounts.add(new CurrentAccount("CA201", "John", 5000, 3000));
        accounts.add(new CurrentAccount("CA202", "Rahul", 7000, 2000));
    }

    // View accounts (Polymorphism)
    static void viewAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        for (Account acc : accounts) {
            acc.display();
            System.out.println("----------------------");
        }
    }

    // Add account
    static void addAccount() {
        try {
            System.out.println("1. Savings Account");
            System.out.println("2. Current Account");
            int choice = sc.nextInt();
            sc.nextLine();

            System.out.print("Account No: ");
            String accNo = sc.nextLine();

            System.out.print("Owner Name: ");
            String name = sc.nextLine();

            System.out.print("Balance: ");
            double bal = sc.nextDouble();

            if (choice == 1) {
                System.out.print("Interest Rate: ");
                double rate = sc.nextDouble();
                accounts.add(new SavingsAccount(accNo, name, bal, rate));

            } else if (choice == 2) {
                System.out.print("Overdraft Limit: ");
                double limit = sc.nextDouble();
                accounts.add(new CurrentAccount(accNo, name, bal, limit));
            }

            System.out.println("Account added successfully!");

        } catch (Exception e) {
            System.out.println("Invalid input.");
            sc.nextLine();
        }
    }

    // Find account
    static Account findAccount(String accNo) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accNo))
                return acc;
        }
        return null;
    }

    // Deposit / Withdraw
    static void transact(boolean isDeposit) {
        System.out.print("Enter Account No: ");
        String accNo = sc.next();

        Account acc = findAccount(accNo);

        if (acc == null) {
            System.out.println("Account not found");
            return;
        }

        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();

        try {
            if (isDeposit)
                acc.deposit(amount);
            else
                acc.withdraw(amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Main menu
    public static void main(String[] args) {

        preload();

        while (true) {
            System.out.println("\n===== BANK SYSTEM =====");
            System.out.println("1. View Accounts");
            System.out.println("2. Add Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> viewAccounts();
                case 2 -> addAccount();
                case 3 -> transact(true);
                case 4 -> transact(false);
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }
}
