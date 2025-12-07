public class SimpleMultiThread {
    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println("线程1 - 数字: " + i);
            }
        }).start();
        new Thread(() -> {
            for (char c = 'A'; c <= 'J'; c++) {
                System.out.println("线程2 - 字母: " + c);
            }
        }).start();
    }
}