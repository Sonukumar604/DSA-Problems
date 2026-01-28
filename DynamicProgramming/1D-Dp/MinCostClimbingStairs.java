import java.util.Arrays;

public class MinCostClimbingStairs {
    // Space Optimized Bottom-Up Approach
    public int minCostClimbingStairs(int[] cost){
        int n = cost.length;

        // We can optimize space from O(n) to O(1) since we only need the last two values.
        // 'down_two' stores the min cost to reach step i-2
        // 'down_one' stores the min cost to reach step i-1
        int down_two = 0; // Corresponds to dp[0]
        int down_one = 0; // Corresponds to dp[1]

        // Iterate from step 2 to the top (n)
        for(int i = 2; i <= n; i++){
            // The new min cost is the minimum of coming from one step below or two steps below.
            int current_cost = Math.min(down_one + cost[i - 1], down_two + cost[i - 2]);
            // Update our pointers
            down_two = down_one;
            down_one = current_cost;
        }

        // 'down_one' now holds the minimum cost to reach the top (step n)
        return down_one;
    }

    // Top-Down Approach (Recursion + Memoization)
    public int minCostClimbingStairsMemo(int[] cost) {
        int n = cost.length;
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return solve(n, cost, memo);
    }

    private int solve(int n, int[] cost, int[] memo) {
        // Base cases: We can start at step 0 or step 1 with 0 cost
        if (n == 0 || n == 1) return 0;

        if (memo[n] != -1) return memo[n];

        // Recurrence: min cost to reach step n is min of (reaching n-1 + cost[n-1]) and (reaching n-2 + cost[n-2])
        memo[n] = Math.min(solve(n - 1, cost, memo) + cost[n - 1], 
                           solve(n - 2, cost, memo) + cost[n - 2]);
        return memo[n];
    }

    public static void main(String[] args) {
        MinCostClimbingStairs solution = new MinCostClimbingStairs();
        int[] cost = {10, 15, 20};
        int result = solution.minCostClimbingStairs(cost);
        System.out.println("Minimum cost to climb stairs (Optimized): " + result);
        System.out.println("Minimum cost to climb stairs (Memoization): " + solution.minCostClimbingStairsMemo(cost));
    }
}
