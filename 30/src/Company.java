import java.util.*;
import java.util.stream.Collectors;

public class Company {
    private List<Employee> employees;
    
    public Company() {
        this.employees = new ArrayList<>();
    }
    
    public boolean addEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("员工不能为null");
        }

        if (employees.stream().anyMatch(emp -> emp.getId().equals(employee.getId()))) {
            System.out.println("添加失败：员工ID " + employee.getId() + " 已存在");
            return false;
        }
        
        employees.add(employee);
        System.out.println("成功添加员工：" + employee.getName());
        return true;
    }
    
    //删除员工 - 根据员工ID
    public boolean removeEmployee(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("员工ID不能为空");
        }
        
        boolean removed = employees.removeIf(emp -> emp.getId().equals(id));
        if (removed) {
            System.out.println("成功删除员工ID: " + id);
        } else {
            System.out.println("删除失败：未找到员工ID: " + id);
        }
        return removed;
    }
    
    //计算所有员工总工资
    public double calculateTotalSalary() {
        return employees.stream()
                       .mapToDouble(Employee::calculateSalary) // 多态调用
                       .sum();
    }
    
    //按工资从低到高排序显示所有员工信息
    public void displayEmployeesBySalary() {
        if (employees.isEmpty()) {
            System.out.println("当前没有员工");
            return;
        }
        
        // 使用Collections.sort()进行排序
        List<Employee> sortedEmployees = new ArrayList<>(employees);
        Collections.sort(sortedEmployees); // 使用Comparable接口的自然排序
        
        System.out.println("\n员工工资排名（从低到高）：");
        for (int i = 0; i < sortedEmployees.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, sortedEmployees.get(i).getEmployeeInfo());
        }
    }
    
    //找出工资最高的员工
    public Employee findHighestPaidEmployee() {
        return employees.stream()
                       .max(Comparator.comparingDouble(Employee::calculateSalary))
                       .orElse(null);
    }
    
    //找出工资最低的员工
    public Employee findLowestPaidEmployee() {
        return employees.stream()
                       .min(Comparator.comparingDouble(Employee::calculateSalary))
                       .orElse(null);
    }
    
    //使用Stream API进行数据统计
    public Map<String, Object> getEmployeeStatistics() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("员工总数", employees.size());
        stats.put("总工资支出", calculateTotalSalary());
        stats.put("平均工资", employees.stream().mapToDouble(Employee::calculateSalary).average().orElse(0));
        stats.put("最高工资", employees.stream().mapToDouble(Employee::calculateSalary).max().orElse(0));
        stats.put("最低工资", employees.stream().mapToDouble(Employee::calculateSalary).min().orElse(0));
        Map<Class<? extends Employee>, Long> typeCount = employees.stream()
            .collect(Collectors.groupingBy(Employee::getClass, Collectors.counting()));
        stats.put("员工类型分布", typeCount);
        
        return stats;
    }
    
    // 显示所有员工信息
    public void displayAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("当前没有员工");
            return;
        }
        
        System.out.println("\n所有员工信息：");
        employees.forEach(emp -> System.out.println(emp.getEmployeeInfo()));
    }
    
    //根据ID查找员工
    public Employee findEmployeeById(String id) {
        return employees.stream()
                       .filter(emp -> emp.getId().equals(id))
                       .findFirst()
                       .orElse(null);
    }
    
    // Getter方法
    public List<Employee> getEmployees() {
        return new ArrayList<>(employees); // 返回副本保护内部数据
    }
    public int getEmployeeCount() {
        return employees.size();
    }
}