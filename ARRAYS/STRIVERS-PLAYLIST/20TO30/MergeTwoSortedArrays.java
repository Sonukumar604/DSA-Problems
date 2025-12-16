import java.util.Arrays;

public class MergeTwoSortedArrays {

    // Brute-Force Approach
    // Time Complexity: O((m+n) log(m+n)), Space Complexity: O(m+n)
    public static void mergeBrute(long[] arr1, long[] arr2, int m, int n) {
        long[] merged = new long[m + n];
        int k = 0;
        for (int i = 0; i < m; i++) {
            merged[k++] = arr1[i];
        }
        for (int i = 0; i < n; i++) {
            merged[k++] = arr2[i];
        }
        Arrays.sort(merged);

        // Copy back the sorted elements
        for (int i = 0; i < m; i++) {
            arr1[i] = merged[i];
        }
        for (int i = 0; i < n; i++) {
            arr2[i] = merged[m + i];
        }
    }

    // Better Approach (Two Pointers with extra space)
    // Time Complexity: O(m+n), Space Complexity: O(m+n)
    public static void mergeBetter(long[] arr1, long[] arr2, int m, int n) {
        long[] merged = new long[m + n];
        int i = 0, j = 0, k = 0;

        while (i < m && j < n) {
            if (arr1[i] <= arr2[j]) {
                merged[k++] = arr1[i++];
            } else {
                merged[k++] = arr2[j++];
            }
        }

        while (i < m) {
            merged[k++] = arr1[i++];
        }
        while (j < n) {
            merged[k++] = arr2[j++];
        }

        // Copy back the sorted elements
        for (int l = 0; l < m + n; l++) {
            if (l < m) {
                arr1[l] = merged[l];
            } else {
                arr2[l - m] = merged[l];
            }
        }
    }

    // Optimal Approach (Gap Method)
    // Time Complexity: O((m+n) log(m+n)), Space Complexity: O(1)
    public static void mergeOptimal(long[] arr1, long[] arr2, int m, int n) {
        int len = m + n;
        int gap = (len / 2) + (len % 2);

        while (gap > 0) {
            int left = 0;
            int right = left + gap;
            while (right < len) {
                // Case 1: Both pointers are in arr1
                if (left < m && right < m) {
                    if (arr1[left] > arr1[right]) swap(arr1, arr1, left, right);
                }
                // Case 2: left is in arr1, right is in arr2
                else if (left < m && right >= m) {
                    if (arr1[left] > arr2[right - m]) swap(arr1, arr2, left, right - m);
                }
                // Case 3: Both pointers are in arr2
                else {
                    if (arr2[left - m] > arr2[right - m]) swap(arr2, arr2, left - m, right - m);
                }
                left++;
                right++;
            }
            if (gap == 1) break;
            gap = (gap / 2) + (gap % 2);
        }
    }

    private static void swap(long[] arr1, long[] arr2, int i, int j) {
        long temp = arr1[i];
        arr1[i] = arr2[j];
        arr2[j] = temp;
    }

    public static void main(String[] args) {
        long[] arr1 = {1, 4, 8, 10};
        long[] arr2 = {2, 3, 9};
        int m = arr1.length;
        int n = arr2.length;

        // --- Brute-Force Test ---
        long[] arr1_brute = arr1.clone();
        long[] arr2_brute = arr2.clone();
        mergeBrute(arr1_brute, arr2_brute, m, n);
        System.out.println("Brute-Force Result:");
        System.out.println("arr1: " + Arrays.toString(arr1_brute));
        System.out.println("arr2: " + Arrays.toString(arr2_brute));
        System.out.println();

        // --- Better Approach Test ---
        long[] arr1_better = arr1.clone();
        long[] arr2_better = arr2.clone();
        mergeBetter(arr1_better, arr2_better, m, n);
        System.out.println("Better Approach Result:");
        System.out.println("arr1: " + Arrays.toString(arr1_better));
        System.out.println("arr2: " + Arrays.toString(arr2_better));
        System.out.println();

        // --- Optimal Approach Test ---
        long[] arr1_optimal = arr1.clone();
        long[] arr2_optimal = arr2.clone();
        mergeOptimal(arr1_optimal, arr2_optimal, m, n);
        System.out.println("Optimal (Gap Method) Result:");
        System.out.println("arr1: " + Arrays.toString(arr1_optimal));
        System.out.println("arr2: " + Arrays.toString(arr2_optimal));
    }
}
