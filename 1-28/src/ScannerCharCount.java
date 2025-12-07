import java.util.HashMap;
import java.util.Scanner;

public class ScannerCharCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入一个字符串: ");
        String str = scanner.nextLine();
        HashMap<Character, Integer> charCount = new HashMap<>();

        for (char c : str.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        System.out.println("\n字符串: \"" + str + "\"");
        System.out.println("字符出现次数统计:");
        for (char c : charCount.keySet()) {
            String displayChar = getDisplayChar(c);
            System.out.println(displayChar + ": " + charCount.get(c) + "次");
        }
        scanner.close();
    }
    private static String getDisplayChar(char c) {
        switch (c) {
            case ' ': return "[空格]";
            case '\t': return "[制表符]";
            case '\n': return "[换行符]";
            case '\r': return "[回车符]";
            default: return "'" + c + "'";
        }
    }
}