import java.util.Arrays;

public class RodCutting {
    // 1. Plain Recursion
    public static int rodCuttingRecursive(int[] prices, int[] length, int rodLength, int n) {
        // Base case: If rod length is 0 or no pieces are left, profit is 0.
        if (rodLength == 0 || n == 0) {
            return 0;
        }
        // If the length of the current piece is less than or equal to the remaining rod length
        if (length[n - 1] <= rodLength) {
            // We have two choices:
            // a) Cut the piece: profit is price of the piece + max profit from the remaining rod.
            //    (We pass 'n' again because we can cut the same piece multiple times - Unbounded).
            int include = prices[n - 1] + rodCuttingRecursive(prices, length, rodLength - length[n - 1], n);
            // b) Don't cut the piece and move to the next smaller piece.
            int exclude = rodCuttingRecursive(prices, length, rodLength, n - 1);
            return Math.max(include, exclude);
        } else {
            // If the piece is longer than the rod, we can't cut it. Move to the next piece.
            return rodCuttingRecursive(prices, length, rodLength, n - 1);
        }
    }
    // 2. Memoization (Top-Down DP)
    public static int rodCuttingMemo(int[] prices, int[] length, int rodLength, int n, int[][] dp) {
        if (rodLength == 0 || n == 0) {
            return 0;
        }
        // If we have already solved this subproblem, return the stored result.
        if (dp[n][rodLength] != -1) {
            return dp[n][rodLength];
        }
        if (length[n - 1] <= rodLength) {
            int include = prices[n - 1] + rodCuttingMemo(prices, length, rodLength - length[n - 1], n, dp);
            int exclude = rodCuttingMemo(prices, length, rodLength, n - 1, dp);
            // Store the result before returning.
            return dp[n][rodLength] = Math.max(include, exclude);
        } else {
            return dp[n][rodLength] = rodCuttingMemo(prices, length, rodLength, n - 1, dp);
        }
    }
    // 3. Tabulation (Bottom-Up DP)
    public static int rodCuttingTabulation(int[] prices, int[] length, int rodLength) {
        int n = length.length;
        int[][] t = new int[n+1][rodLength + 1];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= rodLength; j++){
                if(length[i - 1] <= j){
                    t[i][j] = Math.max(prices[i - 1] + t[i][j - length[i - 1]],t[i - 1][j]);
                } else {
                    t[i][j] = t[i - 1][j];
                }
            }
        }
        return t[n][rodLength];
    }

    public static void main(String[] args){
        int[] length = {1,2,3,4};
        int[] prices = {5,6,8,9};
        int rodLength = 4;
        int n = length.length;

        System.out.println("Recursive Max Profit = " + rodCuttingRecursive(prices, length, rodLength, n));

        int[][] dp = new int[n + 1][rodLength + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        System.out.println("Memoization Max Profit = " + rodCuttingMemo(prices, length, rodLength, n, dp));

        System.out.println("Tabulation Max Profit  = " + rodCuttingTabulation(prices, length, rodLength));
    }
}
