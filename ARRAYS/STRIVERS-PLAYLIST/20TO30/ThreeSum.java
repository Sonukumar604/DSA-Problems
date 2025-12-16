import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThreeSum {

    // Brute-Force Approach
    // Time Complexity: O(N^3 * log(M)), where M is the number of triplets.
    // Space Complexity: O(M) for the HashSet + O(M) for the result list.
    public static List<List<Integer>> threeSumBrute(int[] nums) {
        int n = nums.length;
        Set<List<Integer>> resultSet = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> triplet = Arrays.asList(nums[i], nums[j], nums[k]);
                        // Sort the triplet to handle duplicates regardless of order.
                        triplet.sort(null);
                        resultSet.add(triplet);
                    }
                }
            }
        }
        return new ArrayList<>(resultSet);
    }

    // Optimized Approach using Two Pointers
    // Time Complexity: O(N^2) + O(N log N) for sorting.
    // Space Complexity: O(1) (excluding the result list).
    public static List<List<Integer>> threeSumOptimal(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        // 1. Sort the array. This is crucial for the two-pointer approach.
        Arrays.sort(nums);

        // 2. Iterate through the array, fixing the first element of the triplet.
        for (int i = 0; i < n - 2; i++) {
            // Avoid duplicate triplets by skipping over identical elements for the first number.
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 3. Use two pointers for the remaining part of the array.
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    // Found a triplet.
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Move pointers and skip duplicates for the second and third numbers.
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                } else if (sum < 0) {
                    // The sum is too small, need a larger number.
                    left++;
                } else { // sum > 0
                    // The sum is too large, need a smaller number.
                    right--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println("Original Array: " + Arrays.toString(nums));

        System.out.println("\nBrute-Force Solution:");
        System.out.println(threeSumBrute(nums));

        System.out.println("\nOptimal (Two-Pointer) Solution:");
        System.out.println(threeSumOptimal(nums));
    }
}
