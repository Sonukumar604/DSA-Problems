public class Search2Dmatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int n = matrix.length;
        int m = matrix[0].length;

        int low = 0, high = n * m - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midValue = matrix[mid / m][mid % m];

            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return false;
    }
    public static void main(String[] args) {
        Search2Dmatrix search2Dmatrix = new Search2Dmatrix();
        int[][] matrix = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };
        int target = 3;
        boolean result = search2Dmatrix.searchMatrix(matrix, target);
        System.out.println("Target " + target + " found in matrix: " + result); // Output: true

        int target2 = 100;
        boolean result2 = search2Dmatrix.searchMatrix(matrix, target2);
        System.out.println("Target " + target2 + " found in matrix: " + result2); // Output: false
    }
}
