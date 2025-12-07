import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SimpleWordCount {
    public static void main(String[] args) {
        try {
            String content = new String(Files.readAllBytes(Paths.get("test.txt")));
            String[] words = content.split("\\s+");
            int wordCount = words.length;
            System.out.println("文件中的单词数量: " + wordCount);
        } catch (IOException e) {
            System.out.println("文件读取错误: " + e.getMessage());
        }
    }
}