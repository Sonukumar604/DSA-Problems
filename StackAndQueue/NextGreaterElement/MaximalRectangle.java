import java.util.Stack;

public class MaximalRectangle{

    /**
     * Finds the area of the largest rectangle containing only '1's in a binary matrix.
     * This problem is solved by converting it into a series of "Largest Rectangle in Histogram" problems.
     * For each row, we build a histogram where the height of each bar is the number of
     * consecutive '1's above that position.
     *
     * Time Complexity: O(rows * cols)
     * Space Complexity: O(cols) for the histogram heights array.
     *
     * @param matrix A 2D character array where each element is '0' or '1'.
     * @return The area of the maximal rectangle of '1's.
     */
    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int cols = matrix[0].length;
        int[] heights = new int[cols];
        int maxArea = 0;

        // Iterate through each row of the matrix
        for (char[] row : matrix) {
            // Update the heights array for the current row to form a histogram
            for (int j = 0; j < cols; j++) {
                if (row[j] == '1') {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }
            // Calculate the largest rectangle for the histogram of the current row
            int area = largestRectangleInHistogram(heights);
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

    /**
     * Calculates the largest rectangle area in a histogram using an efficient single-pass
     * monotonic stack approach. This is a helper method for maximalRectangle.
     * Time Complexity: O(n) where n is the number of bars.
     * Space Complexity: O(n) for the stack.
     *
     * @param heights An array of integers representing the heights of histogram bars.
     * @return The area of the largest rectangle in the histogram.
     */
    private static int largestRectangleInHistogram(int[] heights) {
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        int n = heights.length;
        // We also conceptually add a bar of height 0 at the end (i == n)
        // to ensure all bars in the stack are processed.
        for (int i = 0; i <= n; i++) {
            // Get the height of the current bar. If we are past the end, use 0.
            int currentHeight = (i == n) ? 0 : heights[i];
            // While the stack is not empty AND the bar on top of the stack is taller
            // than the current bar, we can calculate its area.
            while (!stack.isEmpty() && heights[stack.peek()] > currentHeight) {
                // The popped bar is the height of the rectangle.
                int height = heights[stack.pop()];
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

    public static void main(String[] args) {
        char[][] matrix1 = {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
        };
        System.out.println("Maximal rectangle area for matrix 1: " + maximalRectangle(matrix1)); // Expected: 6

        char[][] matrix2 = {{'0'}};
        System.out.println("Maximal rectangle area for matrix 2: " + maximalRectangle(matrix2)); // Expected: 0

        char[][] matrix3 = {{'1'}};
        System.out.println("Maximal rectangle area for matrix 3: " + maximalRectangle(matrix3)); // Expected: 1
    }
}