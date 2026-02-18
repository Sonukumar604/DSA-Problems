import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class minimumPathSum {
    // This problem is LeetCode 120. Triangle
    
    public int minimumTotal(List<List<Integer>> triangle) {
        // Defaulting to the most optimized approach
        return minimumTotalSpaceOptimized(triangle);
    }

    // ---------------------------------------------------------
    // 1. Recursive Approach
    // Time Complexity: O(2^N)
    // Space Complexity: O(N) (Recursion stack)
    // ---------------------------------------------------------
    public int minimumTotalRecursive(List<List<Integer>> triangle) {
        return solveRecursive(triangle, 0, 0);
    }

    private int solveRecursive(List<List<Integer>> triangle, int row, int col) {
        if (row == triangle.size()) {
            return 0;
        }
        int down = solveRecursive(triangle, row + 1, col);
        int downRight = solveRecursive(triangle, row + 1, col + 1);
        return triangle.get(row).get(col) + Math.min(down, downRight);
    }

    // ---------------------------------------------------------
    // 2. Recursion + Memoization (Top-Down DP)
    // Time Complexity: O(N^2)
    // Space Complexity: O(N^2) (Memo table) + O(N) (Stack)
    // ---------------------------------------------------------
    public int minimumTotalMemoization(List<List<Integer>> triangle) {
        int n = triangle.size();
        Integer[][] memo = new Integer[n][n];
        return solveMemo(triangle, 0, 0, memo);
    }

    private int solveMemo(List<List<Integer>> triangle, int row, int col, Integer[][] memo) {
        if (row == triangle.size()) {
            return 0;
        }
        if (memo[row][col] != null) {
            return memo[row][col];
        }
        int down = solveMemo(triangle, row + 1, col, memo);
        int downRight = solveMemo(triangle, row + 1, col + 1, memo);
        return memo[row][col] = triangle.get(row).get(col) + Math.min(down, downRight);
    }

    // ---------------------------------------------------------
    // 3. Bottom-Up DP (Tabulation)
    // Time Complexity: O(N^2)
    // Space Complexity: O(N^2)
    // ---------------------------------------------------------
    public int minimumTotalTabulation(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];

        // Base case: Fill the last row
        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = triangle.get(n - 1).get(j);
        }

        // Fill upwards from second to last row
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i + 1][j], dp[i + 1][j + 1]);
            }
        }
        return dp[0][0];
    }

    // ---------------------------------------------------------
    // 4. Space Optimized Bottom-Up DP
    // Time Complexity: O(N^2)
    // Space Complexity: O(N)
    // ---------------------------------------------------------
    public int minimumTotalSpaceOptimized(List<List<Integer>> triangle) {
        int n = triangle.size();
        // Initialize dp array with the last row of the triangle
        int[] dp = new int[n];
        for (int j = 0; j < n; j++) {
            dp[j] = triangle.get(n - 1).get(j);
        }

        // Iterate upwards
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                // dp[j] becomes the min path sum for the current element
                // using the values from the row below (which are currently in dp)
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        minimumPathSum solver = new minimumPathSum();

        // Example: [[2], [3,4], [6,5,7], [4,1,8,3]]
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));

        System.out.println("Recursive: " + solver.minimumTotalRecursive(triangle));
        System.out.println("Memoization: " + solver.minimumTotalMemoization(triangle));
        System.out.println("Tabulation: " + solver.minimumTotalTabulation(triangle));
        System.out.println("Space Optimized: " + solver.minimumTotalSpaceOptimized(triangle));
    }
}
