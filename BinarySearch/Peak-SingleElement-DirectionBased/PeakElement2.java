public class PeakElement2 {
    private int findMaxRow(int[][] mat, int col) {
        int maxRow = 0;
        for (int i = 0; i < mat.length; i++) {
            if (mat[i][col] > mat[maxRow][col]) {
                maxRow = i;
            }
        }
        return maxRow;
    }

    public int[] findPeakGrid(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        int low = 0, high = m - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            int maxRow = findMaxRow(mat, mid);

            int left  = (mid - 1 >= 0) ? mat[maxRow][mid - 1] : -1;
            int right = (mid + 1 < m)  ? mat[maxRow][mid + 1] : -1;

            if (mat[maxRow][mid] > left && mat[maxRow][mid] > right) {
                return new int[]{maxRow, mid}; // Peak found
            } 
            else if (left > mat[maxRow][mid]) {
                high = mid - 1;   // move left
            } 
            else {
                low = mid + 1;    // move right
            }
        }

        return new int[]{-1, -1}; // never reached
    }
    public static void main(String[] args) {
        PeakElement2 peakElement2 = new PeakElement2();
        int[][] mat = {
            {1, 4, 3},
            {6, 5, 2},
            {7, 8, 9}
        };
        int[] result = peakElement2.findPeakGrid(mat);
        System.out.println("A peak element is at position: [" + result[0] + ", " + result[1] + "]"); // Output will be the position of a peak element
    }
}

