import java.util.Scanner;

class NegativeNumberException extends Exception {
    public NegativeNumberException(String msg) { super(msg); }
}

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("输入一个正数: ");
        int num = scanner.nextInt();
        
        try {
            if (num < 0) {
                throw new NegativeNumberException("不能是负数: " + num);
            }
            System.out.println("输入正确: " + num);
        } catch (NegativeNumberException e) {
            System.out.println(e.getMessage());
        }
    }
}