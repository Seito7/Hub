public class Triangle implements Shape {
    private double sideA;
    private double sideB;
    private double sideC;
    
    public Triangle(double sideA, double sideB, double sideC) {
        if (sideA <= 0 || sideB <= 0 || sideC <= 0) {
            throw new IllegalArgumentException("边长必须大于0");
        }
        if (!isValidTriangle(sideA, sideB, sideC)) {
            throw new IllegalArgumentException("三边无法构成三角形");
        }
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }
    
    private boolean isValidTriangle(double a, double b, double c) {
        return (a + b > c) && (a + c > b) && (b + c > a);
    }
    
    @Override
    public double calculateArea() {
        double s = calculatePerimeter() / 2;
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
    }
    
    @Override
    public double calculatePerimeter() {
        return sideA + sideB + sideC;
    }
    
    @Override
    public String getShapeName() {
        return "三角形";
    }
    
    @Override
    public void displayInfo() {
        System.out.printf("%s: 边a=%.2f, 边b=%.2f, 边c=%.2f, 面积=%.2f, 周长=%.2f%n",
            getShapeName(), sideA, sideB, sideC, calculateArea(), calculatePerimeter());
    }

    public double getSideA() {
        return sideA;
    }
    
    public double getSideB() {
        return sideB;
    }
    
    public double getSideC() {
        return sideC;
    }
}