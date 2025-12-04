public class PartTimeEmployee extends Employee {
    private double hourlyRate;
    private int hoursWorked;
    
    public PartTimeEmployee(String id, String name, double hourlyRate, int hoursWorked) {
        super(id, name, 0);
        
        if (hourlyRate < 0) {
            throw new IllegalArgumentException("时薪不能为负数");
        }
        if (hoursWorked < 0) {
            throw new IllegalArgumentException("工作小时数不能为负数");
        }
        
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }
    
    @Override
    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }
    
    @Override
    public String getEmployeeInfo() {
        return String.format("兼职员工： ID: %s, 姓名: %s, 时薪: %.2f, 工作小时: %d, 实发工资: %.2f",
                           id, name, hourlyRate, hoursWorked, calculateSalary());
    }
    public double getHourlyRate() { return hourlyRate; }
    public int getHoursWorked() { return hoursWorked; }
    
    public void setHourlyRate(double hourlyRate) {
        if (hourlyRate >= 0) {
            this.hourlyRate = hourlyRate;
        }
    }
    public void setHoursWorked(int hoursWorked) {
        if (hoursWorked >= 0) {
            this.hoursWorked = hoursWorked;
        }
    }
}