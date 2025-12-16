import java.util.ArrayList;
import java.util.Arrays;

public class Solution {

    /**
     * Finds the repeating and missing numbers in an array of size n containing numbers from 1 to n.
     * This method operates in O(n) time and O(1) auxiliary space.
     *
     * <p><b>Important:</b> This method modifies the input array {@code arr} in-place.
     * If the original array needs to be preserved, pass a copy to this method.
     *
     * @param arr An integer array where one number is repeated and one is missing.
     * @return An ArrayList containing two elements: the repeating number at index 0,
     *         and the missing number at index 1.
     */
    public ArrayList<Integer> findTwoElement(int[] arr) {
        ArrayList<Integer> result = new ArrayList<>();
        int n = arr.length;
        int duplicate = -1;
        int missing = -1;

        // Find the duplicate number
        for (int i = 0; i < n; i++) {
            int index = Math.abs(arr[i]) - 1;
            if (arr[index] < 0) {
                duplicate = Math.abs(arr[i]); // Duplicate found
            } else {
                arr[index] = -arr[index]; // Mark as visited
            }
        }

        // Find the missing number
        for (int i = 0; i < n; i++) {
            if (arr[i] > 0) {// if positive, index + 1 is missing
                missing = i + 1; // Missing number found
                break;
            }
        }

        result.add(duplicate);
        result.add(missing);
        return result;
    }
    public static void main(String[] args) {
        Solution sol = new Solution();
        // The input array should contain numbers from 1 to n. Here n=5.
        // The numbers are {1, 3, 3, 4, 5}. Missing=2, Repeating=3.
        int[] arr = {3, 1, 3, 5, 4}; 
        System.out.println("Original array: " + Arrays.toString(arr.clone())); // Print a clone to show original state

        ArrayList<Integer> result = sol.findTwoElement(arr);

        System.out.println("Duplicate: " + result.get(0) + ", Missing: " + result.get(1));
        System.out.println("Array after in-place modification: " + Arrays.toString(arr));
    }
}
