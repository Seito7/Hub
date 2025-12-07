import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SimpleTCPClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 8888;
        try {
            // 1. 连接到服务器
            System.out.println("正在连接到服务器...");
            Socket socket = new Socket(serverAddress, port);
            System.out.println("已连接到服务器");
            // 2. 获取输入输出流
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(
                socket.getOutputStream(), true);
            // 3. 创建一个Scanner读取用户输入
            Scanner scanner = new Scanner(System.in);
            // 4. 首先显示服务器的欢迎消息
            System.out.println("服务器说: " + in.readLine());
            System.out.println("服务器说: " + in.readLine());
            // 5. 与服务器对话
            String userInput;
            System.out.print("你: ");
            while ((userInput = scanner.nextLine()) != null) {
                // 发送消息到服务器
                out.println(userInput);
                // 如果用户输入"bye"，退出循环
                if (userInput.equalsIgnoreCase("bye")) {
                    break;
                }
                // 接收服务器的回复
                System.out.println("服务器说: " + in.readLine());
                System.out.print("你: ");
            }
            // 6. 关闭连接
            System.out.println("连接关闭");
            scanner.close();
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("客户端错误: " + e.getMessage());
        }
    }
}