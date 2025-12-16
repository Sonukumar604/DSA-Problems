import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement {
    // Brute-Force Approach
    // Time Complexity: O(N^2), Space Complexity: O(1)
    public static int majorityElementBrute(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (nums[j] == nums[i]) {
                    count++;
                }
            }
            if (count > n / 2) {
                return nums[i];
            }
        }
        return -1; // Should not be reached if a majority element is guaranteed
    }

    // Better Approach using HashMap
    // Time Complexity: O(N), Space Complexity: O(N)
    public static int majorityElementBetter(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        // Store frequencies of each element
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Find the element with frequency > n/2
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > n / 2) {
                return entry.getKey();
            }
        }
        return -1;
    }

    // Optimal Approach: Moore's Voting Algorithm
    // Time Complexity: O(N), Space Complexity: O(1)
    public static int majorityElementOptimal(int[] nums) {
        int count = 0;
        int candidate = 0;

        // Find a potential candidate
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        // Note: The problem usually guarantees a majority element exists, so the candidate is the answer.
        // A second pass to verify the candidate's count is only needed if it's not guaranteed.
        return candidate;
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 1, 1, 2, 2};
        System.out.println("Original Array: " + Arrays.toString(nums));
        System.out.println("Brute-Force: The majority element is " + majorityElementBrute(nums));
        System.out.println("Better (Hashing): The majority element is " + majorityElementBetter(nums));
        System.out.println("Optimal (Moore's Voting): The majority element is " + majorityElementOptimal(nums));
    }
}
