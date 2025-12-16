// ...existing code...
import java.util.Arrays;

public class PartitionToKEqualSumSubset {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || k <= 0) return false;
        if (k == 1) return true;
        if (k > nums.length) return false;

        int totalSum = 0;
        for (int num : nums) totalSum += num;
        if (totalSum % k != 0) return false;
        int targetSum = totalSum / k;

        Arrays.sort(nums);
        // reverse to descending order for better pruning
        for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

        // If largest element greater than target, impossible
        if (nums[0] > targetSum) return false;

        boolean[] visited = new boolean[nums.length];
        return backtrack(nums, visited, 0, k, 0, targetSum);
    }

    private boolean backtrack(int[] nums, boolean[] visited, int startIndex, int k, int currentSum, int targetSum) {
        // If we've formed k-1 subsets successfully, the remaining numbers must form the last subset
        if (k == 1) return true;

        if (currentSum == targetSum) {
            // start building next subset from beginning
            return backtrack(nums, visited, 0, k - 1, 0, targetSum);
        }

        for (int i = startIndex; i < nums.length; i++) {
            if (visited[i]) continue;
            // skip duplicates to avoid redundant work
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;
            if (currentSum + nums[i] > targetSum) continue;

            visited[i] = true;
            if (backtrack(nums, visited, i + 1, k, currentSum + nums[i], targetSum)) {
                return true;
            }
            visited[i] = false;

            // If placing nums[i] at this position (when currentSum == 0) fails, no need to try other numbers
            if (currentSum == 0) return false;
        }
        return false;
    }

    // Example usage
    public static void main(String[] args) {
        int[] nums = {2,4,1,3,5};
        int k = 3;
        PartitionToKEqualSumSubset obj = new PartitionToKEqualSumSubset();
        System.out.println(obj.canPartitionKSubsets(nums, k)); // expected: true
    }
}
// ...existing code...
