public class Main {
    public static void main(String[] args) {
        try {
            LibraryManagementSystem system = new LibraryManagementSystem();
            system.run();
        } catch (Exception e) {
            System.out.println("系统启动失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}