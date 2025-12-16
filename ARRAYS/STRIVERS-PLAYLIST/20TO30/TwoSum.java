import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    // Brute-Force Approach
    // Time Complexity: O(N^2), Space Complexity: O(1)
    public static int[] twoSumBrute(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1}; // Return if no solution is found
    }

    // Better/Optimal Approach using HashMap
    // Time Complexity: O(N), Space Complexity: O(N)
    public static int[] twoSumOptimal(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int complement = target - nums[i];
            if (numMap.containsKey(complement)) {
                return new int[]{numMap.get(complement), i};
            }
            numMap.put(nums[i], i);
        }

        return new int[]{-1, -1}; // Return if no solution is found
    }

    // Variant: Check if a pair exists (Two-Pointer Approach)
    // This approach works best if you only need to return 'yes' or 'no',
    // or the values themselves, as sorting loses original indices.
    // Time Complexity: O(N log N), Space Complexity: O(1)
    public static boolean hasTwoSumPair(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return true;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        System.out.println("Original Array: " + Arrays.toString(nums) + ", Target: " + target);
        System.out.println("Brute-Force: Indices are " + Arrays.toString(twoSumBrute(nums, target)));
        System.out.println("Optimal (HashMap): Indices are " + Arrays.toString(twoSumOptimal(nums, target)));
        System.out.println("Variant (Two-Pointer): Pair exists? " + hasTwoSumPair(Arrays.copyOf(nums, nums.length), target));
    }
}
