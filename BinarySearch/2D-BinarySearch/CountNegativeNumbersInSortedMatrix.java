public class CountNegativeNumbersInSortedMatrix {
    /**
     * Counts negative numbers using a Two Pointer approach (staircase traversal).
     * <p>
     * Time Complexity: O(m + n), where m is the number of rows and n is the number of columns.
     * We start from the top-right corner and move either left or down, visiting each row and column at most once.
     * <p>
     * Space Complexity: O(1), as we use constant extra space.
     */
    public int countNegatives(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int count = 0;
        int r = 0;
        int c = col - 1;

        while (r < row && c >= 0) {
            if (grid[r][c] < 0) {
                // If current number is negative, then all numbers below it in this column are also negative
                // (since columns are sorted in non-increasing order).
                count += (row - r);
                c--; // Move left to check the next column
            } else {
                // If current number is positive, then all numbers to the left in this row are also positive
                // (since rows are sorted in non-increasing order).
                r++; // Move down to the next row
            }
        }
        return count;
    }

    /**
     * Counts negative numbers using Binary Search on each row.
     * <p>
     * Time Complexity: O(m * log n), where m is the number of rows and n is the number of columns.
     * We iterate through each row and perform a binary search.
     * <p>
     * Space Complexity: O(1), as we use constant extra space.
     */
    public int countNegativesBinarySearch(int[][] grid) {
        int totalNegatives = 0;
        for (int[] row : grid) {
            totalNegatives += countNegativesInRow(row);
        }
        return totalNegatives;
    }
    private int countNegativesInRow(int[] row) {
        int left = 0;
        int right = row.length - 1;
        int firstNegativeIndex = row.length; // Default to length if no negative is found

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (row[mid] < 0) {
                firstNegativeIndex = mid; // Update firstNegativeIndex
                right = mid - 1; // Search in the left half
            } else {
                left = mid + 1; // Search in the right half
            }
        }
        return row.length - firstNegativeIndex; // Number of negative numbers
    }

    public static void main(String[] args) {
        CountNegativeNumbersInSortedMatrix solver = new CountNegativeNumbersInSortedMatrix();

        // Example grid sorted in non-increasing order both row-wise and column-wise
        int[][] grid = {
            {4, 3, 2, -1},
            {3, 2, 1, -1},
            {1, 1, -1, -2},
            {-1, -1, -2, -3}
        };

        // Test Two Pointer Approach
        int count1 = solver.countNegatives(grid);
        System.out.println("Count using Two Pointer Approach: " + count1); // Expected: 8

        // Test Binary Search Approach
        int count2 = solver.countNegativesBinarySearch(grid);
        System.out.println("Count using Binary Search Approach: " + count2); // Expected: 8
    }
}