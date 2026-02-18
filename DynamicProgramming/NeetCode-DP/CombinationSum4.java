import java.util.Arrays;

public class CombinationSum4 {
    /**
     * Approach 1: Recursion
     * Time Complexity: O(N^T) - Exponential, where N is the length of nums and T is the target.
     * Space Complexity: O(T) - For the recursion stack.
     */
    public int combinationSum4(int[] nums, int target) {
        if(target == 0) return 1;
        if(target < 0) return 0;

        int count = 0;
        for(int num : nums){
            count += combinationSum4(nums, target - num);
        }
        return count;
    }
    /**
     * Approach 2: Recursion with Memoization (Top-Down)
     * Time Complexity: O(T * N) - We solve for each target value once, iterating through nums.
     * Space Complexity: O(T) - For the memoization array and recursion stack.
     */
    public int combinationSum4Memo(int[] nums, int target) {
        int[] memo = new int[target + 1];
        Arrays.fill(memo, -1);
        return solve(nums, target, memo);
    }
    private int solve(int[] nums, int target, int[] memo){
        if(target == 0) return 1;
        if(target < 0) return 0;

        if(memo[target] != -1) return memo[target];

        int count = 0;
        for(int num : nums){
            count += solve(nums, target - num, memo);
        }
        return memo[target] = count;
    }
    /**
     * Approach 3: Tabulation (Bottom-Up)
     * Time Complexity: O(T * N) - We iterate from 1 to target, and for each, iterate through nums.
     * Space Complexity: O(T) - For the dp array.
     */
    public int combinationSum4Tab(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1; // Base case: There's one way to make the target 0 (using no numbers)

        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        CombinationSum4 cs = new CombinationSum4();
        int[] nums = {1, 2, 3};
        int target = 4;
        System.out.println("Recursion: " + cs.combinationSum4(nums, target));
        System.out.println("Memoization: " + cs.combinationSum4Memo(nums, target));
        System.out.println("Tabulation: " + cs.combinationSum4Tab(nums, target));
    }
}
