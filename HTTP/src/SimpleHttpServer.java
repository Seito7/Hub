import java.io.*;
import java.net.*;

public class SimpleHttpServer {
    public static void main(String[] args) {
        final int PORT = 8000;
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("HTTP 服务器已启动，端口：" + PORT);

            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("客户端连接：" + client.getInetAddress());

                new Thread(() -> handleClient(client)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void handleClient(Socket client) {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream())
        ) {
            String requestLine = in.readLine();
            System.out.println("请求：" + requestLine);

            if (requestLine != null && requestLine.startsWith("GET")) {

                String responseBody = "<html><body><h1>Hello from Simple HTTP Server</h1></body></html>";

                out.println("HTTP/1.1 200 OK");
                out.println("Content-Type: text/html; charset=utf-8");
                out.println("Content-Length: " + responseBody.getBytes().length);
                out.println();
                out.println(responseBody);
                out.flush();

            } else {
                out.println("HTTP/1.1 405 Method Not Allowed");
                out.println("Content-Type: text/plain");
                out.println();
                out.println("Only GET supported");
                out.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try { client.close(); } catch (IOException ignored) {}
        }
    }
}