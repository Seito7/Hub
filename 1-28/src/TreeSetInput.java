import java.util.*;

public class TreeSetInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TreeSet<Integer> numbers = new TreeSet<>();
        System.out.println("请输入整数（输入-1结束）");
        while (true) {
            System.out.print("输入数字: ");
            int num = scanner.nextInt();
            
            if (num == -1) {
                break;
            }
            numbers.add(num);
        }
        System.out.println("\n排序结果:");
        for (int n : numbers) {
            System.out.print(n + " ");
        }
        scanner.close();
    }
}