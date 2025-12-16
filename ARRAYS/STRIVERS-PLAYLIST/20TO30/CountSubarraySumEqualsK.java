import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CountSubarraySumEqualsK {

    // Brute-Force Approach
    // Time Complexity: O(N^2), Space Complexity: O(1)
    public static int countSubarraySumKBrute(int[] nums, int k) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            long currentSum = 0;
            for (int j = i; j < n; j++) {
                currentSum += nums[j];
                if (currentSum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    // Optimal Approach using HashMap (Prefix Sum)
    // Time Complexity: O(N), Space Complexity: O(N)
    public static int countSubarraySumKOptimal(int[] nums, int k) {
        int n = nums.length;
        // map: <prefix_sum, frequency>
        Map<Long, Integer> prefixSumMap = new HashMap<>();
        long sum = 0;
        int count = 0;

        // Initialize with a prefix sum of 0 having occurred once.
        // This handles cases where a subarray starting from index 0 sums to k.
        prefixSumMap.put(0L, 1);

        for (int i = 0; i < n; i++) {
            sum += nums[i];
            long remainder = sum - k;

            // If (sum - k) exists in the map, it means there are subarrays ending at i
            // which sum to k. The number of such subarrays is the frequency of (sum - k).
            count += prefixSumMap.getOrDefault(remainder, 0);

            // Add the current prefix sum to the map, updating its frequency.
            prefixSumMap.put(sum, prefixSumMap.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, -3, 1, 1, 1, 4, 2, -3};
        int k = 3;
        System.out.println("Array: " + Arrays.toString(nums) + ", K: " + k);
        System.out.println("Brute-Force: Number of subarrays is " + countSubarraySumKBrute(nums, k));
        System.out.println("Optimal (Hashing): Number of subarrays is " + countSubarraySumKOptimal(nums, k));
    }
}
