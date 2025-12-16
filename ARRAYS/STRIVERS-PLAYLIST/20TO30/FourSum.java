import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {

    // Optimized Approach using Sorting and Two Pointers
    // Time Complexity: O(N^3), dominated by the nested loops and two-pointer scan.
    // Space Complexity: O(1) (excluding the result list).
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }

        int n = nums.length;
        // 1. Sort the array to enable the two-pointer approach and handle duplicates.
        Arrays.sort(nums);

        // 2. Fix the first element and iterate.
        for (int i = 0; i < n - 3; i++) {
            // Skip duplicates for the first element.
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 3. Fix the second element and iterate.
            for (int j = i + 1; j < n - 2; j++) {
                // Skip duplicates for the second element.
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                // 4. Now, solve the 2Sum problem for the rest of the array.
                int left = j + 1;
                int right = n - 1;
                // Use long to prevent overflow when calculating the remaining target.
                long remainingTarget = (long) target - nums[i] - nums[j];

                while (left < right) {
                    long twoSum = nums[left] + nums[right];

                    if (twoSum == remainingTarget) {
                        // Found a quadruplet.
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // Skip duplicates for the third and fourth elements.
                        left++;
                        right--;
                        while (left < right && nums[left] == nums[left - 1]) left++;
                        while (left < right && nums[right] == nums[right + 1]) right--;
                    } else if (twoSum < remainingTarget) {
                        left++;
                    } else { // twoSum > remainingTarget
                        right--;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        System.out.println("Original Array: " + Arrays.toString(nums) + ", Target: " + target);
        System.out.println("Found Quadruplets: " + fourSum(nums, target));
    }
}
