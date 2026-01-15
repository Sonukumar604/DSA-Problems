import java.util.Arrays;

public class MinimumSubsetSum{

    // --- 1. Tabulation (Bottom-Up) ---

    /**
     * Finds the minimum subset sum difference using tabulation.
     * Time Complexity: O(n * range)
     * Space Complexity: O(n * range)
     */
    public static int minDifferenceTabulation(int[] arr){
        int n = arr.length;
        int range = 0;
        for(int num : arr){
            range += num;
        }

        // Get the last row of the subset sum DP table.
        boolean[] possibleSums = subsetSumTabulation(arr, n, range);

        int minDifference = Integer.MAX_VALUE;
        // Find the minimum difference by checking possible sums up to range/2.
        for(int j = 0; j <= range/2; j++){
            if(possibleSums[j]){
                minDifference = Math.min(minDifference, range-2*j);
            }
        }
        return minDifference;
    }

    /**
     * Helper for tabulation that returns an array indicating all possible subset sums.
     */
    private static boolean[] subsetSumTabulation(int[] arr, int n, int range) {
        boolean[][] dp = new boolean[n + 1][range + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= range; j++) {
                if (arr[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n];
    }

    // --- 2. Memoization (Top-Down) ---

    /**
     * Finds the minimum subset sum difference using memoization.
     * Time Complexity: O(n * range)
     * Space Complexity: O(n * range) for DP table + O(n) for recursion stack.
     */
    public static int minDifferenceMemoization(int[] arr) {
        int n = arr.length;
        int range = 0;
        for (int num : arr) {
            range += num;
        }

        // DP table: -1 (not computed), 0 (false), 1 (true)
        int[][] dp = new int[n + 1][range + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        // Populate the DP table by checking for a sum of 'range'.
        // This ensures all reachable subproblems are solved.
        subsetSumMemoHelper(arr, n, range, dp);

        int minDifference = Integer.MAX_VALUE;
        for (int j = 0; j <= range / 2; j++) {
            // Check if sum 'j' is possible using the last 'n' elements.
            if (dp[n][j] == 1) {
                minDifference = Math.min(minDifference, range - 2 * j);
            }
        }
        return minDifference;
    }

    /**
     * Recursive helper for memoization.
     */
    private static boolean subsetSumMemoHelper(int[] arr, int n, int sum, int[][] dp) {
        if (sum == 0) return true;
        if (n == 0 || sum < 0) return false;
        if (dp[n][sum] != -1) return dp[n][sum] == 1;

        boolean result;
        if (arr[n - 1] <= sum) {
            result = subsetSumMemoHelper(arr, n - 1, sum - arr[n - 1], dp) || subsetSumMemoHelper(arr, n - 1, sum, dp);
        } else {
            result = subsetSumMemoHelper(arr, n - 1, sum, dp);
        }
        dp[n][sum] = result ? 1 : 0;
        return result;
    }
    public static void main(String[] args) {
        int[] arr= {1,2,7};
        System.out.println("Original Array: " + Arrays.toString(arr));

        System.out.println("Minimum subset Difference (Tabulation): " + minDifferenceTabulation(arr));

        System.out.println("Minimum subset Difference (Memoization): " + minDifferenceMemoization(arr));

        // Note: A pure recursive solution would be extremely inefficient as it would
        // have to re-calculate the entire subset sum problem for each potential sum,
        // leading to a very high time complexity. Memoization is the recursive equivalent of DP.
    }
}