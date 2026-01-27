public class SearchInRange {
    /**
     * Finds the starting and ending position of a given target value.
     *
     * Time Complexity: O(log N) - We perform two binary searches.
     * Space Complexity: O(1) - We use iterative binary search.
     */
    public int[] searchRange(int[] nums, int target) {
        int first = lowerBound(nums, target);
        
        // target not found
        if (first == nums.length || nums[first] != target) {
            return new int[]{-1, -1};
        }
        
        int last = upperBound(nums, target) - 1;
        return new int[]{first, last};
    }

    // First index where nums[i] >= target
    private int lowerBound(int[] nums, int target) {
        int low = 0, high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    // First index where nums[i] > target
    private int upperBound(int[] nums, int target) {
        int low = 0, high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    public static void main(String[] args) {
        SearchInRange solution = new SearchInRange();
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        int[] range = solution.searchRange(nums, target);
        System.out.println("First and Last Occurrence of " + target + ": [" + range[0] + ", " + range[1] + "]");
    }

}
