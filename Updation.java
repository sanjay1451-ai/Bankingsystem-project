import java.util.*;

class Account {

    int id;
    String customerName;
    double balance;

    public Account(int id, String customerName, double balance) {
        this.id = id;
        this.customerName = customerName;
        this.balance = balance;
    }
}

class AccountNotFoundException extends Exception {

    public AccountNotFoundException(String message) {
        super(message);
    }
}

class InsufficientFundsException extends Exception {

    public InsufficientFundsException(String message) {
        super(message);
    }
}

public class Updation{

    static HashMap<Integer, Account> accounts = new HashMap<>();
    static HashSet<Integer> usedIds = new HashSet<>();

    static Scanner sc = new Scanner(System.in);

    public static void createAccount() {

        System.out.print("Enter account ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (usedIds.contains(id)) {
            System.out.println("This account ID has already been used.");
            return;
        }

        System.out.print("Enter customer name: ");
        String name = sc.nextLine();

        System.out.print("Enter initial balance: ");
        double balance = sc.nextDouble();

        if (balance < 0) {
            System.out.println("Balance cannot be negative.");
            return;
        }

        Account account = new Account(id, name, balance);

        accounts.put(id, account);
        usedIds.add(id);

        System.out.println("Account created successfully.");
    }

    public static void deposit() {

        try {

            System.out.print("Enter account ID: ");
            int id = sc.nextInt();

            if (!accounts.containsKey(id)) {
                throw new AccountNotFoundException("Account not found.");
            }

            System.out.print("Enter amount: ");
            double amount = sc.nextDouble();

            if (amount <= 0) {
                System.out.println("Invalid amount.");
                return;
            }

            Account account = accounts.get(id);

            account.balance += amount;

            System.out.println("Deposit successful.");

        } catch (AccountNotFoundException e) {

            System.out.println(e.getMessage());
        }
    }

    public static void withdraw() {

        try {

            System.out.print("Enter account ID: ");
            int id = sc.nextInt();

            if (!accounts.containsKey(id)) {
                throw new AccountNotFoundException("Account not found.");
            }

            System.out.print("Enter amount: ");
            double amount = sc.nextDouble();

            if (amount <= 0) {
                System.out.println("Invalid amount.");
                return;
            }

            Account account = accounts.get(id);

            if (amount > account.balance) {
                throw new InsufficientFundsException(
                        "Insufficient balance.");
            }

            account.balance -= amount;

            System.out.println("Withdrawal successful.");

        } catch (AccountNotFoundException |
                 InsufficientFundsException e) {

            System.out.println(e.getMessage());
        }
    }

    public static void checkBalance() {

        try {

            System.out.print("Enter account ID: ");
            int id = sc.nextInt();

            if (!accounts.containsKey(id)) {
                throw new AccountNotFoundException(
                        "Account not found.");
            }

            Account account = accounts.get(id);

            System.out.println("Customer Name: "
                    + account.customerName);

            System.out.println("Balance: ₹"
                    + account.balance);

        } catch (AccountNotFoundException e) {

            System.out.println(e.getMessage());
        }
    }

    public static void closeAccount() {

        try {

            System.out.print("Enter account ID: ");
            int id = sc.nextInt();

            if (!accounts.containsKey(id)) {
                throw new AccountNotFoundException(
                        "Account not found.");
            }

            accounts.remove(id);

            System.out.println(
                    "Account closed successfully.");

        } catch (AccountNotFoundException e) {

            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

        while (true) {

            try {

                System.out.println("\n--- BANK MENU ---");
                System.out.println("1. Create Account");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Check Balance");
                System.out.println("5. Close Account");
                System.out.println("6. Exit");

                System.out.print("Enter your choice: ");

                int choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        createAccount();
                        break;

                    case 2:
                        deposit();
                        break;

                    case 3:
                        withdraw();
                        break;

                    case 4:
                        checkBalance();
                        break;

                    case 5:
                        closeAccount();
                        break;

                    case 6:
                        System.out.println("Thank you!");
                        System.exit(0);

                    default:
                        System.out.println(
                                "Invalid choice.");
                }

            } catch (InputMismatchException e) {

                System.out.println(
                        "Please enter valid input.");

                sc.nextLine();
            }
        }
    }
}
