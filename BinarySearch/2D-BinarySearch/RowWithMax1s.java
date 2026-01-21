public class RowWithMax1s {
    private int countOnes(int[] row) {
        int low = 0;
        int high = row.length - 1;
        int firstOneIndex = row.length; // Default to length if no 1 is found

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (row[mid] == 1) {
                firstOneIndex = mid; // Update firstOneIndex
                high = mid - 1; // Search in the left half
            } else {
                low = mid + 1; // Search in the right half
            }
        }
        return row.length - firstOneIndex; // Number of 1s
    }
    public int rowWithMax1s(int[][] mat) {
        int maxRowIndex = -1;
        int maxOnesCount = 0;

        for (int i = 0; i < mat.length; i++) {
            int onesCount = countOnes(mat[i]);
            if (onesCount > maxOnesCount) {
                maxOnesCount = onesCount;
                maxRowIndex = i;
            }
        }
        return maxRowIndex;
    }
    public static void main(String[] args) {
        int[] [] mat = {
            {0, 0, 0, 1},
            {0, 1, 1, 1},
            {1, 1, 1, 1},
            {0, 0, 0, 0}
        };
        RowWithMax1s rowWithMax1s = new RowWithMax1s();
        int result = rowWithMax1s.rowWithMax1s(mat);
        System.out.println("Row with maximum 1s is at index: " + result); // Output: Row with maximum 1s is at index: 2
        
    }
}
