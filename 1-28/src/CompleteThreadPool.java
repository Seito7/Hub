import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.List;

public class CompleteThreadPool {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            final int num = i;
            Future<Integer> future = executor.submit(() -> {
                System.out.println("计算 " + num + " 的平方，线程: " + 
                    Thread.currentThread().getName());
                return num * num;
            });
            futures.add(future);
        }
        System.out.println("\n=== 计算结果 ===");
        for (int i = 0; i < futures.size(); i++) {
            Integer result = futures.get(i).get();
            System.out.println("任务" + (i+1) + "结果: " + result);
        }
        executor.shutdown();
        System.out.println("所有任务完成！");
    }
}