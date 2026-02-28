import java.util.Arrays;

public class HouseRobber2 {
    /**
     * Solves the House Robber II problem using an iterative, space-optimized approach.
     * Since houses are arranged in a circle, we solve two linear House Robber problems:
     * 1. Rob houses 0 to n-2 (exclude last)
     * 2. Rob houses 1 to n-1 (exclude first)
     *
     * Time Complexity: O(n) - We iterate through the array twice.
     * Space Complexity: O(1) - We use constant extra space.
     */
    public int rob(int[] nums){
        int n = nums.length;
        if(n == 0) return 0;
        if(n == 1) return nums[0];

        // Rob houses from index 0 to n-2
        int max1 = robLinear(nums, 0, n - 2);
        // Rob houses from index 1 to n-1
        int max2 = robLinear(nums, 1, n - 1);

        return Math.max(max1, max2);
    }
    private int robLinear(int[] nums, int start, int end){
        int prev2 = 0; // Max money robbed up to house i-2
        int prev1 = 0; // Max money robbed up to house i-1
        for(int i = start; i <= end; i++){
            int current = Math.max(prev1, nums[i] + prev2);
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }
    /**
     * Solves the House Robber II problem using recursion with memoization (Top-Down).
     *
     * Time Complexity: O(n) - Each house index is solved only once for each of the two scenarios.
     * Space Complexity: O(n) - For the memoization array and recursion stack.
     */
    public int robMemo(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;
        if(n == 1) return nums[0];
        int max1 = robLinearMemo(nums, 0, n - 2);
        int max2 = robLinearMemo(nums, 1, n - 1);
        return Math.max(max1, max2);
    }
    private int robLinearMemo(int[] nums, int start, int end){
        int length = end - start + 1;
        int[] memo = new int[length];
        Arrays.fill(memo, -1);
        return solve(nums, start, end, memo);
    }
    private int solve(int[] nums, int start, int end, int[] memo) {
        if (end < start) return 0;
        if (memo[end - start] != -1) return memo[end - start];
        int pick = nums[end] + solve(nums, start, end - 2, memo);
        int notPick = solve(nums, start, end - 1, memo);
        return memo[end - start] = Math.max(pick, notPick);
    }
    public static void main(String[] args) {
        HouseRobber2 hr2 = new HouseRobber2();
        int[] nums1 = {2,3,2};
        System.out.println(hr2.rob(nums1)); // Output: 3
        int[] nums2 = {1,2,3,1};
        System.out.println(hr2.rob(nums2)); // Output: 4
        int[] nums3 = {1,2,3};
        System.out.println(hr2.rob(nums3)); // Output: 3
        System.out.println(hr2.robMemo(nums1)); // Output: 3
    }
}
