import java.util.*;

class Product {
    int id;
    String name;
    double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

public class ProductManager {
    static List<Product> products = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // 初始化几个商品
        products.add(new Product(1, "苹果", 5.5));
        products.add(new Product(2, "香蕉", 3.0));

        while(true) {
            System.out.println("\n1.查看商品 2.添加商品 3.退出");
            System.out.print("请选择:");
            int choice = scanner.nextInt();

            switch(choice) {
                case 1:
                    showProducts();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    return;
            }
        }
    }

    static void showProducts() {
        System.out.println("\n商品列表:");
        for(Product p : products) {
            System.out.println(p.id + " " + p.name + " ￥" + p.price);
        }
    }
    static void addProduct() {
        System.out.print("输入商品ID:");
        int id = scanner.nextInt();
        System.out.print("输入商品名称:");
        String name = scanner.next();
        System.out.print("输入商品价格:");
        double price = scanner.nextDouble();

        products.add(new Product(id, name, price));
        System.out.println("添加成功!");
    }
}