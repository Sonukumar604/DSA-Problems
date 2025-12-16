public class SubarraySumMinimum {

    /**
     * Calculates the sum of minimums of all possible subarrays using a brute-force approach.
     * Time Complexity: O(n^2) where n is the length of the array.
     * Space Complexity: O(1)
     *
     * @param arr The input array of integers.
     * @return The sum of minimums of all subarrays.
     */
    public static int sumSubarrayMinsBruteForce(int[] arr) {
        // Handle null or empty array case.
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int totalSumOfMins = 0;
        int n = arr.length;
        // Iterate through each possible starting point of a subarray.
        for (int i = 0; i < n; i++) {
            // 'minInSubarray' will hold the minimum value for subarrays starting at 'i'.
            int minInSubarray = arr[i];
            // Iterate through each possible ending point of a subarray.
            for (int j = i; j < n; j++) {
                // As we expand the window from i to j, update the minimum value.
                minInSubarray = Math.min(minInSubarray, arr[j]);
                // Add the minimum of the current subarray (arr[i...j]) to the total sum.
                totalSumOfMins += minInSubarray;
            }
        }
        return totalSumOfMins;
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 4};
        // Expected output: (3) + (1) + (1) + (1) + (1) + (1) + (1) + (2) + (2) + (4) = 17
        System.out.println("The sum of subarray minimums is: " + sumSubarrayMinsBruteForce(arr));
    }
}
