import java.lang.reflect.Method;

class BankAccount {
    private double balance;
    public void deposit(double amount) {
        balance += amount;
        System.out.println("存款成功: +" + amount + ", 余额: " + balance);
    }
}

public class ReflectionTest {
    public static void main(String[] args) throws Exception {
        BankAccount account = new BankAccount();
        Class<?> clazz = account.getClass();
        Method method = clazz.getMethod("deposit", double.class);
        method.invoke(account, 1000.0);
    }
}