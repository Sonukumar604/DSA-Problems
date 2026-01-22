package Neetcode;
public class MissingNumber {
    /**
     * Finds the kth missing positive number in a sorted array.
     *
     * Time Complexity: O(log N) - We perform binary search on the indices.
     * Space Complexity: O(1)
     */
    public int findKthPositive(int[] arr, int k){
        int low = 0, high = arr.length - 1;
        while(low <= high){
            int mid = low + (high - low) / 2;
            int missingCount = arr[mid] - (mid + 1);
            if(missingCount < k){
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low + k;
    }
    public static void main(String[] args) {
        MissingNumber obj = new MissingNumber();
        int[] arr = {2,3,4,7,11};
        int k = 5;
        int result = obj.findKthPositive(arr, k);
        System.out.println("The " + k + "th missing positive number is: " + result);
    }
}
