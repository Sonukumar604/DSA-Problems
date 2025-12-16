import java.util.Scanner;
import java.util.Arrays;

public class RotateMatrix{
    //Brute-Force Approach
    // Time Complexity: O(N*N), Space Complexity: O(N*N)
    public static void rotateMatrix(int[][] matrix){
        int n = matrix.length;
        int[][] ans = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                // The element at [i][j] goes to [j][n-1-i]
                ans[j][n - 1 - i] = matrix[i][j];
            }
        }
        // Copy the rotated matrix back to the original matrix
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = ans[i][j];
            }
        }
    }
    //Optimal-Approach (in-place)
    // Time Complexity: O(N*N), Space Complexity: O(1)
    public static void rotateMatrixOptimal(int[][] matrix){
        int n = matrix.length;
        // 1. Transpose the matrix
        for(int i = 0; i < n; i++){
            // Start j from i to avoid swapping elements twice
            for(int j = i; j < n; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // 2. Reverse each row
        for(int i = 0; i < n; i++){
            int left = 0, right = n - 1;
            while(left < right){
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }
    public static void printMatrix(int[][] matrix){
        for(int[] row : matrix){
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int[][] matrix1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println("Original Matrix:");
        printMatrix(matrix1);

        // Using a copy for the brute-force method to show both results
        int[][] matrix1_copy = new int[matrix1.length][];
        for(int i = 0; i < matrix1.length; i++) matrix1_copy[i] = matrix1[i].clone();

        rotateMatrix(matrix1_copy);
        System.out.println("Rotated Matrix (Brute-Force):");
        printMatrix(matrix1_copy);

        int[][] matrix2 = {
            {5, 1, 9, 11},
            {2, 4, 8, 10},
            {13, 3, 6, 7},
            {15, 14, 12, 16}
        };
        System.out.println("Original Matrix:");
        printMatrix(matrix2);

        rotateMatrixOptimal(matrix2);
        System.out.println("Rotated Matrix (Optimal):");
        printMatrix(matrix2);
    }
}