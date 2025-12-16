import java.util.ArrayList;
import java.util.List;

public class SpiralTraversal {

    // This method returns the spiral traversal as a List of Integers, which is a common and reusable approach.
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }

        int n = matrix.length;
        int m = matrix[0].length;
        int top = 0, bottom = n - 1;
        int left = 0, right = m - 1; // Corrected: use m (columns) for right boundary

        while (top <= bottom && left <= right) {
            // 1. Traverse from left to right (top row)
            for (int i = left; i <= right; i++) { // Corrected: use <= to include the last element
                result.add(matrix[top][i]);
            }
            top++;

            // 2. Traverse from top to bottom (right column)
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;

            // 3. Traverse from right to left (bottom row), if there's a row left
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            }

            // 4. Traverse from bottom to top (left column), if there's a column left
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };

        System.out.println("Original Matrix:");
        for (int[] row : matrix) {
            System.out.println(java.util.Arrays.toString(row));
        }

        List<Integer> spiralResult = spiralOrder(matrix);
        System.out.println("\nSpiral Traversal:");
        System.out.println(spiralResult);
    }
}
