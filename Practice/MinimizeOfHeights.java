import java.util.Arrays;

public class MinimizeOfHeights {
    /**
     * Calculates the minimum difference between the maximum and minimum heights
     * after modifying each height by k.
     * Time Complexity: O(N log N) due to sorting.
     * Space Complexity: O(1).
     *
     * @param arr The array of heights.
     * @param k The value to add or subtract.
     * @return The minimum possible difference.
     */
    public int getMinDiff(int[] arr, int k){
        int n = arr.length;
        // 1. Sort the array
        Arrays.sort(arr);

        // 2. Initialize the result with the initial difference
        int ans = arr[n - 1] - arr[0];

        // 3. Define potential smallest and largest elements after modification
        int smallest = arr[0] + k;
        int largest = arr[n - 1] - k;

        // 4. Iterate through the array to find the minimum difference
        for (int i = 0; i < n - 1; i++) {
            // Potential new minimum height
            int min_height = Math.min(smallest, arr[i + 1] - k);
            // Potential new maximum height
            int max_height = Math.max(largest, arr[i] + k);

            // We only consider this pair if the minimum height is non-negative
            if (min_height >= 0) {
                ans = Math.min(ans, max_height - min_height);
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        MinimizeOfHeights obj = new MinimizeOfHeights();
        int[] arr = {3, 9, 12, 16, 20};
        int k = 3;
        // Expected output: 11. After modification, array can become {6, 12, 9, 13, 17}. Min=6, Max=17. Diff=11.
        System.out.println("The minimum difference is: " + obj.getMinDiff(arr, k));
    }
}
