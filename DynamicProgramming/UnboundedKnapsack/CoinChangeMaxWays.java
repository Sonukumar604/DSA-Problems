import java.util.Arrays;

public class CoinChangeMaxWays {
    //Recursive
    public static int countWaysRec(int[] coins, int n, int sum){
        if(sum == 0) return 1;
        if(n == 0 ) return 0;
        if(coins[n-1] <= sum){
            // include the coin (stay on n) + exclude the coin (move to n-1)
            return countWaysRec(coins, n, sum-coins[n-1]) + countWaysRec(coins, n-1, sum);
        }else{
            return countWaysRec(coins, n-1, sum);
        }
    }
    //Memoization
    public static int countWaysMemo(int[] coins, int n, int sum, int[][] dp){
        if(sum == 0) return 1;
        if(n == 0 || sum < 0) return 0;
        if(dp[n][sum] != -1) return dp[n][sum];
        if(coins[n-1] <= sum){
            // include the coin (stay on n) + exclude the coin (move to n-1)
            dp[n][sum] = countWaysMemo(coins, n, sum-coins[n-1], dp) + countWaysMemo(coins, n-1, sum, dp);
        }else{
            dp[n][sum] = countWaysMemo(coins, n-1, sum, dp);
        }
        return dp[n][sum];
    }
    //Tabulation
    public static int countWaysTabu(int[] coins, int sum){
        int n = coins.length;
        int[][] t = new int[n+1][sum+1];
        for(int i = 0; i <= n; i++){
            t[i][0] = 1;
        }
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= sum; j++){
                if(coins[i - 1] <= j){
                    // exclude (t[i-1][j]) + include (t[i][j - coins[i-1]])
                    t[i][j] = t[i-1][j] + t[i][j - coins[i - 1]];
                }else{
                    t[i][j] = t[i - 1][j];
                }
            }
        }
        return t[n][sum];
    }
    public static void main(String[] args) {
        int[] coins = {1,2,3};
        int sum = 5;
        int n = coins.length;

        System.out.println("(Tabulation)Number of ways to make sum = " + countWaysTabu(coins, sum));

        int[][] dp = new int[n + 1][sum + 1];
        for(int[] row : dp) Arrays.fill(row, -1);
        System.out.println("(Memoization)Number of ways to make sum = " + countWaysMemo(coins, n, sum, dp));

        System.out.println("(Recursion)Number of ways to make sum = " + countWaysRec(coins, n, sum));
    }
}
