import java.util.Stack;
import java.util.Arrays;

public class NextSmallerElement {
    /**
     * Finds the next smaller element for each element in an array using a monotonic stack.
     * The next smaller element of an element x is the first smaller element to its right.
     * Time Complexity: O(n)
     * Space Complexity: O(n) for the stack.
     *
     * @param arr The input array of integers.
     * @return An array where each element at index i contains the next smaller element for arr[i],
     *         or -1 if no such element exists.
     */
    public static int[] findNextSmallerElements(int[] arr){
        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Iterate from right to left to find the "next" smaller element.
        for(int i = n - 1; i >= 0; i--){
            // While stack is not empty and its top is greater than or equal to the current element,
            // pop it because it can't be the next smaller element.
            while(!stack.isEmpty() && stack.peek() >= arr[i]){
                stack.pop();
            }
            // If the stack is empty, no smaller element exists to the right.
            // Otherwise, the top of the stack is the next smaller element.
            if(stack.isEmpty()){
                result[i] = -1;
            }else{
                result[i] = stack.peek();
            }
            // Push the current element onto the stack for elements to its left.
            stack.push(arr[i]);
        }
        return result;
    }
    public static void main(String[] args) {
        int[] arr = {4, 5, 2, 10, 8};
        int[] nse = findNextSmallerElements(arr);
        System.out.println("Next Smaller Element: " + Arrays.toString(nse)); // Expected: [2, 2, -1, 8, -1]
    }
}
