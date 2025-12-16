import java.util.Arrays;

public class MaximumProductSubarray {

    // Brute-Force Approach
    // Time Complexity: O(N^2), Space Complexity: O(1)
    // Note: A pure O(N^3) brute-force would re-calculate the product for each subarray.
    // This version is already slightly optimized to O(N^2).
    public static int maxProductBrute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxProduct = nums[0];
        for (int i = 0; i < nums.length; i++) {
            long currentProduct = 1;
            for (int j = i; j < nums.length; j++) {
                currentProduct *= nums[j];
                if (currentProduct > maxProduct) {
                    maxProduct = (int) currentProduct;
                }
            }
        }
        return maxProduct;
    }

    // Optimal Approach
    // Time Complexity: O(N), Space Complexity: O(1)
    public static int maxProductOptimal(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxProduct = nums[0];
        int maxEndingHere = nums[0];
        int minEndingHere = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];

            // When we see a negative number, the roles of max and min flip.
            // A large negative minEndingHere can become a large positive maxEndingHere.
            if (current < 0) {
                int temp = maxEndingHere;
                maxEndingHere = minEndingHere;
                minEndingHere = temp;
            }

            // Update the max and min products ending at the current position.
            maxEndingHere = Math.max(current, maxEndingHere * current);
            minEndingHere = Math.min(current, minEndingHere * current);

            // Update the overall maximum product found so far.
            maxProduct = Math.max(maxProduct, maxEndingHere);
        }
        return maxProduct;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, -2, 4};
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("Brute-Force: Maximum product is " + maxProductBrute(nums));
        System.out.println("Optimal: Maximum product is " + maxProductOptimal(nums));
    }
}
