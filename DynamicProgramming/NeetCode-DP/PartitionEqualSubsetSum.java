public class PartitionEqualSubsetSum {
    //Recursion
    public boolean canPartitionRecursion(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        
        // If the total sum is odd, we cannot partition it into two equal subsets
        if (sum % 2 != 0) {
            return false;
        }
        
        return canPartitionHelper(nums, sum / 2, 0);
    }
    private boolean canPartitionHelper(int[] nums, int target, int index) {
        if (target == 0) {
            return true;
        }
        if (index >= nums.length || target < 0) {
            return false;
        }
        
        // Include the current number and move to the next
        if (canPartitionHelper(nums, target - nums[index], index + 1)) {
            return true;
        }
        
        // Exclude the current number and move to the next
        return canPartitionHelper(nums, target, index + 1);
    }
    // Iterative DP (Space Optimized)
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        
        // If the total sum is odd, we cannot partition it into two equal subsets
        if (sum % 2 != 0) {
            return false;
        }
        
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true; // Base case: we can always form a sum of 0
        
        for (int num : nums) {
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }
        
        return dp[target];
    }

    public static void main(String[] args) {
        PartitionEqualSubsetSum solution = new PartitionEqualSubsetSum();
        
        int[] nums1 = {1, 5, 11, 5};
        System.out.println("Can partition {1, 5, 11, 5}? " + solution.canPartition(nums1)); // Expected: true

        int[] nums2 = {1, 2, 3, 5};
        System.out.println("Can partition {1, 2, 3, 5}? " + solution.canPartition(nums2)); // Expected: false
    }
}
