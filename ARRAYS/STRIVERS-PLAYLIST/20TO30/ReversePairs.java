import java.util.ArrayList;
import java.util.Arrays;

public class ReversePairs {

    // Brute-Force Approach
    // Time Complexity: O(N^2), Space Complexity: O(1)
    public static int reversePairsBrute(int[] nums) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Use long to prevent overflow when multiplying
                if (nums[i] > 2L * nums[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    // Optimal Approach using Merge Sort
    // Time Complexity: O(N log N), Space Complexity: O(N)
    public static int reversePairsOptimal(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        return mergeSort(nums, 0, nums.length - 1);
    }

    private static int mergeSort(int[] arr, int low, int high) {
        if (low >= high) {
            return 0;
        }
        int mid = low + (high - low) / 2;
        int count = 0;
        count += mergeSort(arr, low, mid);
        count += mergeSort(arr, mid + 1, high);
        count += merge(arr, low, mid, high);
        return count;
    }

    private static int merge(int[] arr, int low, int mid, int high) {
        int count = 0;
        int j = mid + 1;
        // --- This is the core logic for counting reverse pairs ---
        for (int i = low; i <= mid; i++) {
            while (j <= high && arr[i] > 2L * arr[j]) {
                j++;
            }
            count += (j - (mid + 1));
        }
        // --- Standard merge logic ---
        ArrayList<Integer> temp = new ArrayList<>();
        int left = low, right = mid + 1;
        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left++]);
            } else {
                temp.add(arr[right++]);
            }
        }
        while (left <= mid) temp.add(arr[left++]);
        while (right <= high) temp.add(arr[right++]);

        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 3, 1};
        System.out.println("Original Array: " + Arrays.toString(nums));
        System.out.println("Brute-Force: Number of reverse pairs is " + reversePairsBrute(nums));
        System.out.println("Optimal (Merge Sort): Number of reverse pairs is " + reversePairsOptimal(nums.clone()));
    }
}
