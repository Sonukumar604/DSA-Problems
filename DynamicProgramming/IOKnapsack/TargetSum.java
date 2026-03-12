import java.util.Arrays;

/**
 * This class provides solutions to the "Target Sum" problem (LeetCode 494)
 * using three different dynamic programming approaches:
 * 1. Recursion
 * 2. Recursion with Memoization (Top-Down DP)
 * 3. Tabulation (Bottom-Up DP)
 *
 * ### Problem Transformation
 * The problem asks to find the number of ways to assign `+` or `-` to each number
 * in an array such that their sum equals a given `target`.
 *
 * This can be transformed into a subset sum problem. Let's say we partition the
 * array into two subsets: `S1` (numbers with `+`) and `S2` (numbers with `-`).
 *
 * We want: `sum(S1) - sum(S2) = target`
 * We know: `sum(S1) + sum(S2) = totalSum` (where `totalSum` is the sum of all numbers in the array)
 *
 * Adding these two equations:
 * `2 * sum(S1) = target + totalSum`
 * `sum(S1) = (target + totalSum) / 2`
 *
 * So, the problem is reduced to finding the number of subsets `S1` that sum up to
 * `(target + totalSum) / 2`.
 *
 * Note: A valid solution exists only if `(target + totalSum)` is non-negative and even.
 */
public class TargetSum {

    // --- Main Method to call specific approaches ---

    public int findTargetSumWays(int[] nums, int target) {
        // Defaulting to the most optimized (Tabulation) approach.
        return findTargetSumWaysTabulation(nums, target);
    }

    // --- 1. Recursion ---

    /**
     * Solves the Target Sum problem using plain recursion.
     * Time Complexity: O(2^N), where N is the number of elements. Exponential.
     * Space Complexity: O(N) for the recursion stack.
     */
    public int findTargetSumWaysRecursive(int[] nums, int target) {
        int totalSum = Arrays.stream(nums).sum();
        int newTarget = target + totalSum;
        if (newTarget < 0 || newTarget % 2 != 0) {
            return 0;
        }
        newTarget /= 2;
        return countSubsetsWithSumRecursive(nums, nums.length, newTarget);
    }

    private int countSubsetsWithSumRecursive(int[] arr, int n, int target) {
        if (target == 0) return 1;
        if (n == 0) return 0;

        if (arr[n - 1] <= target) {
            // Include the current element OR exclude it.
            return countSubsetsWithSumRecursive(arr, n - 1, target - arr[n - 1]) +
                   countSubsetsWithSumRecursive(arr, n - 1, target);
        } else {
            // Must exclude the current element.
            return countSubsetsWithSumRecursive(arr, n - 1, target);
        }
    }

    // --- 2. Memoization (Top-Down DP) ---

    /**
     * Solves the Target Sum problem using recursion with memoization.
     * Time Complexity: O(N * T), where N is the number of elements and T is the target sum.
     * Space Complexity: O(N * T) for the memoization table + O(N) for the recursion stack.
     */
    public int findTargetSumWaysMemoization(int[] nums, int target) {
        int totalSum = Arrays.stream(nums).sum();
        int newTarget = target + totalSum;
        if (newTarget < 0 || newTarget % 2 != 0) {
            return 0;
        }
        newTarget /= 2;
        int[][] dp = new int[nums.length + 1][newTarget + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return countSubsetsWithSumMemo(nums, nums.length, newTarget, dp);
    }

    private int countSubsetsWithSumMemo(int[] arr, int n, int target, int[][] dp) {
        if (target == 0) return 1;
        if (n == 0) return 0;
        if (dp[n][target] != -1) {
            return dp[n][target];
        }

        if (arr[n - 1] <= target) {
            dp[n][target] = countSubsetsWithSumMemo(arr, n - 1, target - arr[n - 1], dp) +
                            countSubsetsWithSumMemo(arr, n - 1, target, dp);
        } else {
            dp[n][target] = countSubsetsWithSumMemo(arr, n - 1, target, dp);
        }
        return dp[n][target];
    }

    // --- 3. Tabulation (Bottom-Up DP) ---

    /**
     * Solves the Target Sum problem using tabulation.
     * Time Complexity: O(N * T), where N is the number of elements and T is the target sum.
     * Space Complexity: O(N * T) for the DP table. Can be optimized to O(T).
     */
    public int findTargetSumWaysTabulation(int[] nums, int target) {
        int totalSum = Arrays.stream(nums).sum();
        int newTarget = target + totalSum;
        if (newTarget < 0 || newTarget % 2 != 0) {
            return 0; // No valid partition exists.
        }
        newTarget /= 2;

        // Now we need to count the number of subsets that sum up to newTarget.
        return countSubsetsWithSumTabulation(nums, newTarget);
    }

    private int countSubsetsWithSumTabulation(int[] arr, int target) {
        int n = arr.length;
        int[][] dp = new int[n + 1][target + 1];

        // There's one way to make sum 0 (the empty subset).
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                if (arr[i - 1] <= j) {
                    // Two choices:
                    // 1. Include arr[i-1]: Number of ways is dp[i-1][j - arr[i-1]]
                    // 2. Exclude arr[i-1]: Number of ways is dp[i-1][j]
                    dp[i][j] = dp[i - 1][j - arr[i - 1]] + dp[i - 1][j];
                } else {
                    // Must exclude arr[i-1] as it's larger than the current sum.
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][target];
    }

    /**
     * Main method for dry running the TargetSum solution.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        TargetSum solution = new TargetSum();

        // Test Case 1
        int[] nums1 = {1, 1, 1, 1, 1};
        int target1 = 3;
        System.out.println("--- Test Case 1: nums=" + Arrays.toString(nums1) + ", target=" + target1 + " ---");
        // Note: Recursive is too slow for larger inputs, but works here.
        // System.out.println("Recursive Ways: " + solution.findTargetSumWaysRecursive(nums1, target1));
        System.out.println("Memoization Ways: " + solution.findTargetSumWaysMemoization(nums1, target1)); // Expected: 5
        System.out.println("Tabulation Ways:  " + solution.findTargetSumWaysTabulation(nums1, target1)); // Expected: 5
        System.out.println();

        // Test Case 2
        int[] nums2 = {1};
        int target2 = 1;
        System.out.println("--- Test Case 2: nums=" + Arrays.toString(nums2) + ", target=" + target2 + " ---");
        System.out.println("Recursive Ways:   " + solution.findTargetSumWaysRecursive(nums2, target2)); // Expected: 1
        System.out.println("Memoization Ways: " + solution.findTargetSumWaysMemoization(nums2, target2)); // Expected: 1
        System.out.println("Tabulation Ways:  " + solution.findTargetSumWaysTabulation(nums2, target2)); // Expected: 1
        System.out.println();

        // Test Case 3 (with zeros)
        int[] nums3 = {0, 0, 0, 0, 0, 0, 0, 0, 1};
        int target3 = 1;
        System.out.println("--- Test Case 3: nums=" + Arrays.toString(nums3) + ", target=" + target3 + " ---");
        // System.out.println("Recursive Ways: " + solution.findTargetSumWaysRecursive(nums3, target3));
        System.out.println("Memoization Ways: " + solution.findTargetSumWaysMemoization(nums3, target3)); // Expected: 256
        System.out.println("Tabulation Ways:  " + solution.findTargetSumWaysTabulation(nums3, target3)); // Expected: 256
        System.out.println();
    }
}
