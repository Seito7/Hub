import java.io.*;
import java.net.*;

public class SimpleTCPServer {
    public static void main(String[] args) {
        int port = 6666;
        try {
            // 1. 创建服务器Socket
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("服务器启动，等待客户端连接...");
            // 2. 等待客户端连接
            Socket clientSocket = serverSocket.accept();
            System.out.println("客户端已连接: " + clientSocket.getInetAddress());
            
            // 3. 获取输入输出流
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(
                clientSocket.getOutputStream(), true);
            
            // 4. 向客户端发送欢迎消息
            out.println("欢迎连接到TCP服务器！");
            out.println("输入 'bye' 可以退出");
            
            // 5. 接收和发送消息
            String clientMessage;
            while ((clientMessage = in.readLine()) != null) {
                System.out.println("客户端说: " + clientMessage);
                
                if (clientMessage.equalsIgnoreCase("bye")) {
                    out.println("再见！");
                    break;
                }
                
                // 简单的回应：把消息原样返回
                out.println("服务器收到: " + clientMessage);
            }
            
            // 6. 关闭连接
            System.out.println("客户端断开连接");
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
            
        } catch (IOException e) {
            System.out.println("服务器错误: " + e.getMessage());
        }
    }
}