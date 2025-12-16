public class TrappingRainWater2 {
    /**
     * Calculates the total amount of trapped rainwater using an optimized two-pointer approach.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param height An array representing the height of bars.
     * @return The total units of trapped water.
     */
    public static int trappingRainWaterOptimized(int[] height) {
        if (height == null || height.length < 3) {
            return 0; // Cannot trap water with less than 3 bars.
        }

        int n = height.length;
        int left = 0;
        int right = n - 1;

        int leftMax = 0;
        int rightMax = 0;
        int trappedWater = 0;

        // Process the array from both ends
        while (left < right) {
            // The water level is limited by the shorter of the two walls (leftMax and rightMax).
            // We process the side with the shorter bar height first.
            if (height[left] <= height[right]) {
                // The left wall is shorter or equal, so the water level is determined by the left side.
                if (height[left] >= leftMax) {
                    // This bar is a new highest wall on the left, so it can't trap water. Update leftMax.
                    leftMax = height[left];
                } else {
                    // The current bar is shorter than leftMax, so it can trap water.
                    trappedWater += leftMax - height[left];
                }
                left++; // Move to the next bar from the left.
            } else {
                // The right wall is shorter, so the water level is determined by the right side.
                if (height[right] >= rightMax) {
                    // This bar is a new highest wall on the right. Update rightMax.
                    rightMax = height[right];
                } else {
                    // The current bar is shorter than rightMax, so it can trap water.
                    trappedWater += rightMax - height[right];
                }
                right--; // Move to the next bar from the right.
            }
        }
        return trappedWater;
    }

    public static void main(String[] args) {
        int[] arr = {0,1, 0,2, 1, 0,1, 3, 2, 1, 2, 1};
        System.out.println("Total trapped water: " + trappingRainWaterOptimized(arr));
    }
}
