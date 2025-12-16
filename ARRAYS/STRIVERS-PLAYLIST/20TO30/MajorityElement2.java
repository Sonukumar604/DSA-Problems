import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MajorityElement2 {

    // Brute-Force Approach
    // Time Complexity: O(N^2), Space Complexity: O(1) (excluding result list)
    public static List<Integer> majorityElementBrute(int[] nums) {
        int n = nums.length;
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            // Skip if this element is already in the result list to avoid duplicates
            if (result.contains(nums[i])) {
                continue;
            }
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (nums[j] == nums[i]) {
                    count++;
                }
            }
            if (count > n / 3) {
                result.add(nums[i]);
            }
        }
        return result;
    }

    // Better Approach using HashMap
    // Time Complexity: O(N), Space Complexity: O(N)
    public static List<Integer> majorityElementBetter(int[] nums) {
        int n = nums.length;
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();

        // Store frequencies of each element
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Find elements with frequency > n/3
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > n / 3) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    // Optimal Approach: Extended Moore's Voting Algorithm
    // Time Complexity: O(N), Space Complexity: O(1)
    public static List<Integer> majorityElementOptimal(int[] nums) {
        int n = nums.length;
        int count1 = 0, count2 = 0;
        Integer candidate1 = null, candidate2 = null;

        // 1. Find up to two potential candidates
        for (int num : nums) {
            if (candidate1 != null && candidate1 == num) {
                count1++;
            } else if (candidate2 != null && candidate2 == num) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        // 2. Verify the candidates
        List<Integer> result = new ArrayList<>();
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (candidate1 != null && num == candidate1) count1++;
            if (candidate2 != null && num == candidate2) count2++;
        }

        if (count1 > n / 3) result.add(candidate1);
        if (count2 > n / 3) result.add(candidate2);

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 3};
        System.out.println("Original Array: " + Arrays.toString(nums));
        System.out.println("Brute-Force: Majority elements are " + majorityElementBrute(nums));
        System.out.println("Better (Hashing): Majority elements are " + majorityElementBetter(nums));
        System.out.println("Optimal (Moore's Voting): Majority elements are " + majorityElementOptimal(nums));
        System.out.println();

        int[] nums2 = {1, 1, 1, 3, 3, 2, 2, 2};
        System.out.println("Original Array: " + Arrays.toString(nums2));
        System.out.println("Brute-Force: Majority elements are " + majorityElementBrute(nums2));
        System.out.println("Better (Hashing): Majority elements are " + majorityElementBetter(nums2));
        System.out.println("Optimal (Moore's Voting): Majority elements are " + majorityElementOptimal(nums2));
    }
}
