import java.util.Properties;
import java.io.*;

public class SmartConfigManager {
    public static void main(String[] args) {
        String configFile = "config.properties";
        
        // 检查文件是否存在，不存在则创建
        if (!new File(configFile).exists()) {
            System.out.println("配置文件不存在，正在创建...");
            createDefaultConfig(configFile);
        }
        
        // 读取配置
        readConfig(configFile);
    }
    
    public static void createDefaultConfig(String filename) {
        Properties props = new Properties();
        props.setProperty("db.url", "jdbc:mysql://localhost:3306/mydb");
        props.setProperty("db.username", "admin");
        props.setProperty("db.password", "password");
        props.setProperty("app.name", "Java应用");
        props.setProperty("app.version", "1.0.0");
        props.setProperty("server.port", "8080");
        props.setProperty("debug.mode", "false");
        
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            props.store(fos, "自动创建的配置文件");
            System.out.println("✅ 配置文件创建成功: " + new File(filename).getAbsolutePath());
        } catch (Exception e) {
            System.out.println("❌ 创建失败: " + e.getMessage());
        }
    }
    
    public static void readConfig(String filename) {
        Properties props = new Properties();
        
        try (FileInputStream fis = new FileInputStream(filename)) {
            props.load(fis);
            
            System.out.println("\n📋 配置文件内容:");
            System.out.println("📍 文件位置: " + new File(filename).getAbsolutePath());
            System.out.println("🔗 数据库URL: " + props.getProperty("db.url"));
            System.out.println("👤 用户名: " + props.getProperty("db.username"));
            System.out.println("🛡️  密码: " + props.getProperty("db.password"));
            System.out.println("📱 应用名称: " + props.getProperty("app.name"));
            System.out.println("🔢 版本: " + props.getProperty("app.version"));
            
        } catch (Exception e) {
            System.out.println("❌ 读取失败: " + e.getMessage());
        }
    }
}