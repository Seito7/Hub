import java.util.Scanner;

public class CircleArea {
    public static void main(String[] args) {
        final double PI = 3.1415926;
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入圆的半径: ");
        double radius = scanner.nextDouble();
        double area = PI * radius * radius;
        System.out.printf("圆的面积为: %.2f%n", area);
        scanner.close();
    }
}