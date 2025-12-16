import java.util.Stack;

public class SumSubarrayRange2 {

    /**
     * Calculates the sum of ranges (max - min) for all subarrays using an optimal O(n) approach.
     * It leverages the principle: Sum(range) = Sum(max) - Sum(min).
     *
     * @param nums The input array.
     * @return The total sum of all subarray ranges.
     */
    public static long sumSubarrayRanges(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long sumOfMaxs = sumSubarrayMaxs(nums);
        long sumOfMins = sumSubarrayMins(nums);
        return sumOfMaxs - sumOfMins;
    }

    /**
     * Helper method to calculate the sum of maximums of all subarrays using a monotonic stack.
     * Time Complexity: O(n), Space Complexity: O(n)
     */
    private static long sumSubarrayMaxs(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Find Previous Greater Element for each element
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                stack.pop();
            }
            left[i] = i - (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }

        stack.clear();

        // Find Next Greater or Equal Element for each element
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            right[i] = (stack.isEmpty() ? n : stack.peek()) - i;
            stack.push(i);
        }

        long totalSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum += (long) arr[i] * left[i] * right[i];
        }
        return totalSum;
    }

    /**
     * Helper method to calculate the sum of minimums of all subarrays using a monotonic stack.
     * Time Complexity: O(n), Space Complexity: O(n)
     */
    private static long sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Find Previous Less Element for each element
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            left[i] = i - (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }

        stack.clear();

        // Find Next Less or Equal Element for each element
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            right[i] = (stack.isEmpty() ? n : stack.peek()) - i;
            stack.push(i);
        }

        long totalSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum += (long) arr[i] * left[i] * right[i];
        }
        return totalSum;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        // Expected: (1-1) + (2-2) + (3-3) + (2-1) + (3-2) + (3-1) = 0+0+0+1+1+2 = 4
        System.out.println("The sum of subarray ranges is: " + sumSubarrayRanges(nums));

        int[] nums2 = {4, -2, -3, 4, 1};
        System.out.println("The sum of subarray ranges is: " + sumSubarrayRanges(nums2)); // Expected: 59
    }
}
