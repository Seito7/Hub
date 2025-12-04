import java.util.Scanner;

public class Main {

    private static final Bank bank = new Bank();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n银行账户管理系统:");
            System.out.println("1. 创建账户");
            System.out.println("2. 存款");
            System.out.println("3. 取款");
            System.out.println("4. 查询余额");
            System.out.println("5. 显示所有账户");
            System.out.println("6. 多线程测试");
            System.out.println("0. 退出");
            System.out.print("请选择: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> deposit();
                case 3 -> withdraw();
                case 4 -> query();
                case 5 -> bank.showAll();
                case 6 -> multiThreadTest();
                case 0 -> {
                    System.out.println("系统已退出。");
                    return;
                }
                default -> System.out.println("无效选择！");
            }
        }
    }

    private static void createAccount() {
        System.out.print("输入用户名: ");
        String name = sc.nextLine();
        System.out.print("初始金额: ");
        double bal = sc.nextDouble();
        String id = bank.createAccount(name, bal);
        System.out.println("账户创建成功！账号：" + id);
    }

    private static void deposit() {
        System.out.print("输入账户ID: ");
        String id = sc.nextLine();
        System.out.print("存款金额: ");
        double a = sc.nextDouble();
        bank.deposit(id, a);
        System.out.println("存款成功！");
    }

    private static void withdraw() {
        System.out.print("输入账户ID: ");
        String id = sc.nextLine();
        System.out.print("取款金额: ");
        double a = sc.nextDouble();
        boolean ok = bank.withdraw(id, a);
        System.out.println(ok ? "取款成功！" : "余额不足！");
    }

    private static void query() {
        System.out.print("输入账户ID: ");
        String id = sc.nextLine();
        Account acc = bank.getAccount(id);
        System.out.println(acc != null ? acc : "账户不存在！");
    }

    // 多线程测试
    private static void multiThreadTest() {
        System.out.print("输入测试账户ID: ");
        String id = sc.nextLine();

        Thread t1 = new Thread(() -> bank.deposit(id, 100));
        Thread t2 = new Thread(() -> bank.withdraw(id, 50));
        Thread t3 = new Thread(() -> bank.deposit(id, 200));

        t1.start();
        t2.start();
        t3.start();

        try { t1.join(); t2.join(); t3.join(); } catch (InterruptedException ignored) {}

        System.out.println("多线程测试结束：");
        System.out.println(bank.getAccount(id));
    }
}