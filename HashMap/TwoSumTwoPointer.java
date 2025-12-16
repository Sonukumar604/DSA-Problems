import java.util.Arrays;

public class TwoSumTwoPointer {

    /**
     * Finds two numbers that sum to a target using the Two Pointer approach.
     * This requires sorting the array first while preserving original indices.
     *
     * Time Complexity: O(n log n) - dominated by the sorting step.
     * Space Complexity: O(n) - to store the value-index pairs.
     *
     * @param nums   The input array of integers.
     * @param target The target sum.
     * @return An array containing the original indices of the two numbers.
     * @throws IllegalArgumentException if no solution is found.
     */
    public static int[] findTwoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException("Input array must contain at least two numbers.");
        }

        int n = nums.length;
        int[][] valueIndexPairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            valueIndexPairs[i][0] = nums[i]; // The value
            valueIndexPairs[i][1] = i;       // The original index
        }

        // Step 1: Sort the array of pairs based on the value.
        Arrays.sort(valueIndexPairs, (a, b) -> Integer.compare(a[0], b[0]));

        // Step 2: Use two pointers to find the pair in the sorted array.
        int left = 0;
        int right = n - 1;

        while (left < right) {
            int currentSum = valueIndexPairs[left][0] + valueIndexPairs[right][0];

            if (currentSum == target) {
                // Found the pair! Return their original indices.
                return new int[]{valueIndexPairs[left][1], valueIndexPairs[right][1]};
            } else if (currentSum < target) {
                left++; // Sum is too small, need a larger number.
            } else { // currentSum > target
                right--; // Sum is too large, need a smaller number.
            }
        }

        // If the loop completes, no solution was found.
        throw new IllegalArgumentException("No two sum solution found for the given target.");
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 4};
        int target = 6;
        int[] result = findTwoSum(nums, target);
        System.out.println("Two-Pointer Approach:");
        System.out.println("Input: nums = " + Arrays.toString(nums) + ", target = " + target);
        // The original indices of 2 and 4 are 1 and 2.
        System.out.println("Output: " + Arrays.toString(result));

        int[] nums2 = {2, 7, 11, 15};
        int target2 = 9;
        int[] result2 = findTwoSum(nums2, target2);
        System.out.println("\nInput: nums = " + Arrays.toString(nums2) + ", target = " + target2);
        System.out.println("Output: " + Arrays.toString(result2));
    }
}