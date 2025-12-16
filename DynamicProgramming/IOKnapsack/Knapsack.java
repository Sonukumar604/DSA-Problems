import java.util.Arrays;
public class Knapsack{
    //Recursive kanpsack
    public static int knapsackRecursive(int wt[], int val[], int w, int n){
        if(n == 0 || w == 0){
            return 0;
        }
        if(wt[n-1] > w){
            return knapsackRecursive(wt, val, w, n-1);
        }
        else{
            int include = val[n-1] + knapsackRecursive(wt, val, w-wt[n-1], n-1);
            int exclude = knapsackRecursive(wt, val, w, n-1);
            return Math.max(include, exclude);
        }
    }
    //Memoization
    static int[][] dp;
    public static int knapsackMemo(int wt[], int val[], int w, int n){
        if(n == 0 || w == 0){
            return 0;
        }
        if(dp[n][w] != -1){
            return dp[n][w];
        }
        if(wt[n-1] > w){
            dp[n][w] = knapsackMemo(wt, val, w, n-1);
        }
        else{
            int include = val[n-1] + knapsackMemo(wt, val, w-wt[n-1], n-1);
            int exclude = knapsackMemo(wt, val, w, n-1);
            dp[n][w] = Math.max(include, exclude); 
        }
        return dp[n][w];
    }

    // Bottom-up Dynamic Programming (Tabulation)
    public static int knapsackDP(int wt[], int val[], int w, int n){
        int[][] t = new int[n + 1][w + 1];

        // Build table t[][] in bottom-up manner
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= w; j++) {
                if (i == 0 || j == 0) {
                    t[i][j] = 0; // Base case
                } else if (wt[i - 1] <= j) {
                    // If the current item's weight is less than or equal to the current capacity 'j'
                    // We have two choices: include the item or exclude it.
                    int include = val[i - 1] + t[i - 1][j - wt[i - 1]];
                    int exclude = t[i - 1][j];
                    t[i][j] = Math.max(include, exclude);
                } else {
                    // If the current item's weight is more than the capacity 'j', we can't include it.
                    t[i][j] = t[i - 1][j];
                }
            }
        }
        return t[n][w];
    }
    public static void main(String[] args){
        int[] val = {60, 100, 120};
        int[] wt = {10, 20, 30};
        int w = 50;
        int n = val.length;
        int resRecursive = knapsackRecursive(wt, val, w, n);
        System.out.println("Maximum value in kanpsack: " + resRecursive);
 
        System.out.println("_________________________________________________");

        dp = new int[n+1][w+1];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }
        int resMemo = knapsackMemo(wt, val, w, n);
        System.out.println("Maximum value in knapsack: " + resMemo);

        System.out.println("_________________________________________________");

        int resDP = knapsackDP(wt, val, w, n);
        System.out.println("Maximum value in knapsack (DP): " + resDP);
    }
}