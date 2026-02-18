
import java.util.Arrays;

public class HouseRobber {
    public int rob(int[] nums){
        int n = nums.length;
        if(n == 0) return 0;
        if(n == 1) return nums[0];

        // Space-optimized DP approach
        // prev2 stores the max money robbed up to house i-2
        // prev1 stores the max money robbed up to house i-1
        int prev2 = nums[0];
        int prev1 = Math.max(nums[0], nums[1]);

        for(int i = 2; i < n; i++){
            // Current max is either not robbing the current house (prev1)
            // or robbing the current house + the max from two houses ago (nums[i] + prev2)
            int current = Math.max(prev1, nums[i] + prev2);
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }
    /**
     * Calculates the maximum amount of money that can be robbed using recursion with memoization.
     *
     * Time Complexity: O(n) - Each house index is solved only once.
     * Space Complexity: O(n) - For the memoization array and recursion stack.
     */
    public int robMemo(int[] nums) {
        int n = nums.length;
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return solve(nums, n - 1, memo);
    }

    private int solve(int[] nums, int index, int[] memo) {
        if (index < 0) return 0;
        if (memo[index] != -1) return memo[index];

        int pick = nums[index] + solve(nums, index - 2, memo);
        int notPick = solve(nums, index - 1, memo);

        return memo[index] = Math.max(pick, notPick);
    }

    public static void main(String[] args) {
        HouseRobber hr = new HouseRobber();
        int[] nums1 = {1,2,3,1};
        System.out.println(hr.rob(nums1)); // Output: 4
        System.out.println(hr.robMemo(nums1)); // Output: 4

        int[] nums2 = {2,7,9,3,1};
        System.out.println(hr.rob(nums2)); // Output: 12
        System.out.println(hr.robMemo(nums2)); // Output: 12

        int[] nums3 = {2,1,1,2};
        System.out.println(hr.rob(nums3)); // Output: 4
        System.out.println(hr.robMemo(nums3)); // Output: 4
    }
}
