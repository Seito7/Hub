public class Circle implements Shape {
    private double radius;
    private static final double PI = Math.PI;
    public Circle(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("半径必须大于0");
        }
        this.radius = radius;
    }
    @Override
    public double calculateArea() {
        return PI * radius * radius;
    }
    
    @Override
    public double calculatePerimeter() {
        return 2 * PI * radius;
    }
    
    @Override
    public String getShapeName() {
        return "圆形";
    }
    
    @Override
    public void displayInfo() {
        System.out.printf("%s: 半径=%.2f, 面积=%.2f, 周长=%.2f%n",
            getShapeName(), radius, calculateArea(), calculatePerimeter());
    }

    public double getRadius() {
        return radius;
    }
}