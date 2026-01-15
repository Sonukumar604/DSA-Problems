public class TotalNQueens {
    public static int totalNQueens(int n){
        boolean[][] board = new boolean[n][n];
        return solveNQueens(board, 0);
    }
    public static int solveNQueens(boolean[][] board, int row){
        if(row == board.length) return 1;

        int count = 0;
        for(int col = 0; col < board.length; col++){
            if(isSafe(board, row, col)){
                board[row][col] = true;
                count += solveNQueens(board, row + 1);
                board[row][col] = false;
            }
        }
        return count;
    }
    public static boolean isSafe(boolean[][] board, int row, int col){
        for(int i = 0; i < row; i++){
            if(board[i][col]) return false;
            if(col - (row - i) >= 0 && board[i][col - (row - i)]) return false;
            if(col + (row - i) < board.length && board[i][col + (row - i)]) return false;

        }
        return true;

    }
    public static void main(String[] args) {
        int n = 4;
        System.out.println("Total solutions for " + n + "-Queens: " + totalNQueens(n)); // Expected: 2
        n = 8;
        System.out.println("Total solutions for " + n + "-Queens: " + totalNQueens(n)); // Expected: 92
    }
}
