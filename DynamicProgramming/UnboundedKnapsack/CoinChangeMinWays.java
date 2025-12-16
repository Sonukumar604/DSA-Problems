import java.util.Arrays;

/**
 * This class solves the "Minimum Coin Change" problem using dynamic programming.
 * It's a classic example of the Unbounded Knapsack pattern.
 */
public class CoinChangeMinWays {
    // A large value to represent an impossible state (infinity).
    // We subtract 1 to prevent integer overflow if we later perform an operation like `INF + 1`.
    private static final int INF = Integer.MAX_VALUE - 1;

    // 1. Plain Recursion
    /**
     * Public wrapper for the recursive solution.
     * It calls the helper and interprets the result.
     */
    public static int minCoinsRecursive(int[] coins, int sum) {
        int n = coins.length;
        int result = minCoinsRecursiveHelper(coins, n, sum);
        // This is the line you asked about, now inside the public wrapper.
        return (result >= INF) ? -1 : result;
    }
    // Private helper that contains the core recursive logic.
    private static int minCoinsRecursiveHelper(int[] coins, int n, int sum) {
        if (sum == 0) return 0; // Base case 1: 0 coins needed for sum 0.
        if (n == 0) return INF; // Base case 2: Impossible to make sum > 0 with 0 coins.
        if (coins[n - 1] <= sum) {
            // Choice 1: Include the coin.
            int include = minCoinsRecursiveHelper(coins, n, sum - coins[n - 1]);
            // Choice 2: Exclude the coin.
            int exclude = minCoinsRecursiveHelper(coins, n - 1, sum);

            // If including the coin is possible (not INF), add 1 to its result.
            if (include != INF) {
                include += 1;
            }
            return Math.min(include, exclude);
        } else {
            // Coin is too large, must exclude.
            return minCoinsRecursiveHelper(coins, n - 1, sum);
        }
    }

    // 2. Memoization (Top-Down DP)
    /**
     * Public wrapper for the memoized solution.
     * It sets up the DP table, calls the helper, and interprets the result.
     */
    public static int minCoinsMemo(int[] coins, int sum) {
        int n = coins.length;
        int[][] dp = new int[n + 1][sum + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        int result = minCoinsMemoHelper(coins, n, sum, dp);
        // This is the line you asked about, now inside the public wrapper.
        return (result >= INF) ? -1 : result;
    }

    // Private helper that contains the core memoized logic.
    private static int minCoinsMemoHelper(int[] coins, int n, int sum, int[][] dp) {
        if (sum == 0) return 0;
        if (n == 0) return INF;
        if (dp[n][sum] != -1) return dp[n][sum];

        if (coins[n - 1] <= sum) {
            int include = minCoinsMemoHelper(coins, n, sum - coins[n - 1], dp);
            int exclude = minCoinsMemoHelper(coins, n - 1, sum, dp);

            if (include != INF) {
                include += 1;
            }
            return dp[n][sum] = Math.min(include, exclude);
        } else {
            return dp[n][sum] = minCoinsMemoHelper(coins, n - 1, sum, dp);
        }
    }

    /**
     * Calculates the minimum number of coins required to make a given sum.
     *
     * @param coins An array of available coin denominations.
     * @param sum   The target sum to achieve.
     * @return The minimum number of coins, or -1 if the sum cannot be made.
     */
    public static int minCoinsTabulation(int[] coins, int sum){
        int n = coins.length;

        // dp[i][j] will store the minimum number of coins required for sum 'j' using the first 'i' coins.
        int[][] dp = new int[n+1][sum+1];

        // --- Initialization ---
        // Base Case 1: To make a sum of 0, 0 coins are always required, regardless of the coins available.
        for(int i = 0; i <= n; i++){
            dp[i][0] = 0;
        }
        // Base Case 2: To make any sum > 0 with 0 coins is impossible. We represent this with infinity.
        for(int j = 1; j <= sum; j++){
            dp[0][j] = INF;
        }

        // --- Build the DP table ---
        // 'i' represents considering the first 'i' coins.
        // 'j' represents the target sum we are trying to achieve.
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= sum; j++){
                // If the current coin's value is less than or equal to the target sum 'j'
                if(coins[i - 1] <= j){
                    // We have two choices:
                    // 1. Include the coin: The count is 1 (for the current coin) + the minimum coins needed for the
                    //    remaining sum (j - coins[i-1]). We use dp[i] because we can use the same coin multiple times (unbounded).
                    // 2. Exclude the coin: The result is the same as for the previous i-1 coins (dp[i-1][j]).
                    // We take the minimum of these two choices.
                    dp[i][j] = Math.min(1 + dp[i][j - coins[i - 1]], dp[i - 1][j]);
                }else{
                    // If the current coin's value is greater than the target sum 'j', we have no choice but to exclude it.
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        // The final answer is in the bottom-right cell. If it's still INF, the sum is not possible.
        return (dp[n][sum] >= INF) ? -1 : dp[n][sum];
    }
    public static void main(String[] args) {
        int[] coins = {1,2,3};
        int sum = 5;
        // For sum=5, the minimum coins are {2, 3}, which is 2 coins.

        // Tabulation
        System.out.println("(Tabulation) Minimum coins required = " + minCoinsTabulation(coins, sum));

        // Memoization
        System.out.println("(Memoization) Minimum coins required = " + minCoinsMemo(coins, sum));

        // Recursion
        System.out.println("(Recursion) Minimum coins required = " + minCoinsRecursive(coins, sum));
    }
}
