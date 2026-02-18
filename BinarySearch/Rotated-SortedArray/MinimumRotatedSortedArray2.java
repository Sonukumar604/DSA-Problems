import java.util.Arrays;

public class MinimumRotatedSortedArray2 {
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        int ans = Integer.MAX_VALUE;
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(nums[low] == nums[mid] && nums[mid] == nums[high]){
                ans = Math.min(ans, nums[low]);
                low++;
                high--;
                continue;
            }
            else if(nums[mid] <= nums[high]){
                ans = Math.min(ans, nums[mid]);
                high = mid - 1;
            }
            else{
                ans = Math.min(ans, nums[low]);
                low = mid + 1;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        MinimumRotatedSortedArray2 solution = new MinimumRotatedSortedArray2();
        
        // Test Case 1: Rotated array with duplicates
        int[] nums1 = {2, 2, 2, 0, 1};
        System.out.println("Array: " + Arrays.toString(nums1));
        System.out.println("Minimum element: " + solution.findMin(nums1));

        // Test Case 2: Rotated array with duplicates (pivot inside)
        int[] nums2 = {3, 3, 1, 3};
        System.out.println("Array: " + Arrays.toString(nums2));
        System.out.println("Minimum element: " + solution.findMin(nums2));

        // Test Case 3: All elements are the same
        int[] nums3 = {1, 1, 1};
        System.out.println("Array: " + Arrays.toString(nums3));
        System.out.println("Minimum element: " + solution.findMin(nums3));
    }
}
