import java.util.HashMap;
import java.util.Map;

public class LongestSubarrayWithk {

    /**
     * Finds the length of the longest subarray with a sum equal to k.
     * This method handles positive, negative, and zero elements in the array.
     * <p>
     * The approach uses a HashMap to store the cumulative sum up to an index and the index itself.
     * We iterate through the array, calculating the cumulative sum `currentSum`.
     * For each `currentSum`, we check if `currentSum - k` exists in our map.
     * If it does, it means the sum of elements between the index stored for `currentSum - k`
     * and the current index `i` is exactly `k`. We can then calculate the length of this subarray.
     * We keep track of the maximum length found.
     * <p>
     * We initialize the map with a sum of 0 at index -1 to correctly handle subarrays that start from index 0.
     *
     * @param nums The input array of integers.
     * @param k    The target sum.
     * @return The length of the longest subarray with sum k, or 0 if no such subarray exists.
     */
    public static int getLongestSubarrayWithSumK(int[] nums, int k) {
        // HashMap to store the first occurrence of each prefix sum.
        // Key: prefix sum, Value: index
        // Using Long for the sum to prevent potential integer overflow.
        Map<Long, Integer> prefixSumMap = new HashMap<>();
        
        long currentSum = 0;
        int maxLength = 0;
        
        // Initialize map with a sum of 0 at index -1.
        // This handles cases where the subarray with sum k starts from index 0.
        prefixSumMap.put(0L, -1);
        
        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            
            // Check if (currentSum - k) exists in the map.
            // If it does, it means there is a subarray ending at the current index
            // which sums to k.
            if (prefixSumMap.containsKey(currentSum - k)) {
                int length = i - prefixSumMap.get(currentSum - k);
                maxLength = Math.max(maxLength, length);
            }
            
            // If the currentSum is not already in the map, add it.
            // We only add it if it's not present to keep the earliest (leftmost) index,
            // which helps in finding the longest subarray.
            if (!prefixSumMap.containsKey(currentSum)) {
                prefixSumMap.put(currentSum, i);
            }
        }
        
        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = {10, 5, 2, 7, 1, 9};
        int k = 15;
        System.out.println("Array: " + java.util.Arrays.toString(nums));
        System.out.println("Target sum k: " + k);
        int length = getLongestSubarrayWithSumK(nums, k);
        // The subarray is {5, 2, 7, 1} which sums to 15. Length is 4.
        System.out.println("Length of the longest subarray with sum " + k + " is: " + length); 
        System.out.println("------------------------------------");

        int[] nums2 = {1, -1, 5, -2, 3};
        int k2 = 3;
        System.out.println("Array: " + java.util.Arrays.toString(nums2));
        System.out.println("Target sum k: " + k2);
        int length2 = getLongestSubarrayWithSumK(nums2, k2);
        // The subarray is {1, -1, 5, -2} which sums to 3. Length is 4.
        System.out.println("Length of the longest subarray with sum " + k2 + " is: " + length2);
        System.out.println("------------------------------------");

        int[] nums3 = {-1, 1, 1};
        int k3 = 1;
        System.out.println("Array: " + java.util.Arrays.toString(nums3));
        System.out.println("Target sum k: " + k3);
        int length3 = getLongestSubarrayWithSumK(nums3, k3);
        // The subarray is {-1, 1, 1} which sums to 1. Length is 3.
        System.out.println("Length of the longest subarray with sum " + k3 + " is: " + length3);
    }
}
