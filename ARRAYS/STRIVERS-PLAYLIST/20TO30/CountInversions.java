import java.util.ArrayList;
import java.util.Arrays;

public class CountInversions {

    // Brute-Force Approach
    // Time Complexity: O(N^2), Space Complexity: O(1)
    public static int countInversionsBrute(int[] arr) {
        int n = arr.length;
        int count = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    // Optimal Approach using Merge Sort
    // Time Complexity: O(N log N), Space Complexity: O(N)
    public static int countInversionsOptimal(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return mergeSort(arr, 0, arr.length - 1);
    }

    private static int mergeSort(int[] arr, int low, int high) {
        int count = 0;
        if (low < high) {
            int mid = low + (high - low) / 2;
            count += mergeSort(arr, low, mid);       // Inversions in the left half
            count += mergeSort(arr, mid + 1, high);  // Inversions in the right half
            count += merge(arr, low, mid, high);     // Inversions between halves
        }
        return count;
    }

    private static int merge(int[] arr, int low, int mid, int high) {
        ArrayList<Integer> temp = new ArrayList<>();
        int left = low;
        int right = mid + 1;
        int count = 0;

        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left]);
                left++;
            } else {
                temp.add(arr[right]);
                right++;
                // This is the crucial step. If arr[left] > arr[right], then all
                // remaining elements in the left subarray (from arr[left] to arr[mid])
                // are also greater than arr[right] and will form an inversion.
                count += (mid - left + 1);
            }
        }

        while (left <= mid) {
            temp.add(arr[left++]);
        }
        while (right <= high) {
            temp.add(arr[right++]);
        }

        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 2, 4, 1};
        System.out.println("Original Array: " + Arrays.toString(arr));
        System.out.println("Brute-Force: Number of inversions is " + countInversionsBrute(arr));
        System.out.println("Optimal (Merge Sort): Number of inversions is " + countInversionsOptimal(arr.clone()));
    }
}
