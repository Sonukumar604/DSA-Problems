import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindSubarrayWithGivenSum {

    /**
     * Finds the start and end indices of the first contiguous subarray that sums to a given value 'k'.
     * This method handles both positive and negative numbers and is optimized for time complexity.
     *
     * The core idea is to use a HashMap to store the first occurrence of each prefix sum.
     * For a running sum `currentSum` at index `i`, if `currentSum - k` exists in the map at index `j`,
     * then the subarray from `j + 1` to `i` has the desired sum.
     *
     * Time Complexity: O(n) - We iterate through the array once.
     * Space Complexity: O(n) - In the worst case, the map stores a prefix sum for each element.
     *
     * @param nums The input array of integers.
     * @param k    The target sum.
     * @return An array containing the start and end indices `[start, end]` of the subarray.
     *         Returns `null` if no such subarray is found.
     */
    public static int[] findSubarray(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        // Map to store the first index where a running sum was seen.
        // Key: running sum, Value: index
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        int currentSum = 0;

        // Base case: A sum of 0 is found at index -1 (before the array starts).
        // This handles cases where the subarray starts from index 0.
        prefixSumMap.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            int complement = currentSum - k;

            // Check if a previous running sum exists that is equal to `currentSum - k`.
            if (prefixSumMap.containsKey(complement)) {
                int startIndex = prefixSumMap.get(complement) + 1;
                return new int[]{startIndex, i};
            }

            // If the current sum is not in the map, add it.
            prefixSumMap.putIfAbsent(currentSum, i);
        }

        // If the loop completes, no such subarray was found.
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {10, 2, -2, -20, 10};
        int k = -10;
        int[] result = findSubarray(nums, k);

        System.out.println("Input Array: " + Arrays.toString(nums) + ", Target Sum: " + k);
        if (result != null) {
            System.out.println("Subarray with the given sum found at indices: " + Arrays.toString(result)); // Expected: [0, 3]
        } else {
            System.out.println("No subarray with the given sum found.");
        }
    }
}