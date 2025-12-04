import java.util.Objects;

public abstract class Employee implements Comparable<Employee> {
    protected String id;        // 员工ID
    protected String name;      // 员工姓名
    protected double baseSalary; // 基础工资
    
    public Employee(String id, String name, double baseSalary) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("员工ID不能为空");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("员工姓名不能为空");
        }
        if (baseSalary < 0) {
            throw new IllegalArgumentException("基础工资不能为负数");
        }
        
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
    }
    public abstract double calculateSalary();
    public String getEmployeeInfo() {
        return String.format("ID: %s, 姓名: %s, 基础工资: %.2f, 实际工资: %.2f", 
                           id, name, baseSalary, calculateSalary());
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public double getBaseSalary() { return baseSalary; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Employee other) {
        return Double.compare(this.calculateSalary(), other.calculateSalary());
    }
    
    @Override
    public String toString() {
        return getEmployeeInfo();
    }
}