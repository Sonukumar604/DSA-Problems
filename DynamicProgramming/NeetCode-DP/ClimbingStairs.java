import java.util.Arrays;

public class ClimbingStairs {
    /**
     * Calculates the number of distinct ways to climb to the top of n stairs,
     * taking either 1 or 2 steps at a time. This is a classic dynamic programming
     * problem that follows the Fibonacci sequence. (Iterative, Space-Optimized)
     *
     * Time Complexity: O(n) - We iterate through the numbers from 2 to n once.
     * Space Complexity: O(1) - We only use a few variables to store state.
     *
     * @param n The total number of stairs.
     * @return The number of distinct ways to climb.
     */
    public int climbStairs(int n){
        if (n < 0) return 0;
        // Base cases: If there are 0 or 1 stairs, there is only one way to climb.
        if(n <= 1) return 1;

        // Space Optimization: We only need the last two values
        int prev2 = 1; // Represents dp[i-2] (initially dp[0])
        int prev1 = 1; // Represents dp[i-1] (initially dp[1])

        for(int i = 2; i <= n; i++){
            int current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }

    /**
     * Calculates the number of ways to climb stairs using recursion with memoization.
     * (Top-Down DP)
     *
     * Time Complexity: O(n) - Each subproblem is solved only once.
     * Space Complexity: O(n) - For the memoization array and recursion stack.
     */
    public int climbStairsMemo(int n) {
        if (n < 0) return 0;
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return solve(n, memo);
    }

    private int solve(int n, int[] memo) {
        if (n <= 1) return 1;

        if (memo[n] != -1) {
            return memo[n];
        }

        memo[n] = solve(n - 1, memo) + solve(n - 2, memo);
        return memo[n];
    }

    public static void main(String[] args){
        ClimbingStairs cs = new ClimbingStairs();
        int n = 5;
        System.out.println("Number of ways to climb " + n + " stairs (Iterative): " + cs.climbStairs(n));
        System.out.println("Number of ways to climb " + n + " stairs (Memoization): " + cs.climbStairsMemo(n));
    }
}
