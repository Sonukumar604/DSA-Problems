import java.util.Arrays;

public class CountNumberSubsetSum {
    //recursive function
    public static int countSubsetRec(int[] arr, int n, int target){
        if(target == 0) return 1;
        if(n == 0) return 0;
        if(arr[n-1] <= target){
            return countSubsetRec(arr, n-1, target-arr[n-1])+countSubsetRec(arr, n-1, target);
        }else{
            return countSubsetRec(arr, n-1, target);
        }
    }
    //memoization
    public static int countSubsetMemo(int[] arr, int n , int target, int[][] dp){
        if(target == 0) return 1;
        if(n == 0) return 0;
        if(dp[n][target] != -1){
            return dp[n][target];
        }
        if(arr[n-1] <= target){
            dp[n][target] = countSubsetMemo(arr, n-1, target-arr[n-1], dp) + countSubsetMemo(arr, n-1, target, dp);
        }else{
            dp[n][target] = countSubsetMemo(arr, n-1, target, dp);
        }
        return dp[n][target];
    }
    //tabulation
    public static int countSubsetsWithSum(int[] arr, int target){
        int n = arr.length;
        int[][] dp = new int[n+1][target+1];
        for(int i = 0; i <= n;i++){
            dp[i][0] = 1;
        }
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= target; j++){
                if(arr[i-1] <= j){
                    dp[i][j] = dp[i-1][j-arr[i-1]] + dp[i-1][j];
                }
                else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][target];
    }

    public static int countSubsetsWithDifference(int[] arr, int diff){
        int totalSum = 0;
        for(int num : arr) totalSum += num;

        int targetSum = totalSum + diff;
        if (targetSum < 0 || targetSum % 2 != 0) return 0;

        int target = targetSum / 2;
        int n = arr.length;

        // You can choose which implementation to use here.
        // 1. Tabulation
        return countSubsetsWithSum(arr, target);

        // 2. Recursive
        // return countSubsetRec(arr, n, target);

        // 3. Memoization
        // int[][] dp = new int[n+1][target+1];
        // for(int[] row : dp) Arrays.fill(row, -1);
        // return countSubsetMemo(arr, n, target, dp);
    }
    public static void main(String[] args){
        int[] arr= {1,1,2,3};
        int diff = 1;
        System.out.println("Count of subsets with given difference: " + countSubsetsWithDifference(arr, diff));
    }
}
