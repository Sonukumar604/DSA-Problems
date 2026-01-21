public class SplitArrayLargestSum {
    private boolean canSplit(int[] nums, int k, int maxSum) {
        int count = 1;
        int currentSum = 0;
        for (int num : nums) {
            if (num > maxSum) { // If a single element is greater than maxSum, it's impossible
                return false;
            }
            if (currentSum + num <= maxSum) {
                currentSum += num;
            } else {
                count++;
                currentSum = num;
            }
        }
        return count <= k;
    }

    public int splitArray(int[] nums, int k) {
        int low = 0; // Minimum possible largest sum (max element in nums)
        int high = 0; // Maximum possible largest sum (sum of all elements in nums)

        for (int num : nums) {
            low = Math.max(low, num);
            high += num;
        }

        int ans = high; // Initialize answer with the maximum possible sum

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canSplit(nums, k, mid)) {
                ans = mid;
                high = mid - 1; // Try to find a smaller maximum sum
            } else {
                low = mid + 1; // Need a larger maximum sum
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SplitArrayLargestSum solution = new SplitArrayLargestSum();
        int[] nums1 = {7, 2, 5, 10, 8};
        int k1 = 2;
        System.out.println("Minimum largest sum for nums1: " + solution.splitArray(nums1, k1)); // Expected: 18

        int[] nums2 = {1, 2, 3, 4, 5};
        int k2 = 2;
        System.out.println("Minimum largest sum for nums2: " + solution.splitArray(nums2, k2)); // Expected: 9

        int[] nums3 = {1, 4, 4};
        int k3 = 3;
        System.out.println
}
