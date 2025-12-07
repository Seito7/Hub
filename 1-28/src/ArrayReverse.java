public class ArrayReverse {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.print("原数组: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.print("\n逆序输出: ");
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.print(arr[i] + " ");
        }
    }
}