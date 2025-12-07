import java.util.ArrayList;
import java.util.HashSet;

public class RemoveDuplicates {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(2);
        list.add(1);
        list.add(4);
        System.out.println("原始列表: " + list);
        ArrayList<Integer> uniqueList = new ArrayList<>(new HashSet<>(list));
        System.out.println("去重后: " + uniqueList);
    }
}