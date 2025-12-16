import java.util.Arrays;

public class EqualSumPartition{
    //Recursive
    public static boolean subsetSumRec(int[] arr, int n , int sum){
        if(sum == 0) return true;
        if(n == 0) return false;
        if(arr[n-1] <= sum){
            return subsetSumRec(arr, n-1, sum) || subsetSumRec(arr, n-1, sum-arr[n-1]);
        }else{
            return subsetSumRec(arr, n-1, sum);
        }
    }
    //Memoization
    public static boolean subsetSumMemo(int[] arr, int n, int sum, int[][] dp){
        if(sum == 0) return true;
        if(n == 0) return false;
        if(dp[n][sum] != -1) return dp[n][sum] == 1;
        boolean ans;
        if(arr[n-1] <= sum){
            ans = subsetSumMemo(arr, n-1, sum - arr[n-1], dp) || subsetSumMemo(arr, n-1, sum, dp);
        }else{
            ans = subsetSumMemo(arr, n-1, sum, dp);
        }
        dp[n][sum] = ans ? 1 : 0;
        return ans;
    }
    //Tabulation
    public static boolean subsetSumTab(int[] arr, int target){
        int n = arr.length;
        boolean[][] dp = new boolean[n+1][target+1];
        for(int i = 0; i <= n; i++){
            dp[i][0] = true;
        }
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= target; j++){
                if(arr[i-1] <= j){
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-arr[i-1]];
                }
                else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][target];
    }
    public static boolean CanPartitionRecursive(int[] arr){
        int sum = Arrays.stream(arr).sum();
        if(sum % 2 != 0)return false;
        return subsetSumRec(arr,arr.length,  sum/2);
    }
    public static boolean CanPartitionMemoization(int[] arr){
        int sum = Arrays.stream(arr).sum();
        if(sum % 2 != 0) return false;
        int[][] dp = new int[arr.length + 1][sum/2 + 1];
        for(int[] row : dp) Arrays.fill(row,-1);
        return subsetSumMemo(arr, arr.length, sum/2, dp);
    }
    public static boolean CanPartitionTabulation(int[] arr){
        int sum = Arrays.stream(arr).sum();
        if(sum % 2 != 0) return false;
        return subsetSumTab(arr, sum/2);
    }
    public static void main(String[] args) {
        int[] arr = {1,5,11,15};
        if(CanPartitionTabulation(arr)){
            System.out.println("can be partitioned into equal sum subset");
        }else{
            System.out.println("can not be partitioned into equal sum subsets");
        }
    }
}