import java.util.Arrays;

public class UniquePaths {
    // Recursive (Top-Down) with Memoization Approach
    public int uniquePathsRecursive(int m, int n) {
        int[][] memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return solve(m - 1, n - 1, memo);
    }

    private int solve(int row, int col, int[][] memo) {
        // Base case: If we are at the first row or first column, there's only one way.
        if (row == 0 || col == 0) {
            return 1;
        }
        // If we have already computed the value, return it.
        if (memo[row][col] != -1) {
            return memo[row][col];
        }

        // Recursive step: Sum of paths from the cell above and the cell to the left.
        return memo[row][col] = solve(row - 1, col, memo) + solve(row, col - 1, memo);
    }

    // Iterative DP (Space Optimized)
    public int uniquePaths(int m, int n){
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i = 0; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }
    public static void main(String[] args) {
        UniquePaths solution = new UniquePaths();
        int m = 3, n = 7;
        System.out.println("Unique paths (Iterative DP): " + solution.uniquePaths(m, n)); // Output: 28
        System.out.println("Unique paths (Recursive with Memoization): " + solution.uniquePathsRecursive(m, n)); // Output: 28
    }
}
