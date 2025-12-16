import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestSubarraySumK {

    // Brute-Force Approach
    // Time Complexity: O(N^2), Space Complexity: O(1)
    public static int longestSubarraySumKBrute(int[] nums, int k) {
        int n = nums.length;
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            int currentSum = 0;
            for (int j = i; j < n; j++) {
                currentSum += nums[j];
                if (currentSum == k) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        return maxLength;
    }

    // Better Approach (Optimal for arrays with positives, negatives, and zeros)
    // Time Complexity: O(N), Space Complexity: O(N)
    public static int longestSubarraySumKBetter(int[] nums, int k) {
        int n = nums.length;
        Map<Long, Integer> prefixSumMap = new HashMap<>();
        long sum = 0;
        int maxLength = 0;

        for (int i = 0; i < n; i++) {
            sum += nums[i];

            if (sum == k) {
                maxLength = Math.max(maxLength, i + 1);
            }

            long remainder = sum - k;
            if (prefixSumMap.containsKey(remainder)) {
                int len = i - prefixSumMap.get(remainder);
                maxLength = Math.max(maxLength, len);
            }

            // Store the prefix sum only if it's not already there,
            // to ensure we get the longest possible subarray.
            if (!prefixSumMap.containsKey(sum)) {
                prefixSumMap.put(sum, i);
            }
        }
        return maxLength;
    }

    // Optimal Approach (Only for arrays with positives and zeros)
    // Time Complexity: O(N), Space Complexity: O(1)
    public static int longestSubarraySumKOptimal(int[] nums, int k) {
        int n = nums.length;
        int left = 0, right = 0;
        long sum = 0;
        int maxLength = 0;

        while (right < n) {
            sum += nums[right];

            while (left <= right && sum > k) {
                sum -= nums[left];
                left++;
            }

            if (sum == k) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
            right++;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1, 1, 1, 1, 4, 2, 3};
        int k = 3;
        System.out.println("Array: " + Arrays.toString(nums) + ", K: " + k);
        System.out.println("Brute-Force: Longest subarray length is " + longestSubarraySumKBrute(nums, k));
        System.out.println("Better (Hashing): Longest subarray length is " + longestSubarraySumKBetter(nums, k));
        System.out.println("Optimal (Two-Pointers): Longest subarray length is " + longestSubarraySumKOptimal(nums, k));
    }
}
