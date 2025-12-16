import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class SlidingWindowMax {

    /**
     * Finds the maximum value in each sliding window of size k.
     * This is an optimal solution with O(n) time complexity and O(k) space complexity.
     *
     * @param nums The input array of numbers.
     * @param k    The size of the sliding window.
     * @return A list containing the maximum of each window.
     */
    public static List<Integer> maxSlidingWindow(int[] nums, int k) {
        // Handle edge cases
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();
        // The Deque will store indices of the array elements.
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < nums.length; i++) {
            // 1. Clean the front: Remove indices that are out of the current window's bounds.
            // The window is from [i - k + 1, i]. An index is out of bounds if it's <= i - k.
            if (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            // 2. Clean the back: Maintain the decreasing order property.
            // Remove all indices from the back that point to values smaller than the current value.
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // 3. Add the current index to the back.
            deque.offerLast(i);

            // 4. Record the result: Once the window is full, the front of the deque has the max.
            if (i >= k - 1) {
                result.add(nums[deque.peekFirst()]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        List<Integer> result = maxSlidingWindow(nums, k);
        // Expected output: [3, 3, 5, 5, 6, 7]
        System.out.println("Maximums of sliding windows of size " + k + ": " + result);
    }
}
