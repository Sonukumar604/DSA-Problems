public class MatrixPaths {
    public static int countPaths(int m, int n) {
        return countPathsUtil(0, 0, m, n);
    }
    private static int countPathsUtil(int i, int j, int m, int n) {
        // Base case: reached bottom-right corner
        if (i == m - 1 && j == n - 1) {
            return 1;
        }
        // If out of bounds
        if (i >= m || j >= n) {
            return 0;
        }
        // Move down and right
        return countPathsUtil(i + 1, j, m, n) + countPathsUtil(i, j + 1, m, n);
    }
    public static void main(String[] args) {
        int m = 3, n = 3;
        System.out.println("Number of unique paths in a " + m + "x" + n + " matrix: " + countPaths(m, n));
    }
}
