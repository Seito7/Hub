import java.util.Scanner;

public class Calculate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.加法  2.减法  3.乘法  4.除法");
        System.out.print("请输入你想要的计算方式: ");
        char operator = scanner.next().charAt(0);
        System.out.print("请输入第一个数字: ");
        double num1 = scanner.nextDouble();
        System.out.print("请输入第二个数字: ");
        double num2 = scanner.nextDouble();
        double result;
        switch (operator) {
            case '1':
                result = num1 + num2;
                break;
            case '2':
                result = num1 - num2;
                break;
            case '3':
                result = num1 * num2;
                break;
            case '4':
                if (num2 == 0) {
                    System.out.println("错误：除数不能为 0！");
                    scanner.close();
                    return;
                }
                result = num1 / num2;
                break;
            default:
                System.out.println("错误：无效的运算！");
                scanner.close();
                return;
        }
        System.out.printf("计算结果: %.2f ",result);
        scanner.close();
    }
}