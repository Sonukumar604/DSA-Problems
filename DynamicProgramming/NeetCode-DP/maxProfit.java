import java.util.Arrays;

public class maxProfit {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // dp[i][0] -> holding stock
        // dp[i][1] -> not holding stock (can buy)
        int[][] dp = new int[n][2];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return solve(prices, 0, 1, dp);
    }

    private int solve(int[] prices, int index, int buy, int[][] dp) {
        if (index >= prices.length) {
            return 0;
        }
        if (dp[index][buy] != -1) {
            return dp[index][buy];
        }

        int profit = 0;
        if (buy == 1) { // Can buy
            int buyStock = -prices[index] + solve(prices, index + 1, 0, dp);
            int skip = solve(prices, index + 1, 1, dp);
            profit = Math.max(buyStock, skip);
        } else { // Holding stock
            int sellStock = prices[index] + solve(prices, index + 2, 1, dp);
            int skip = solve(prices, index + 1, 0, dp);
            profit = Math.max(sellStock, skip);
        }
        return dp[index][buy] = profit;
    }

    public static void main(String[] args) {
        maxProfit solution = new maxProfit();
        int[] prices = {1, 3, 4, 0, 4};
        System.out.println(solution.maxProfit(prices)); // Output: 6
    }
}
