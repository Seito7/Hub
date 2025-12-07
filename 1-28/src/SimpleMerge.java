import java.util.Arrays;

public class SimpleMerge {
    public static void main(String[] args) {
        int[] a = {1,2, 3, 5};
        int[] b = {2, 4, 6, 8};
        
        int[] merged = merge(a, b);
        System.out.println("合并结果: " + Arrays.toString(merged));
    }
    
    public static int[] merge(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        
        while (i < a.length && j < b.length) {
            result[k++] = (a[i] <= b[j]) ? a[i++] : b[j++];
        }
        
        while (i < a.length) result[k++] = a[i++];
        while (j < b.length) result[k++] = b[j++];
        
        return result;
    }
}