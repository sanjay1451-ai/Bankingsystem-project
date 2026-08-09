import java.util.HashMap;
import java.util.Scanner;

class Account {

    int accountId;
    String name;
    double balance;

    public Account(int accountId, String name, double balance) {
        this.accountId = accountId;
        this.name = name;
        this.balance = balance;
    }
}
public class Accountcreation {

    static HashMap<Integer, Account> accounts = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void createAccount() {

        System.out.print("Enter Account ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (accounts.containsKey(id)) {
            System.out.println("Account already exists.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();

        Account account = new Account(id, name, balance);

        accounts.put(id, account);

        System.out.println("Account created successfully.");
    }

    public static void main(String[] args) {

        createAccount();

    }
}
