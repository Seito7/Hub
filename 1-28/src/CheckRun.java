import java.util.Scanner;

public class CheckRun {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("请输入一个年份: ");
    int year = scanner.nextInt();

    boolean isLeapYear = false;
    if (year % 4 == 0) {
        if (year % 100 == 0) {
            // 整百年份需要能被 400 整除才是闰年
            if (year % 400 == 0) {
                isLeapYear = true;
            }
        } else {
            // 非整百年份能被 4 整除就是闰年
            isLeapYear = true;
        }
    }

    if (isLeapYear) {
        System.out.println(year + " 年是闰年");
    } else {
        System.out.println(year + " 年不是闰年");
    }
    scanner.close();
}
}
