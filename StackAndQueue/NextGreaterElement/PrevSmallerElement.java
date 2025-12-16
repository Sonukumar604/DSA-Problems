import java.util.Stack;
import java.util.Arrays;

public class PrevSmallerElement {

    /**
     * Finds the previous smaller element for each element in an array using a monotonic stack.
     * The previous smaller element of an element x is the first smaller element to its left.
     *
     * @param arr The input array of integers.
     * @return An array where each element at index i contains the previous smaller element for arr[i],
     *         or -1 if no such element exists.
     */
    public static int[] findPreviousSmallerElements(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Iterate from left to right to find the "previous" smaller element.
        for (int i = 0; i < n; i++) {
            // While stack is not empty and its top is greater than or equal to the current element,
            // pop it. These elements cannot be the "previous smaller" for the current or future elements.
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }

            // If the stack is empty, no smaller element exists to the left.
            // Otherwise, the top of the stack is the previous smaller element.
            result[i] = stack.isEmpty() ? -1 : stack.peek();

            // Push the current element onto the stack for elements to its right.
            stack.push(arr[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 2, 10, 8};
        int[] pse = findPreviousSmallerElements(arr);
        System.out.println("Previous Smaller Element: " + Arrays.toString(pse)); // Expected: [-1, 4, -1, 2, 2]
    }
}