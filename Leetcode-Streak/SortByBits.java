import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class SortByBits {

    /**
     * Sorts an array of integers based on the number of 1s in their binary representation.
     * If two numbers have the same number of 1s, they are sorted by their numerical value.
     *
     * Approach: Custom Comparator
     * 1. The array is converted to a List of Integers to leverage the powerful `sort` method.
     * 2. A custom comparator is provided to define the sorting logic.
     * 3. The comparator first calculates the bit count for two numbers, `a` and `b`, using `Integer.bitCount()`.
     * 4. Primary Sort: If the bit counts are different, it sorts based on the bit count (`countA - countB`).
     * 5. Secondary Sort: If the bit counts are the same, it sorts based on the numerical value (`a - b`).
     * 6. Finally, the sorted list is converted back to an integer array.
     *
     * Time Complexity: O(N log N)
     * - The dominant operation is the sorting of the list. `List.sort()` in Java uses TimSort, which has an average and worst-case time complexity of O(N log N).
     * - `Integer.bitCount()` is a highly optimized, often hardware-intrinsic operation, which can be considered O(1) for a 32-bit integer.
     *
     * Space Complexity: O(N)
     * - We create a new `List<Integer>` and a new `int[]` result array, both of which require space proportional to the number of elements in the input array.
     *
     * Dry Run:
     * Input: arr = [0, 1, 2, 3, 4, 5, 6, 7, 8]
     *
     * Bit Counts:
     * - 0 (0)      -> 0 bits
     * - 1 (1)      -> 1 bit
     * - 2 (10)     -> 1 bit
     * - 3 (11)     -> 2 bits
     * - 4 (100)    -> 1 bit
     * - 5 (101)    -> 2 bits
     * - 6 (110)    -> 2 bits
     * - 7 (111)    -> 3 bits
     * - 8 (1000)   -> 1 bit
     *
     * Sorting Process:
     * 1. Group by bit count:
     *    - 0 bits: [0]
     *    - 1 bit:  [1, 2, 4, 8]
     *    - 2 bits: [3, 5, 6]
     *    - 3 bits: [7]
     * 2. Sort within groups (secondary sort): The numbers within each group are already sorted numerically.
     *    For example, for 1 bit, the order is 1, 2, 4, 8. For 2 bits, it's 3, 5, 6.
     * 3. Combine groups based on bit count (primary sort):
     *    Concatenate the groups in ascending order of bit counts.
     * Final Result: [0, 1, 2, 4, 8, 3, 5, 6, 7]
     */
    public int[] sortByBits(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int num : arr) {
            list.add(num);
        }
        list.sort((a, b) -> {
            int countA = Integer.bitCount(a);
            int countB = Integer.bitCount(b);
            if (countA == countB) {
                return a - b;   // secondary sort
            }
            return countA - countB;
        });
        // Convert back to array
        int[] result = new int[arr.length];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    public static void main(String[] args) {
        SortByBits solution = new SortByBits();

        // Test Case 1 (from dry run)
        int[] arr1 = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println("Original Array 1: " + Arrays.toString(arr1));
        int[] result1 = solution.sortByBits(arr1);
        System.out.println("Sorted Array 1:   " + Arrays.toString(result1));
        System.out.println("Expected:         [0, 1, 2, 4, 8, 3, 5, 6, 7]");

        System.out.println();

        // Test Case 2
        int[] arr2 = {1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1};
        System.out.println("Original Array 2: " + Arrays.toString(arr2));
        int[] result2 = solution.sortByBits(arr2);
        System.out.println("Sorted Array 2:   " + Arrays.toString(result2));
        System.out.println("Expected:         [1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024]");
    }
}
