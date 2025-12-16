import java.util.Stack;

// Corrected class name to match the file name.
public class LargesyRectangleInHistogrm2 {
    /**
     * Calculates the largest rectangle area in a histogram using an efficient single-pass
     * monotonic stack approach.
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int largestRectangle(int[] arr){
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        int n = arr.length;

        // Iterate through each bar of the histogram.
        // We also conceptually add a bar of height 0 at the end (i == n)
        // to ensure all bars in the stack are processed.
        for(int i = 0; i <= n; i++){
            // Get the height of the current bar. If we are past the end, use 0.
            int currentHeight = (i == n) ? 0 : arr[i];

            // While the stack is not empty AND the bar on top of the stack is taller
            // than the current bar, we can calculate its area.
            while(!stack.isEmpty() && arr[stack.peek()] > currentHeight){
                // The popped bar is the height of the rectangle.
                int height = arr[stack.pop()];

                // The right boundary is the current index 'i'.
                // The left boundary is the index of the new top of the stack.
                // If the stack is empty, the rectangle extends to the beginning.
                int width = i - (stack.isEmpty() ? -1 : stack.peek()) - 1;

                maxArea = Math.max(maxArea, height * width);
            }
            // Push the current index onto the stack.
            stack.push(i);
        }
        return maxArea;
    }
    public static void main(String[] args){
        int[] arr = {2, 1, 5, 6, 2, 3};
        System.out.println("Largest rectangle area: " + largestRectangle(arr)); // Expected: 10
    }
}