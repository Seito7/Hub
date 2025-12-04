import java.util.List;

public class ShapeTest {
    public static void main(String[] args) {
        ShapeManager manager = new ShapeManager();
        
        System.out.println("图形计算系统测试：\n");
        
        try {
            Shape circle = new Circle(5.0);
            Shape rectangle = new Rectangle(4.0, 6.0);
            Shape triangle = new Triangle(3.0, 4.0, 5.0);

            manager.addShape(circle);
            manager.addShape(rectangle);
            manager.addShape(triangle);

            manager.addShape(new Circle(3.0));
            manager.addShape(new Rectangle(2.0, 2.0));

            manager.displayAllShapes();

            System.out.println("\n统计信息：");
            System.out.printf("图形总数: %d%n", manager.getShapeCount());
            System.out.printf("总面积: %.2f%n", manager.calculateTotalArea());
            System.out.printf("总周长: %.2f%n", manager.calculateTotalPerimeter());

            System.out.println("\n圆形信息：");
            List<Shape> circles = manager.getShapesByName("圆形");
            for (Shape circleShape : circles) {
                circleShape.displayInfo();
            }
            System.out.println("\n异常处理测试：");
            try {
                Shape invalidCircle = new Circle(-1.0);
            } catch (IllegalArgumentException e) {
                System.out.println("异常捕获: " + e.getMessage());
            }
            
            try {
                Shape invalidTriangle = new Triangle(1.0, 2.0, 5.0);
            } catch (IllegalArgumentException e) {
                System.out.println("异常捕获: " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.out.println("程序运行出错: " + e.getMessage());
        }
    }
}