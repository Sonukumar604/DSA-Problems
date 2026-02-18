import java.util.Arrays;

public class PerfectSquares {
    // Approach 1: Brute Force Recursion
    // Time Complexity: Exponential
    public int numSquaresRecursion(int n) {
        if (n == 0) return 0;
        
        int min = n;
        for (int i = 1; i * i <= n; i++) {
            min = Math.min(min, 1 + numSquaresRecursion(n - i * i));
        }
        return min;
    }

    // Approach 2: Recursion with Memoization
    public int numSquaresMemo(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return solveMemo(n, memo);
    }

    private int solveMemo(int n, int[] memo) {
        if (n == 0) return 0;
        if (memo[n] != -1) return memo[n];

        int min = n;
        for (int i = 1; i * i <= n; i++) {
            min = Math.min(min, 1 + solveMemo(n - i * i, memo));
        }
        return memo[n] = min;
    }

    // Approach 3: Iterative DP (Tabulation)
    public int numSquares(int n){
        int[] dp = new int[n+1];
        for(int i = 0; i <= n; i++){
            dp[i] = i;
            for(int j = 1; j*j <= i; j++){
                dp[i] = Math.min(dp[i], dp[i - j*j] + 1);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        PerfectSquares ps = new PerfectSquares();
        int n = 12; // Expected output: 3 (4 + 4 + 4)
        System.out.println("Recursion: " + ps.numSquaresRecursion(n));
        System.out.println("Memoization: " + ps.numSquaresMemo(n));
        System.out.println("Tabulation: " + ps.numSquares(n));
    }
}
