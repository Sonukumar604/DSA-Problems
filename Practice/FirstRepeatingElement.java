import java.util.HashSet;
import java.util.HashMap;

public class FirstRepeatingElement {
    // The element should occur more than once and the index of its first occurrence should be the smallest.
    // The function should return the 1-based index of the first occurrence.

    // Approach 1: Brute Force
    // Time Complexity: O(n^2), Space Complexity: O(1)
    public static int firstRepeated(int[] arr) {
        int n = arr.length;
        // Iterate through each element
        for (int i = 0; i < n; i++) {
            // Check if this element repeats in the rest of the array
            for (int j = i + 1; j < n; j++) {
                if (arr[i] == arr[j]) {
                    // Found a repeating element. Since we are iterating from the left,
                    // the first one we find is our answer.
                    // Return its 1-based index.
                    return i + 1;
                }
            }
        }
        // If the loops complete, no repeating element was found.
        return -1;
    }

    // Approach 2: Using HashSet
    // Time Complexity: O(n), Space Complexity: O(n)
    public static int firstRepeatedWithHashSet(int[] arr) {
        HashSet<Integer> seen = new HashSet<>();
        int minIndex = -1;

        // Iterate from right to left. The last time we see a duplicate,
        // its index will be the smallest index of a first occurrence.
        for (int i = arr.length - 1; i >= 0; i--) {
            // If we have seen this element before, it's a repeating element.
            if (seen.contains(arr[i])) {
                // Update the minimum index found so far.
                minIndex = i;
            } else {
                // If it's the first time seeing it, add it to the set.
                seen.add(arr[i]);
            }
        }

        if (minIndex != -1) {
            // Return the 1-based index
            return minIndex + 1;
        }

        // No repeating element found
        return -1;
    }

    // Approach 3: Using HashMap
    // Time Complexity: O(n), Space Complexity: O(n)
    public static int firstRepeatedWithHashMap(int[] arr) {
        HashMap<Integer, Integer> firstOccurrence = new HashMap<>();
        int minIndex = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (firstOccurrence.containsKey(arr[i])) {
                // This is a repeating element.
                // Get the index of its first occurrence.
                int firstIndex = firstOccurrence.get(arr[i]);
                // Update minIndex if this element's first occurrence is earlier.
                minIndex = Math.min(minIndex, firstIndex);
            } else {
                // First time seeing this element, store its index.
                firstOccurrence.put(arr[i], i);
            }
        }

        // If minIndex was never updated, no repeating element was found.
        if (minIndex == Integer.MAX_VALUE) {
            return -1;
        }

        // Return the 1-based index.
        return minIndex + 1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 3, 4, 3, 5, 6}; // Expected output: 2 (because 5 repeats, and its first occurrence is at index 1)
        System.out.println("Brute Force: The first repeating element is at position: " + firstRepeated(arr));
        System.out.println("HashSet Approach: The first repeating element is at position: " + firstRepeatedWithHashSet(arr));
        System.out.println("HashMap Approach: The first repeating element is at position: " + firstRepeatedWithHashMap(arr));

        int[] arr2 = {1, 2, 3, 4}; // No repeating elements
        System.out.println("\nTest with no repeating elements:");
        System.out.println("Brute Force: " + firstRepeated(arr2)); // Expected: -1
        System.out.println("HashSet Approach: " + firstRepeatedWithHashSet(arr2)); // Expected: -1
        System.out.println("HashMap Approach: " + firstRepeatedWithHashMap(arr2)); // Expected: -1
    }
}
