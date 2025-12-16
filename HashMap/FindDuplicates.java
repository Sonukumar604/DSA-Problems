import java.util.Arrays;

public class FindDuplicates {
    /**
     * Finds the single duplicate number in an array of n+1 integers where
     * each integer is in the range [1, n]. This is a classic application of
     * Floyd's Tortoise and Hare (cycle detection) algorithm.
     *
     * @param nums An array of size n+1 with numbers from 1 to n.
     * @return The duplicate number.
     */
    // Corrected method name to follow Java conventions (camelCase)
    public static int findDuplicatesUsingCycleDetection(int[] nums){
        if(nums == null || nums.length < 2){
            throw new IllegalArgumentException("Input array must contain at least two numbers");
        }
        int slow = nums[0];
        int fast = nums[0];
        do{
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while(slow != fast);
        
        // Reset slow to the start and find the entrance of the cycle
        slow = nums[0];
        while(slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public static void main(String[] args) {
        // This problem variant assumes n+1 numbers from 1 to n.
        // e.g., 5 numbers from 1 to 4.
        int[] duplicateNums = {1, 3, 4, 2, 2};
        System.out.println("Input Array: " + Arrays.toString(duplicateNums));
        int duplicate = findDuplicatesUsingCycleDetection(duplicateNums);
        System.out.println("The duplicate number is: " + duplicate); // Expected: 2

        int[] duplicateNums2 = {3, 1, 3, 4, 2};
        System.out.println("\nInput Array: " + Arrays.toString(duplicateNums2));
        int duplicate2 = findDuplicatesUsingCycleDetection(duplicateNums2);
        System.out.println("The duplicate number is: " + duplicate2); // Expected: 3
    }
}
