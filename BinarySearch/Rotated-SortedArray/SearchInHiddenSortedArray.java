
public class SearchInHiddenSortedArray {

    private int[] nums;

    public SearchInHiddenSortedArray(int[] nums) {
        this.nums = nums;
    }

    // Mock API (in real problems this is given)
    int get(int index) {
        if (index >= nums.length) return Integer.MAX_VALUE;
        return nums[index];
    }

    public int search(int target) {

        // ---------- STEP 1: FIND SEARCH RANGE ----------
        int low = 0;
        int high = nums.length - 1;

        // ---------- STEP 2: FIND PIVOT (MIN ELEMENT) ----------
        int pivot = findPivot(low, high);

        // ---------- STEP 3: DECIDE WHICH HALF ----------
        if (get(pivot) <= target && target <= get(high)) {
            return binarySearch(pivot, high, target);
        } else {
            return binarySearch(low, pivot - 1, target);
        }
    }

    // ---------- FIND MINIMUM INDEX (PIVOT) ----------
    private int findPivot(int low, int high) {
        while (low < high) {
            int mid = low + (high - low) / 2;

            if (get(mid) > get(high)) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    // ---------- STANDARD BINARY SEARCH ----------
    private int binarySearch(int low, int high, int target) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int value = get(mid);

            if (value == target) return mid;
            if (value > target) high = mid - 1;
            else low = mid + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 7, 0, 1, 2};
        SearchInHiddenSortedArray sol = new SearchInHiddenSortedArray(arr);
        int target = 0;
        System.out.println("Index of " + target + ": " + sol.search(target)); // Expected: 4
    }
}
