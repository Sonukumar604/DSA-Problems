import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SubarraysXorK {

    // Brute-Force Approach
    // Time Complexity: O(N^3), Space Complexity: O(1)
    public static int subarraysXorKBrute(int[] nums, int k) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int currentXor = 0;
                for (int l = i; l <= j; l++) {
                    currentXor ^= nums[l];
                }
                if (currentXor == k) {
                    count++;
                }
            }
        }
        return count;
    }

    // Better Approach
    // Time Complexity: O(N^2), Space Complexity: O(1)
    public static int subarraysXorKBetter(int[] nums, int k) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int currentXor = 0;
            for (int j = i; j < n; j++) {
                currentXor ^= nums[j];
                if (currentXor == k) {
                    count++;
                }
            }
        }
        return count;
    }

    // Optimal Approach using HashMap (Prefix XOR)
    // Time Complexity: O(N), Space Complexity: O(N)
    public static int subarraysXorKOptimal(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> prefixXorMap = new HashMap<>();
        int prefixXor = 0;
        int count = 0;
        prefixXorMap.put(0, 1); // To handle subarrays starting from index 0

        for (int i = 0; i < n; i++) {
            prefixXor ^= nums[i];
            int requiredXor = prefixXor ^ k;
            count += prefixXorMap.getOrDefault(requiredXor, 0);
            prefixXorMap.put(prefixXor, prefixXorMap.getOrDefault(prefixXor, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {4, 2, 2, 6, 4};
        int k = 6;
        System.out.println("Array: " + Arrays.toString(nums) + ", K: " + k);
        System.out.println("Brute-Force: Number of subarrays is " + subarraysXorKBrute(nums, k));
        System.out.println("Better: Number of subarrays is " + subarraysXorKBetter(nums, k));
        System.out.println("Optimal (Hashing): Number of subarrays is " + subarraysXorKOptimal(nums, k));
    }
}
