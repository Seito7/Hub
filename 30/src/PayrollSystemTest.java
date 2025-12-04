import java.util.Map;

public class PayrollSystemTest {
    public static void main(String[] args) {
        // 创建公司实例
        Company company = new Company();
        
        System.out.println("员工工资管理系统测试：\n");
        
        try {
            // 创建不同类型的员工
            Employee fullTimeEmp = new FullTimeEmployee("FT001", "小明", 8000, 2000);
            Employee partTimeEmp = new PartTimeEmployee("PT001", "小张", 50, 120);
            Employee salesEmp = new SalesEmployee("SE001", "小王", 4000, 100000);
            
            // 添加更多员工进行测试
            Employee fullTimeEmp2 = new FullTimeEmployee("FT002", "小红", 7500, 1500);
            Employee salesEmp2 = new SalesEmployee("SE002", "小晨", 4500, 80000, 0.06); // 6%提成
            
            // 添加员工到公司
            company.addEmployee(fullTimeEmp);
            company.addEmployee(partTimeEmp);
            company.addEmployee(salesEmp);
            company.addEmployee(fullTimeEmp2);
            company.addEmployee(salesEmp2);
            
            // 测试添加重复ID
            Employee duplicateEmp = new FullTimeEmployee("FT001", "重复员工", 5000, 1000);
            company.addEmployee(duplicateEmp);
            System.out.println("\n" + "-".repeat(50));
            
            // 显示所有员工信息
            company.displayAllEmployees();
            
            System.out.println("\n" + "-".repeat(50));
            
            // 按工资排序显示
            company.displayEmployeesBySalary();
            
            System.out.println("\n" + "-".repeat(50));
            
            // 查找工资最高和最低的员工
            Employee highestPaid = company.findHighestPaidEmployee();
            Employee lowestPaid = company.findLowestPaidEmployee();
            
            System.out.println("工资最高的员工：");
            System.out.println(highestPaid != null ? highestPaid.getEmployeeInfo() : "无");
            
            System.out.println("\n工资最低的员工：");
            System.out.println(lowestPaid != null ? lowestPaid.getEmployeeInfo() : "无");
            
            System.out.println("\n" + "-".repeat(50));
            
            // 使用Stream API进行数据统计
            Map<String, Object> stats = company.getEmployeeStatistics();
            System.out.println("公司工资统计：");
            stats.forEach((key, value) -> {
                System.out.printf("%s: %s%n", key, value);
            });
            
            System.out.println("\n" + "-".repeat(50));
            
            // 测试equals和hashCode方法
            System.out.println("equals和hashCode方法测试：");
            Employee testEmp1 = new FullTimeEmployee("FT001", "小明", 8000, 2000);
            Employee testEmp2 = new FullTimeEmployee("FT001", "大明", 9000, 3000);
            
            System.out.println("相同ID员工equals比较: " + testEmp1.equals(testEmp2));
            System.out.println("相同ID员工hashCode比较: " + (testEmp1.hashCode() == testEmp2.hashCode()));
            
            // 测试删除功能
            System.out.println("\n" + "-".repeat(50));
            company.removeEmployee("PT001");
            company.displayAllEmployees();
            
        } catch (Exception e) {
            System.out.println("系统运行出错: " + e.getMessage());
            e.printStackTrace();
        }
    }
}