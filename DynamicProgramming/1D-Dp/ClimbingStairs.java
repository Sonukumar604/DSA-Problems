public class ClimbingStairs {
    /**
     * Calculates the number of distinct ways to climb to the top of n stairs,
     * taking either 1 or 2 steps at a time. This is a classic dynamic programming
     * problem that follows the Fibonacci sequence.
     *
     * Time Complexity: O(n) - We iterate through the numbers from 2 to n once.
     * Space Complexity: O(n) - We use an array of size n+1 to store results.
     *
     * @param n The total number of stairs.
     * @return The number of distinct ways to climb.
     */
    public int climbStairs(int n){
        // Base cases: If there are 0 or 1 stairs, there is only one way to climb.
        if(n <= 1) return 1;

        // dp[i] will be storing the number of ways to reach the i-th stair.
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
    public static void main(String[] args){
        ClimbingStairs cs = new ClimbingStairs();
        int n = 5;
        System.out.println("Number of ways to climb " + n + " stairs: " + cs.climbStairs(n));
    }
}
