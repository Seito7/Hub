public class SimpleGenericMax {
    public static <T extends Comparable<T>> T findMax(T[] arr) {
        if (arr == null || arr.length == 0) return null;
        
        T max = arr[0];
        for (T element : arr) {
            if (element.compareTo(max) > 0) {
                max = element;
            }
        }
        return max;
    }
    public static void main(String[] args) {
        Integer[] nums = {5, 2, 8, 1, 9};
        String[] words = {"hello", "world", "java"};
        
        System.out.println("最大数字: " + findMax(nums));
        System.out.println("最大字符串: " + findMax(words));
    }
}