import java.util.LinkedHashMap;
import java.util.Map;

public class FirstNonRepeating {
    /**
     * Finds the first non-repeating element using a brute-force approach.
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     *
     * @param arr The input array.
     * @return The first non-repeating element, or -1 if none is found.
     */
    public static int firstNonRepeatingBruteForce(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            boolean isRepeating = false;
            // Check if arr[i] is repeated elsewhere in the array
            for (int j = 0; j < n; j++) {
                // We must ensure we are not comparing the element to itself
                if (i != j && arr[i] == arr[j]) {
                    isRepeating = true;
                    break; // Found a repeat, no need to check further for this element
                }
            }
            // If after checking all other elements, it's not repeating, it's our answer.
            if (!isRepeating) {
                return arr[i];
            }
        }
        // If the loop completes, it means every element has a duplicate.
        return -1;
    }

    /**
     * Finds the first non-repeating element using a LinkedHashMap to maintain order and count frequencies.
     * Time Complexity: O(n)
     * Space Complexity: O(k) where k is the number of unique elements.
     *
     * @param arr The input array.
     * @return The first non-repeating element, or -1 if none is found.
     */
    public static int firstNonRepeating(int[] arr) {
        Map<Integer, Integer> counts = new LinkedHashMap<>();

        // First pass: build the frequency map.
        // LinkedHashMap preserves the insertion order.
        for (int num : arr) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        // Second pass: find the first element with a count of 1.
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return -1; // No non-repeating element found.
    }

    public static void main(String[] args) {
        int[] arr = {9, 4, 9, 6, 7, 4}; // Expected: 6
        System.out.println("Array: [9, 4, 9, 6, 7, 4]");
        System.out.println("Brute-Force: " + firstNonRepeatingBruteForce(arr));
        System.out.println("Optimal (HashMap): " + firstNonRepeating(arr));
        System.out.println("------------------------------------");

        // Test case with multiple non-repeating elements. Expected: 3
        int[] arr2 = {1, 5, 3, 4, 3, 5, 6};
        System.out.println("Array: [1, 5, 3, 4, 3, 5, 6]");
        System.out.println("Brute-Force: " + firstNonRepeatingBruteForce(arr2));
        System.out.println("Optimal (HashMap): " + firstNonRepeating(arr2));
        System.out.println("------------------------------------");

        // Test case with no non-repeating elements. Expected: -1
        int[] arr3 = {1, 1, 2, 2, 3, 3};
        System.out.println("Array: [1, 1, 2, 2, 3, 3]");
        System.out.println("Brute-Force: " + firstNonRepeatingBruteForce(arr3));
        System.out.println("Optimal (HashMap): " + firstNonRepeating(arr3));
        System.out.println("------------------------------------");

        // Test case with a single element. Expected: 10
        int[] arr4 = {10};
        System.out.println("Array: [10]");
        System.out.println("Brute-Force: " + firstNonRepeatingBruteForce(arr4));
        System.out.println("Optimal (HashMap): " + firstNonRepeating(arr4));
        System.out.println("------------------------------------");

        // Test case with an empty array. Expected: -1
        int[] arr5 = {};
        System.out.println("Array: []");
        System.out.println("Brute-Force: " + firstNonRepeatingBruteForce(arr5));
        System.out.println("Optimal (HashMap): " + firstNonRepeating(arr5));
    }
}
