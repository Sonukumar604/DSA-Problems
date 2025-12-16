import java.util.Arrays;

public class MergeSortedArrays {

    // Function to merge two sorted arrays
    public static int[] merge(int[] arr1, int[] arr2) {
        int m = arr1.length;
        int n = arr2.length;
        int[] merged = new int[m + n];
        int i = 0, j = 0, k = 0;

        // Merge the two arrays
        while (i < m && j < n) {
            if (arr1[i] < arr2[j]) {
                merged[k++] = arr1[i++];
            } else {
                merged[k++] = arr2[j++];
            }
        }

        // Copy any remaining elements from either array
        while (i < m) {
            merged[k++] = arr1[i++];
        }
        while (j < n) {
            merged[k++] = arr2[j++];
        }

        return merged;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {2, 4, 6, 8};
        int[] merged = merge(arr1, arr2);
        System.out.println("Merged Array: " + Arrays.toString(merged));
    }
}
