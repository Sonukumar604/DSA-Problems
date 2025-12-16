import java.util.Stack;


public class LargestRectangleInHistogram {

    /**
     * Finds the index of the Previous Smaller Element (PSE) for each bar.
     * The PSE for a bar at index `i` is the first bar to its left with a smaller height.
     * @param heights The array of bar heights.
     * @return An array where `result[i]` is the index of the PSE for `heights[i]`, or -1 if none exists.
     */
    public static int[] getPrevSmallerIndices(int[] heights) {
        int n = heights.length;
        int[] prevSmaller = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            // While stack is not empty and the bar at the top of the stack is taller than or equal to the current bar
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            // If the stack is empty, there's no smaller element to the left. Otherwise, the top is the PSE.
            prevSmaller[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return prevSmaller;
    }

    /**
     * Finds the index of the Next Smaller Element (NSE) for each bar.
     * The NSE for a bar at index `i` is the first bar to its right with a smaller height.
     * @param heights The array of bar heights.
     * @return An array where `result[i]` is the index of the NSE for `heights[i]`, or `n` if none exists.
     */
    public static int[] getNextSmallerIndices(int[] heights) {
        int n = heights.length; // Corrected typo: length, not lenght
        int[] nextSmaller = new int[n];
        Stack<Integer> stack = new Stack<>();
        // Corrected for-loop syntax: use semicolons, not a colon
        for (int i = n - 1; i >= 0; i--) {
            // While stack is not empty and the bar at the top of the stack is taller than or equal to the current bar
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            // If the stack is empty, there's no smaller element to the right. Otherwise, the top is the NSE.
            nextSmaller[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        return nextSmaller;
    }
    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        // Renamed helper methods to match the calls
        int[] prevSmaller = getPrevSmallerIndices(heights);
        int[] nextSmaller = getNextSmallerIndices(heights);
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int width = nextSmaller[i] - prevSmaller[i] - 1;
            int area = heights[i] * width;
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        // Corrected method call syntax
        System.out.println("Largest rectangle area for {2, 1, 5, 6, 2, 3} is: " + largestRectangleArea(heights)); // Expected: 10
    }
}