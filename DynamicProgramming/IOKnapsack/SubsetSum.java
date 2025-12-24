import java.util.Arrays;

/**
 * This class provides different methods to solve the Subset Sum Problem:
 * Given a set of non-negative integers and a value sum, determine if there is
 * a subset of the given set with a sum equal to the given sum.
 */
public class SubsetSum{

    // 1. Plain Recursion (Top-Down)
    /**
     * Solves the subset sum problem using a plain recursive approach.
     * Time Complexity: O(2^n) - Exponential, as it explores every possible subset.
     * Space Complexity: O(n) - For the recursion call stack.
     */
    public static boolean isSubsetSumRecursive(int[] arr, int sum, int n) {
        // Base Case 1: If sum is 0, an empty subset {} is found.
        if (sum == 0) return true;
        // Base Case 2: If no items are left (n=0) but sum is not 0, no solution exists.
        if (n == 0) return false;

        // If the last element is greater than the sum, it cannot be included.
        if (arr[n - 1] > sum) {
            return isSubsetSumRecursive(arr, sum, n - 1);
        } else {
            // Two choices for the last element:
            // a) Exclude the element and check the rest of the array.
            boolean exclude = isSubsetSumRecursive(arr, sum, n - 1);
            // b) Include the element and check for the remaining sum in the rest of the array.
            boolean include = isSubsetSumRecursive(arr, sum - arr[n - 1], n - 1);
            // Return true if either choice leads to a solution.
            return exclude || include;
        }
    }

    // 2. Memoization (Top-Down DP)
    /**
     * Solves the subset sum problem using memoization to avoid recomputing subproblems.
     * Time Complexity: O(n * sum)
     * Space Complexity: O(n * sum) for the DP table + O(n) for recursion stack.
     */
    public static boolean isSubsetSumMemo(int[] arr, int sum, int n, int[][] dp) {
        if (sum == 0) return true;
        if (n == 0) return false;

        // If this state has been computed, return the stored result.
        // 1 for true, 0 for false.
        if (dp[n][sum] != -1) return dp[n][sum] == 1;

        boolean result;
        if (arr[n - 1] > sum) {
            result = isSubsetSumMemo(arr, sum, n - 1, dp);
        } else {
            result = isSubsetSumMemo(arr, sum, n - 1, dp) || isSubsetSumMemo(arr, sum - arr[n - 1], n - 1);
        }
        // Store the result before returning.
        dp[n][sum] = result ? 1 : 0;
        return result;
    }

    // 3. Tabulation (Bottom-Up DP)
    /**
     * Solves the subset sum problem using tabulation.
     * Time Complexity: O(n * sum)
     * Space Complexity: O(n * sum) for the DP table.
     */
    public static boolean isSubsetSumTabulation(int[] arr, int sum){
        int n = arr.length;
        boolean[][] dp = new boolean[n+1][sum+1];

        // Initialization: A sum of 0 is always possible (with an empty set).
        for(int i = 0; i <= n; i++){
            dp[i][0] = true;
        }
        // With 0 items, no sum > 0 is possible. This is false by default in a boolean array.
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= sum; j++){
                if(arr[i-1] <= j){
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-arr[i-1]];
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][sum];
    }

    public static void main(String[] args) {
        int[] arr = {2,3,7};
        int sum = 11;
        int n = arr.length;

        // 1. Recursive
        boolean existsRecursive = isSubsetSumRecursive(arr, sum, n);
        System.out.println("Recursive: Subset sum exists? " + existsRecursive);

        // 2. Memoization
        int[][] dp = new int[n + 1][sum + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        boolean existsMemo = isSubsetSumMemo(arr, sum, n, dp);
        System.out.println("Memoization: Subset sum exists? " + existsMemo);

        // 3. Tabulation
        boolean existsTabulation = isSubsetSumTabulation(arr, sum);
        System.out.println("Tabulation: Subset sum exists? " + existsTabulation);
    }
}