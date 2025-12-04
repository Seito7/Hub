public class Rectangle implements Shape {
    private double width;
    private double height;
    
    public Rectangle(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("长和宽必须大于0");
        }
        this.width = width;
        this.height = height;
    }
    
    @Override
    public double calculateArea() {
        return width * height;
    }
    
    @Override
    public double calculatePerimeter() {
        return 2 * (width + height);
    }
    
    @Override
    public String getShapeName() {
        return "矩形";
    }
    
    @Override
    public void displayInfo() {
        System.out.printf("%s: 长=%.2f, 宽=%.2f, 面积=%.2f, 周长=%.2f%n",
            getShapeName(), width, height, calculateArea(), calculatePerimeter());
    }

    public double getWidth() {
        return width;
    }
    
    public double getHeight() {
        return height;
    }
}