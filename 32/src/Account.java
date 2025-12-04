import java.io.Serializable;

public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String accountId;
    private final String username;
    private double balance;

    public Account(String accountId, String username, double balance) {
        this.accountId = accountId;
        this.username = username;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getUsername() {
        return username;
    }

    public synchronized double getBalance() {
        return balance;
    }

    public synchronized void deposit(double amount) {
        balance += amount;
    }

    public synchronized boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "AccountID: " + accountId + ", 用户名: " + username + ", 余额: " + balance;
    }
}