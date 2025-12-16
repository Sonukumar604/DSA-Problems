import java.util.Arrays;

public class MCMMemoization {
    public static int[][] dp;
    public static int mcm(int[] arr, int i, int j){
        if(i >= j){
            return 0;
        }
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        int min = Integer.MAX_VALUE;
        for(int k = i; k < j; k++){
            int left = mcm(arr, i, k);
            int right = mcm(arr, k + 1, j);
            int cost = arr[i- 1]* arr[k] * arr[j];
            int temp = left + right + cost;
            min = Math.min(min, temp);
        }
        dp[i][j] = min;
        return min;
    }
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 30};
        int n = arr.length;
        dp = new int[n][n];
        for(int row : dp){
            Arrays.fill(row, -1);
        }
        int ans = mcm(arr, 1, n-1);
        System.out.println("Minimum number of multiplications: " + ans);
    }
}
