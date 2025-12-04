public class SalesEmployee extends Employee {
    private double salesAmount;
    private double commissionRate;
    
    public SalesEmployee(String id, String name, double baseSalary, double salesAmount) {
        this(id, name, baseSalary, salesAmount, 0.05);
    }
    
    public SalesEmployee(String id, String name, double baseSalary, double salesAmount, double commissionRate) {
        super(id, name, baseSalary);
        
        if (salesAmount < 0) {
            throw new IllegalArgumentException("销售额不能为负数");
        }
        if (commissionRate < 0) {
            throw new IllegalArgumentException("提成比例不能为负数");
        }
        
        this.salesAmount = salesAmount;
        this.commissionRate = commissionRate;
    }
    
    @Override
    public double calculateSalary() {
        return baseSalary + (salesAmount * commissionRate);
    }
    
    @Override
    public String getEmployeeInfo() {
        return String.format("销售员工： ID: %s, 姓名: %s, 基础工资: %.2f, 销售额: %.2f, 提成比例: %.1f%%, 实发工资: %.2f",
                           id, name, baseSalary, salesAmount, commissionRate * 100, calculateSalary());
    }

    public double getSalesAmount() { return salesAmount; }
    public double getCommissionRate() { return commissionRate; }
    
    public void setSalesAmount(double salesAmount) {
        if (salesAmount >= 0) {
            this.salesAmount = salesAmount;
        }
    }
    
    public void setCommissionRate(double commissionRate) {
        if (commissionRate >= 0) {
            this.commissionRate = commissionRate;
        }
    }
}