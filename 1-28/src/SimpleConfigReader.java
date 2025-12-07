import java.util.Properties;
import java.io.FileInputStream;

public class SimpleConfigReader {
    public static void main(String[] args) {
        Properties props = new Properties();
        
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);

            String user = props.getProperty("db.username");
            String password = props.getProperty("db.password");

            System.out.println("用户名: " + user);
            System.out.println("密码: " + password);
            
        } catch (Exception e) {
            System.out.println("读取配置文件失败: " + e.getMessage());
        }
    }
}