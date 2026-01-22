public class UpperBound {
    /**
     * Finds the upper bound index of a target in a sorted array.
     * The upper bound is the smallest index `i` such that `arr[i] > target`.
     *
     * Time Complexity: O(log N) - We use binary search to halve the search space.
     * Space Complexity: O(1) - We use constant extra space.
     */
    public static int upperBound(int[] arr, int target){
        int low = 0;
        int high = arr.length - 1;
        int ans = arr.length;
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(arr[mid] > target){
                ans = mid;
                high = mid - 1;
            }
            else{
                low = mid + 1;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 8, 10, 12};
        int target = 7;

        int ub = upperBound(arr, target);
        System.out.println("Upper Bound index: " + ub);
    }
}
