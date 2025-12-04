import java.util.ArrayList;
import java.util.List;

public class ShapeManager {
    private List<Shape> shapes;
    
    public ShapeManager() {
        this.shapes = new ArrayList<>();
    }

    public void addShape(Shape shape) {
        if (shape != null) {
            shapes.add(shape);
            System.out.println("成功添加: " + shape.getShapeName());
        }
    }

    public void displayAllShapes() {
        if (shapes.isEmpty()) {
            System.out.println("当前没有图形");
            return;
        }
        
        System.out.println("\n所有图形信息：");
        for (int i = 0; i < shapes.size(); i++) {
            System.out.print((i + 1) + ". ");
            shapes.get(i).displayInfo();  // 多态调用：运行时确定具体实现
        }
    }

    public double calculateTotalArea() {
        double totalArea = 0;
        for (Shape shape : shapes) {
            totalArea += shape.calculateArea();  // 多态调用
        }
        return totalArea;
    }

    public double calculateTotalPerimeter() {
        double totalPerimeter = 0;
        for (Shape shape : shapes) {
            totalPerimeter += shape.calculatePerimeter();  // 多态调用
        }
        return totalPerimeter;
    }

    public List<Shape> getShapesByName(String name) {
        List<Shape> result = new ArrayList<>();
        for (Shape shape : shapes) {
            if (shape.getShapeName().equals(name)) {
                result.add(shape);
            }
        }
        return result;
    }

    public int getShapeCount() {
        return shapes.size();
    }

    public void clearAllShapes() {
        shapes.clear();
        System.out.println("已清空所有图形");
    }
}