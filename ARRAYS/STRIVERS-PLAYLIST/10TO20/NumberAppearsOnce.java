import java.util.HashMap;
import java.util.Map;

public class NumberAppearsOnce {
    //Brute Approach
    //Time Complextiy : O(n^2), Space Complexity : O(1)
    public static int findSingleNumber(int[] nums){
        int n = nums.length;
        for(int i = 0; i < n; i++){
            int num = nums[i];
            int count = 0;
            // The inner loop must scan the entire array to count occurrences.
            for(int j = 0; j < n; j++){
                if(nums[j] == num){
                    count++;
                }
            }
            if(count == 1){
                return num;
            }
        }
        return -1;
    }

    // Better Approach using HashMap
    // Time Complexity: O(N), Space Complexity: O(N)
    public static int findSingleNumberBetter(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        // Count frequencies of each number.
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // Find the number with a frequency of 1.
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return -1;
    }

    // Optimal Approach using XOR
    // Time Complexity: O(N), Space Complexity: O(1)
    public static int findSingleNumberOptimal(int[] nums) {
        int xorResult = 0;
        // XORing all elements will cancel out the pairs, leaving the single number.
        // a ^ a = 0
        // a ^ 0 = a
        for (int num : nums) {
            xorResult = xorResult ^ num;
        }
        return xorResult;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 3, 3, 4, 4, 5, 5}; // The single number is 2
        System.out.println("Brute Force: The number that appears once is: " + findSingleNumber(nums));
        System.out.println("Better (HashMap): The number that appears once is: " + findSingleNumberBetter(nums));
        System.out.println("Optimal (XOR): The number that appears once is: " + findSingleNumberOptimal(nums));
    }
}
