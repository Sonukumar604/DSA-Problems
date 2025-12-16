import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {
    // Variation 1: Given row and col, tell the element at that place.
    // Uses the nCr formula.
    public static int nCr(int n, int r){
        long res = 1;
        // Calculate nCr = n! / (r! * (n-r)!)
        for(int i = 0; i < r; i++){
            res = res * (n - i);
            res = res / (i + 1);
        }
        return (int)res;
    }

    // Helper for Variation 1, using 1-based indexing for row and column.
    public static int getValue(int r, int c){
        return nCr(r - 1, c - 1);
    }

    // Variation 2: Print the nth row of Pascal's triangle.
    // This is an optimal approach that calculates each element from the previous one.
    public static void printRow(int n){
        long value = 1; // Corrected typo from 'val' to 'value' for consistency
        System.out.print(value + " ");
        for(int i = 1; i < n; i++){
            // Formula: nCr = nC(r-1) * (n-r+1)/r
            // Here, n is (n-1) and r is i.
            value = value * (n - i) / i;
            System.out.print(value + " ");
        }
        System.out.println();
    }

    // Variation 3: Print the entire triangle up to n rows.
    public static void printPascal(int n){
        // Outer loop for each row
        for(int row = 1; row <= n; row++){
            long value = 1;
            // Inner loop for each column in the current row
            for(int col = 1; col <= row; col++){
                System.out.print(value + " ");
                // Calculate the next value in the row
                value = value * (row - col) / col;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int n = 6; // Number of rows for the triangle

        System.out.println("--- Variation 1: Get a specific element ---");
        int r = 5, c = 3; // Get element at 5th row, 3rd column (1-based)
        System.out.println("Element at row " + r + ", col " + c + " is: " + getValue(r, c)); // Expected: 6
        System.out.println();

        System.out.println("--- Variation 2: Print a specific row ---");
        System.out.print("The " + n + "th row is: ");
        printRow(n); // Expected: 1 5 10 10 5 1
        System.out.println();

        System.out.println("--- Variation 3: Print the full triangle ---");
        printPascal(n);
    }
}
