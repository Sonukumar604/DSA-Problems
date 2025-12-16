import java.util.Arrays;

public class SetMatrixZero {
    //Brute Force
    //Time Complexity : O((N*M)*(N+M)) + O(N*M), Space Complexity: O(1)
    public static void setMatrixZero(int[][] matrix){
        if(matrix == null || matrix.length == 0){
            return;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(matrix[i][j] == 0){
                    for(int k = 0; k < m; k++){
                        if(matrix[i][k] != 0){
                            matrix[i][k] = -1;
                        }
                    }
                    for(int k = 0; k < n; k++){
                        if(matrix[k][j] != 0){
                            matrix[k][j] = -1;
                        }
                    }
                }
            }
        }
        // Second pass: change all -1s to 0s
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(matrix[i][j] == -1){
                    matrix[i][j] = 0;
                }
            }
        }
    }
    //Better Apporach
    //Time Complexity : O(2 * m * n), Space Complexity : O(m + n)
    public static void setMatrixZeroBetter(int[][] matrix){
        if(matrix == null || matrix.length == 0){
            return;
        }
        int n = matrix.length;
        int m = matrix[0].length;

        boolean[] zeroRows = new boolean[n];
        boolean[] zeroCols = new boolean[m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(matrix[i][j] == 0){
                    zeroRows[i] = true;
                    zeroCols[j] = true;
                }
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(zeroRows[i] || zeroCols[j]){
                    matrix[i][j] = 0;
                }
            }
        }
    }
    //Optimal Approach
    //Time Complexity : O(m * n)
    //Space Complexity : O(1)
    public static void setMatrixZeroOptimal(int[][] matrix){
        if(matrix == null || matrix.length == 0){
            return;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        // Use the first cell of the first column to track if the first column needs to be zeroed.
        // Use a separate variable for the first row's state, as matrix[0][0] is used for both.
        boolean col0 = false;

        // 1. First pass: Determine which rows and columns need to be zeroed.
        // Use the first row and first column of the matrix as markers.
        for(int i = 0; i < n; i++){
            if(matrix[i][0] == 0) col0 = true;
            for(int j = 1; j < m; j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0; // Mark row
                    matrix[0][j] = 0; // Mark col
                }
            }
        }

        // 2. Second pass: Set elements to zero based on markers in the first row and column.
        // Iterate from the back to avoid overwriting the markers prematurely.
        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }

        // 3. Third pass: Zero out the first row if needed.
        if(matrix[0][0] == 0){
            for(int j = 0; j < m; j++){
                matrix[0][j] = 0;
            }
        }

        // 4. Fourth pass: Zero out the first column if needed.
        if(col0){
            for(int i = 0; i < n; i++){
                matrix[i][0] = 0;
            }
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] matrix1 = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        System.out.println("Original Matrix:");
        printMatrix(matrix1);
        setMatrixZero(matrix1);
        System.out.println("Brute Force Approach:");
        printMatrix(matrix1);

        int[][] matrix2 = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        System.out.println("Original Matrix:");
        printMatrix(matrix2);
        setMatrixZeroBetter(matrix2);
        System.out.println("Better Approach:");
        printMatrix(matrix2);

        int[][] matrix3 = {{1, 1, 1, 1}, {1, 0, 1, 1}, {1, 1, 0, 0}, {0, 0, 0, 1}};
        System.out.println("Original Matrix:");
        printMatrix(matrix3);
        setMatrixZeroOptimal(matrix3);
        System.out.println("Optimal Approach:");
        printMatrix(matrix3);
    }
}
