public class Concatenation {
    //brute force approach
    public static int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] result = new int[2 * n];
        for (int i = 0; i < n; i++) {
            result[i] = nums[i];
            result[i + n] = nums[i];
        }
        return result;
    }
    //Better approach using System.arraycopy
    public static int[] getConcatenationArrayCopy(int[] nums) {
        int n = nums.length;
        int[] result = new int[2 * n];
        System.arraycopy(nums, 0, result, 0, n);
        System.arraycopy(nums, 0, result, n, n);
        return result;
    }
    //Approach using Arrays.setAll (Java 8+)
    public static int[] getConcatenationFill(int[] nums) {
        int n = nums.length;
        int[] result = new int[2 * n];
        java.util.Arrays.setAll(result, i -> nums[i % n]);
        return result;
    }
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int[] concatenatedArray = getConcatenation(nums);
        System.out.print("Concatenated Array (Brute Force): ");
        for (int num : concatenatedArray) {
            System.out.print(num + " ");
        }
        System.out.println();

        int[] concatenatedArrayCopy = getConcatenationArrayCopy(nums);
        System.out.print("Concatenated Array (Array Copy): ");
        for (int num : concatenatedArrayCopy) {
            System.out.print(num + " ");
        }
        System.out.println();

        int[] concatenatedArrayFill = getConcatenationFill(nums);
        System.out.print("Concatenated Array (Fill Method): ");
        for (int num : concatenatedArrayFill) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
