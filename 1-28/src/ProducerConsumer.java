import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {
    private static final int CAPACITY = 5;
    private final Queue<Integer> queue = new LinkedList<>();
    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();
        Thread producer = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        producer.start();
        consumer.start();
    }
    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (queue) {
                while (queue.size() == CAPACITY) {
                    System.out.println("队列已满，生产者等待...");
                    queue.wait();
                }
                System.out.println("生产者生产: " + value);
                queue.add(value++);
                queue.notify();
                Thread.sleep(1000);
            }
        }
    }
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (queue) {
                while (queue.isEmpty()) {
                    System.out.println("队列为空，消费者等待...");
                    queue.wait();
                }
                int value = queue.poll();
                System.out.println("消费者消费: " + value);
                queue.notify();
                Thread.sleep(1500);
            }
        }
    }
}