import java.util.Arrays;

public class LongestIncreasingsubsequences {
    //Recursion
    public int lengthOfLIS(int[] nums) {
        return lengthOfLISHelper(nums, 0, -1);
    }
    public int lengthOfLISHelper(int[] nums, int index, int prevIndex){
        if(index == nums.length) return 0;

        // Case 1: Include the current element if it's greater than the previous element
        int include = 0;
        if(prevIndex == -1 || nums[index] > nums[prevIndex]){
            include = 1 + lengthOfLISHelper(nums, index + 1, index);
        }

        // Case 2: Exclude the current element and move to the next
        int exclude = lengthOfLISHelper(nums, index + 1, prevIndex);

        // Return the maximum of both cases
        return Math.max(include, exclude);
    }
    //recursion + memoization
    public int lengthOfLISMemo(int[] nums) {
        int n = nums.length;
        int[][] memo = new int[n][n + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return lengthOfLISHelperMemo(nums, 0, -1, memo);
    }
    public int lengthOfLISHelperMemo(int[] nums, int index, int prevIndex, int[][] memo){
        if(index == nums.length) return 0;

        if(memo[index][prevIndex + 1] != -1){
            return memo[index][prevIndex + 1];
        }

        // Case 1: Include the current element if it's greater than the previous element
        int include = 0;
        if(prevIndex == -1 || nums[index] > nums[prevIndex]){
            include = 1 + lengthOfLISHelperMemo(nums, index + 1, index, memo);
        }

        // Case 2: Exclude the current element and move to the next
        int exclude = lengthOfLISHelperMemo(nums, index + 1, prevIndex, memo);

        // Store the result in the memoization table before returning
        memo[index][prevIndex + 1] = Math.max(include, exclude);
        return memo[index][prevIndex + 1];
    }
    public static void main(String[] args) {
        LongestIncreasingsubsequences lis = new LongestIncreasingsubsequences();
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Length of Longest Increasing Subsequence (Recursion): " + lis.lengthOfLIS(nums));
        System.out.println("Length of Longest Increasing Subsequence (Memoization): " + lis.lengthOfLISMemo(nums));
    }
}
