import java.util.Arrays;
/**
 * This class provides different methods to count the number of subsets
 * in an array that sum up to a given target value.
 */
public class CountSubsetSum {
    //Recursive
    public static int countSubsetRec(int[] arr, int n , int sum){
        if(sum == 0) return 1;
        if(n == 0) return 0;
        if(arr[n-1] <= sum){
            return countSubsetRec(arr,n-1, sum - arr[n-1]) + countSubsetRec(arr, n-1, sum);
        }else{
            return countSubsetRec(arr, n-1, sum);
        }
    }
    //Memoization
    public static int countSubsetMemo(int[] arr, int n, int sum, int[][]dp){
        if(sum == 0) return 1;
        if(n == 0) return 0;
        if(dp[n][sum] != -1) return dp[n][sum];
        if(arr[n-1] <= sum){
            dp[n][sum] = countSubsetMemo(arr, n-1, sum-arr[n-1], dp) + countSubsetMemo(arr, n-1, sum, dp);
        }else{
            dp[n][sum] = countSubsetMemo(arr, n-1, sum, dp);
        }
        return dp[n][sum];
    }
    //Tabulation
    public static int countSubsetSumTab(int[] arr, int sum){
        int n = arr.length;
        int[][] t = new int[n+1][sum+1];
        for(int i = 0; i <= n; i++){
            t[i][0] = 1;
        }
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= sum; j++){
                if(arr[i-1] <= j){
                    t[i][j] = t[i-1][j-arr[i-1]] + t[i-1][j];
                }
                else{
                    t[i][j] = t[i-1][j];
                }
            }
        }
        return t[n][sum];
    }
    public static void main(String[] args) {
        int[] arr = {2,3,5,6,8,10};
        int sum = 10;
        int n = arr.length;
        //Recursive 
        int recAns = countSubsetRec(arr, n, sum);
        System.out.println("Recursive count: " + recAns);
        //Memoization
        int[][] dp = new int[n+1][sum+1];
        for(int[] row : dp) java.util.Arrays.fill(row, -1);
        int memoAns = countSubsetMemo(arr, n, sum, dp);
        System.out.println("Memoization count: " + memoAns);
        //Tabulation
        System.out.println("   Tabulation count: " + countSubsetSumTab(arr, sum));
    }
}
