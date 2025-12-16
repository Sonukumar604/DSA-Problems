import java.util.Stack;

public class SubarraySumMaximum {

    /**
     * Calculates the sum of maximums of all possible subarrays using an optimal
     * monotonic stack approach.
     * Time Complexity: O(n)
     * Space Complexity: O(n) for the stacks and helper arrays.
     *
     * @param arr The input array of integers.
     * @return The sum of maximums of all subarrays (as a long to prevent overflow).
     */
    public static long sumSubarrayMaxsOptimal(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int n = arr.length;
        // left[i] = number of elements to the left of i (including i)
        // where arr[i] is the maximum.
        int[] left = new int[n];
        // right[i] = number of elements to the right of i (including i)
        // where arr[i] is the maximum.
        int[] right = new int[n];

        Stack<Integer> stack = new Stack<>();

        // Calculate left boundaries (distance to Previous Greater Element)
        // To handle duplicates correctly, we use `<` for left and `<=` for right.
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                stack.pop();
            }
            left[i] = i - (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }

        stack.clear();

        // Calculate right boundaries (distance to Next Greater or Equal Element)
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            right[i] = (stack.isEmpty() ? n : stack.peek()) - i;
            stack.push(i);
        }

        long totalSum = 0;
        for (int i = 0; i < n; i++) {
            // The contribution of arr[i] is arr[i] * (number of subarrays where it's the max)
            long contribution = (long) arr[i] * left[i] * right[i];
            totalSum += contribution;
        }
        return totalSum;
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 4};
        System.out.println("The sum of subarray maximums is: " + sumSubarrayMaxsOptimal(arr)); // Expected: 30
    }
}
