import java.util.Arrays;

public class PalindromePartitioningMemoization {
    static int[][] dp;
    static boolean isPalindrome(String s, int i, int j){
        while(i < j){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    public static int minPartitions(String s, int i, int j){
        if(i >= j || isPalindrome(s, i, j)){
            return 0;
        }
        if(dp[i][j] != -1) return dp[i][j];
        int min = Integer.MAX_VALUE;
        for(int k = i; k < j; k++){
            int left , right;
            if(dp[i][k] != -1) {
                left = dp[i][k];
            } else {
                left = minPartitions(s, i, k);
            }
            if(dp[k+1][j] != -1) {
                right = dp[k+1][j];
            } else {
                right = minPartitions(s, k+1, j);
            }
            int temp = 1 + left + right;
            min = Math.min(min, temp);
        }
        dp[i][j] = min;
        return min;
    }
    public static void main(String[] args){
        String s = "ababbbabbababa";
        int n = s.length();
        dp = new int[n+1][n+1];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }
        int result = minPartitions(s, 0, n-1);
        System.out.println("Minimum cuts needed: " + result);
    }
}
