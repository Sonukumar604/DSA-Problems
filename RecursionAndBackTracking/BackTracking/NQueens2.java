public class NQueens2 {
    public int totalNQueens(int n) {
        boolean[][] board = new boolean[n][n];
        return backtrack(0, board, n);
    }
    private static int backtrack(int row, boolean[][] board, int n){
        if(row == n){
            return 1;
        }
        int count = 0;
        for(int col = 0; col < n; col++){
            if(isSafe(board, row, col, n)){
                board[row][col] = true;
                count += backtrack(row + 1, board, n);
                board[row][col] = false;
            }
        }
        return count;
    }
    private static boolean isSafe(boolean[][] board, int row, int col, int n){
        for(int i = 0; i < row; i++){
            if(board[i][col]){
                return false;
            }
            else if(col - (row - i) >= 0 && board[i][col - (row - i)]){
                return false;// Check upper-left diagonal
            }
            else if(col + (row - i) < n && board[i][col + (row - i)]){
                return false;// Check upper-right diagonal
            }
        }
        return true;
    }
    public static void main(String[] args) {
        NQueens2 nq2 = new NQueens2();
        int n = 4;
        int totalSolutions = nq2.totalNQueens(n);
        System.out.println("Total distinct solutions for " + n + "-Queens: " + totalSolutions);
    }
}
