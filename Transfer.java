import java.util.HashMap;

public class Transfer {

    public static void transfer(
            HashMap<Integer, Account> accounts,
            int fromId,
            int toId,
            double amount)
            throws AccountNotFoundException,
                   InsufficientFundsException {

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        if (!accounts.containsKey(fromId)) {
            throw new AccountNotFoundException(
                    "Sender account not found.");
        }

        if (!accounts.containsKey(toId)) {
            throw new AccountNotFoundException(
                    "Receiver account not found.");
        }

        Account sender = accounts.get(fromId);
        Account receiver = accounts.get(toId);

        if (sender.balance < amount) {
            throw new InsufficientFundsException(
                    "Insufficient funds.");
        }

        
        sender.balance -= amount;
        receiver.balance += amount;

        System.out.println("Transfer successful.");
        System.out.println(
                "Sender balance: ₹" + sender.balance);
        System.out.println(
                "Receiver balance: ₹" + receiver.balance);
    }
}