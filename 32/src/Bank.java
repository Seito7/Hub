import java.io.*;
import java.util.*;

public class Bank {

    private static final String FILE_NAME = "accounts.dat";
    private final Map<String, Account> accounts;

    @SuppressWarnings("unchecked")
    public Bank() {
        // 读取持久化数据
        Map<String, Account> loaded;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            loaded = (Map<String, Account>) in.readObject();
        } catch (Exception e) {
            loaded = new HashMap<>();
        }
        accounts = loaded;
    }

    // 保存账户数据到文件
    private synchronized void save() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(accounts);
        } catch (IOException e) {
            System.out.println("保存失败: " + e.getMessage());
        }
    }

    // 创建账户
    public synchronized String createAccount(String username, double initBalance) {
        String id = UUID.randomUUID().toString().substring(0, 8);
        Account acc = new Account(id, username, initBalance);
        accounts.put(id, acc);
        save();
        return id;
    }

    // 存款
    public void deposit(String id, double amount) {
        Account acc = accounts.get(id);
        if (acc != null) {
            synchronized (acc) { acc.deposit(amount); }
            save();
        }
    }

    // 取款
    public boolean withdraw(String id, double amount) {
        Account acc = accounts.get(id);
        if (acc != null) {
            synchronized (acc) {
                if (acc.withdraw(amount)) {
                    save();
                    return true;
                }
            }
        }
        return false;
    }

    // 查询
    public Account getAccount(String id) {
        return accounts.get(id);
    }

    // 显示全部账户
    public void showAll() {
        for (Account acc : accounts.values()) {
            System.out.println(acc);
        }
    }
}