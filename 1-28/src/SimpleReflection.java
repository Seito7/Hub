import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class SimpleReflection {
    public static void main(String[] args) {
        // 获取String类的信息
        Class<?> clazz = String.class;
        
        System.out.println("=== 类信息 ===");
        System.out.println("类名: " + clazz.getName());
        System.out.println("简单类名: " + clazz.getSimpleName());
        System.out.println();
        
        // 获取所有方法
        System.out.println("=== 方法列表 ===");
        Method[] methods = clazz.getDeclaredMethods();
        for (int i = 0; i < Math.min(methods.length, 5); i++) {
            System.out.println(methods[i].getName());
        }
        System.out.println("... (共" + methods.length + "个方法)");
        System.out.println();
        
        // 获取所有属性
        System.out.println("=== 属性列表 ===");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getType().getSimpleName() + " " + field.getName());
        }
    }
}