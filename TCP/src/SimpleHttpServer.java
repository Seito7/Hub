import java.io.*;
import java.net.*;
import java.util.*;

public class SimpleHttpServer {
    private static final int PORT = 8080;
    
    public static void main(String[] args) {
        System.out.println("启动HTTP服务器，监听端口: " + PORT);
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                // 等待客户端连接
                Socket clientSocket = serverSocket.accept();
                System.out.println("收到来自 " + clientSocket.getInetAddress() + " 的连接");
                
                // 处理请求
                handleRequest(clientSocket);
            }
        } catch (IOException e) {
            System.err.println("服务器错误: " + e.getMessage());
        }
    }
    
    private static void handleRequest(Socket clientSocket) {
        try (
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            // 读取HTTP请求的第一行
            String requestLine = in.readLine();
            if (requestLine == null) return;
            
            System.out.println("请求: " + requestLine);
            
            // 解析请求行
            String[] parts = requestLine.split(" ");
            if (parts.length < 2) {
                sendError(out, 400, "Bad Request");
                return;
            }
            
            String method = parts[0];  // GET, POST等
            String path = parts[1];    // 请求路径
            
            // 只处理GET请求
            if (!method.equals("GET")) {
                sendError(out, 405, "Method Not Allowed");
                return;
            }
            
            // 读取所有请求头（直到空行）
            String header;
            while ((header = in.readLine()) != null) {
                if (header.isEmpty()) break;  // 空行表示请求头结束
                System.out.println("请求头: " + header);
            }
            
            // 处理请求并发送响应
            handleGetRequest(path, out);
            
        } catch (IOException e) {
            System.err.println("处理请求时出错: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static void handleGetRequest(String path, PrintWriter out) {
        // 根据不同路径返回不同内容
        if (path.equals("/") || path.equals("/index.html")) {
            sendHtmlResponse(out, "欢迎页面", 
                "<html><body>" +
                "<h1>欢迎使用简单HTTP服务器</h1>" +
                "<p>这是一个简单的HTTP服务器实现</p>" +
                "<ul>" +
                "<li><a href='/'>首页</a></li>" +
                "<li><a href='/time'>当前时间</a></li>" +
                "<li><a href='/info'>服务器信息</a></li>" +
                "<li><a href='/hello'>Hello World</a></li>" +
                "</ul>" +
                "</body></html>");
        } else if (path.equals("/time")) {
            String currentTime = new Date().toString();
            sendHtmlResponse(out, "当前时间", 
                "<html><body>" +
                "<h1>当前服务器时间</h1>" +
                "<p>" + currentTime + "</p>" +
                "<a href='/'>返回首页</a>" +
                "</body></html>");
        } else if (path.equals("/info")) {
            sendHtmlResponse(out, "服务器信息",
                "<html><body>" +
                "<h1>服务器信息</h1>" +
                "<p>这是一个用Java实现的简单HTTP服务器</p>" +
                "<p>端口: " + PORT + "</p>" +
                "<a href='/'>返回首页</a>" +
                "</body></html>");
        } else if (path.equals("/hello")) {
            sendPlainTextResponse(out, "Hello, World!");
        } else if (path.equals("/json")) {
            sendJsonResponse(out, "{\"message\": \"Hello JSON\", \"status\": \"success\"}");
        } else {
            sendError(out, 404, "Not Found");
        }
    }
    
    private static void sendHtmlResponse(PrintWriter out, String title, String content) {
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/html; charset=UTF-8");
        out.println("Connection: close");
        out.println();  // 空行分隔头部和主体
        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>" + title + "</title></head>");
        out.println("<body>" + content + "</body></html>");
    }
    
    private static void sendPlainTextResponse(PrintWriter out, String text) {
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/plain; charset=UTF-8");
        out.println("Connection: close");
        out.println();  // 空行分隔头部和主体
        out.println(text);
    }
    
    private static void sendJsonResponse(PrintWriter out, String json) {
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: application/json; charset=UTF-8");
        out.println("Connection: close");
        out.println();  // 空行分隔头部和主体
        out.println(json);
    }
    
    private static void sendError(PrintWriter out, int statusCode, String message) {
        String statusText;
        switch (statusCode) {
            case 400: statusText = "Bad Request"; break;
            case 404: statusText = "Not Found"; break;
            case 405: statusText = "Method Not Allowed"; break;
            default: statusText = "Internal Server Error";
        }
        
        out.println("HTTP/1.1 " + statusCode + " " + statusText);
        out.println("Content-Type: text/html; charset=UTF-8");
        out.println("Connection: close");
        out.println();  // 空行分隔头部和主体
        out.println("<html><body>");
        out.println("<h1>" + statusCode + " " + statusText + "</h1>");
        out.println("<p>" + message + "</p>");
        out.println("<a href='/'>返回首页</a>");
        out.println("</body></html>");
    }
}