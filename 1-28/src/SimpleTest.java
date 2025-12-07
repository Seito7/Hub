public class SimpleTest {
    public static void main(String[] args) {
        SimpleBankAccount account = new SimpleBankAccount();

        account.deposit(1000);
        account.withdraw(300);
        account.checkBalance();
        account.withdraw(800); // 会显示余额不足
    }
}
class SimpleBankAccount {
    private double balance = 0;

    public void deposit(double amount) {
        balance += amount;
        System.out.println("存款: +" + amount);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("取款: -" + amount);
        } else {
            System.out.println("余额不足");
        }
    }

    public void checkBalance() {
        System.out.println("余额: " + balance);
    }
}
