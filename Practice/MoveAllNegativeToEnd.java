import java.util.Arrays;

public class MoveAllNegativeToEnd {
    /**
     * Moves all negative elements to the end of the array without changing the
     * relative order of positive and negative elements.
     *
     * This approach uses an auxiliary array and is the correct way to solve the
     * problem as per typical platform requirements (like GeeksforGeeks)
     * that mandate preserving the relative order of elements.
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     *
     * @param arr The array to be modified in-place.
     */
    public void segregateElements(int[] arr) {
        int n = arr.length;
        if (n == 0) {
            return; // Nothing to do for an empty array
        }

        int[] temp = new int[n];
        int k = 0; // Index for the temp array

        // 1. Add all positive elements to temp
        for (int i = 0; i < n; i++) {
            if (arr[i] >= 0) {
                temp[k++] = arr[i];
            }
        }

        // 2. Add all negative elements to temp
        for (int i = 0; i < n; i++) {
            if (arr[i] < 0) {
                temp[k++] = arr[i];
            }
        }

        // 3. Copy temp back to the original array
        System.arraycopy(temp, 0, arr, 0, n);
    }

    public static void main(String[] args) {
        MoveAllNegativeToEnd mover = new MoveAllNegativeToEnd();
        int[] arr = {1, -1, 3, 2, -7, -5, 11, 6};
        System.out.println("Original array: " + Arrays.toString(arr));
        mover.segregateElements(arr);
        System.out.println("Array after moving negatives to end: " + Arrays.toString(arr));
    }

}
