public class rowWithMax1s {
    /**
     * Finds the row with the maximum number of 1s using a brute-force approach.
     * This approach has a time complexity of O(n * m).
     * @param arr The input matrix.
     * @param n Number of rows.
     * @param m Number of columns.
     * @return The index of the row with the most 1s, or -1 if no 1s are found.
     */
    public int rowWithMax1s(int arr[][], int n, int m){
        int maxOnesCount = 0;
        int rowIndex = -1;
 
        for (int i = 0; i < n; i++) {
            int currentOnesCount = 0;
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1) {
                    currentOnesCount++;
                }
            }
 
            if (currentOnesCount > maxOnesCount) {
                maxOnesCount = currentOnesCount;
                rowIndex = i;
            }
        }
        return rowIndex;
    }
 
    public static void main(String[] args) {
        rowWithMax1s obj = new rowWithMax1s();
        int arr[][] = {
            {0, 0, 0, 1}
            ,{0, 0, 1, 1}
            ,{0, 1, 1, 1}
            ,{1, 1, 1, 1}
            ,{0, 0, 0, 0}
        };
        int n = arr.length;
        int m = arr[0].length;
        int result = obj.rowWithMax1s(arr, n, m);
        System.out.println("Row with maximum 1s: " + result);
    }
}