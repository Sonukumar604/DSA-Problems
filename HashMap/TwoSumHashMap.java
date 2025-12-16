import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSumHashMap {

    /**
     * Finds two numbers that sum to a target using the optimal HashMap approach.
     * This method provides the best time complexity for the general case.
     *
     * Time Complexity: O(n) - We iterate through the array only once.
     * Space Complexity: O(n) - In the worst case, we store all n elements in the map.
     *
     * @param nums   The input array of integers.
     * @param target The target sum.
     * @return An array containing the indices of the two numbers.
     * @throws IllegalArgumentException if no solution is found.
     */
    public static int[] findTwoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException("Input array must contain at least two numbers.");
        }

        // The map will store: <number, index>
        Map<Integer, Integer> seenNumbers = new HashMap<>();

        // Iterate through the array once.
        for (int i = 0; i < nums.length; i++) {
            int currentNum = nums[i];
            int complement = target - currentNum;

            // Check if the complement needed to reach the target exists in our map.
            // This lookup is O(1) on average.
            if (seenNumbers.containsKey(complement)) {
                // If it exists, we found our solution.
                int complementIndex = seenNumbers.get(complement);
                return new int[]{complementIndex, i};
            }

            // If the complement is not found, add the current number and its index to the map
            // for future lookups.
            seenNumbers.put(currentNum, i);
        }

        // If the loop completes, no solution was found.
        throw new IllegalArgumentException("No two sum solution found for the given target.");
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = findTwoSum(nums, target);
        System.out.println("Optimal HashMap Approach:");
        System.out.println("Input: nums = " + Arrays.toString(nums) + ", target = " + target);
        System.out.println("Output: " + Arrays.toString(result)); // Expected: [0, 1]

        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        int[] result2 = findTwoSum(nums2, target2);
        System.out.println("\nInput: nums = " + Arrays.toString(nums2) + ", target = " + target2);
        System.out.println("Output: " + Arrays.toString(result2)); // Expected: [1, 2]
    }
}