public class LongestSubarraySumK {
    /**
     * Finds the length of the longest subarray with a sum equal to k.
     *
     * NOTE: This two-pointer/sliding window approach is optimal and correct
     * ONLY for arrays containing POSITIVE integers. If the array can contain
     * negative numbers or zeros, a different approach using a HashMap to store
     * prefix sums is required.
     *
     * @param arr The input array of positive integers.
     * @param k The target sum.
     * @return The length of the longest subarray, or 0 if no such subarray is found.
     */
    public static int largestSubarraySumK(int[] arr, int k){
        int left = 0;
        long currentSum = 0;
        int maxLen = 0;
        for(int right = 0; right < arr.length; right++){
            currentSum += arr[right];

            // If the sum exceeds k, shrink the window from the left.
            while(currentSum > k && left <= right){
                currentSum -= arr[left];
                left++;
            }

            // If the sum is exactly k, we have a candidate subarray.
            // Update the maximum length found so far.
            if(currentSum == k){
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] arr = {4, 1, 1, 1, 2, 3, 5};
        int k = 5;
        int result = largestSubarraySumK(arr, k);
        System.out.println("The length of the longest subarray with sum " + k + " is: " + result);

        // Example where the longest subarray is at the end
        int[] arr2 = {1, 2, 3, 7};
        int k2 = 5;
        int result2 = largestSubarraySumK(arr2, k2);
        System.out.println("The length of the longest subarray with sum " + k2 + " is: " + result2);
    }

}
