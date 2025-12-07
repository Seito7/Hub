import java.util.Scanner;
import java.nio.file.*;

public class FileTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入内容: ");
        String content = scanner.nextLine();
        try {
            Files.write(Paths.get("test.txt"), content.getBytes());
            System.out.println("写入成功！");
            String readContent = new String(Files.readAllBytes(Paths.get("test.txt")));
            System.out.println("读取内容: " + readContent);
            
        } catch (Exception e) {
            System.out.println("文件操作失败: " + e.getMessage());
        }
    }
}