public class FullTimeEmployee extends Employee {
    private double performanceBonus;
    
    public FullTimeEmployee(String id, String name, double baseSalary, double performanceBonus) {
        super(id, name, baseSalary);
        if (performanceBonus < 0) {
            throw new IllegalArgumentException("绩效奖金不能为负数");
        }
        this.performanceBonus = performanceBonus;
    }

    @Override
    public double calculateSalary() {
        return baseSalary + performanceBonus;
    }

    @Override
    public String getEmployeeInfo() {
        return String.format("全职员工： ID: %s, 姓名: %s, 基础工资: %.2f, 绩效奖金: %.2f, 实发工资: %.2f",
                           id, name, baseSalary, performanceBonus, calculateSalary());
    }
    
    public double getPerformanceBonus() {
        return performanceBonus;
    }
    
    public void setPerformanceBonus(double performanceBonus) {
        if (performanceBonus >= 0) {
            this.performanceBonus = performanceBonus;
        }
    }
}