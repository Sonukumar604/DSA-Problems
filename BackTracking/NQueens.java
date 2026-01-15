import java.util.*;

public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) Arrays.fill(row, '.');
        backtrackRow(0, board, solutions, n);
        return solutions;
    }

    // place one queen per row (row-wise recursion)
    private void backtrackRow(int row, char[][] board, List<List<String>> solutions, int n) {
        if (row == n) {
            solutions.add(construct(board));
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col, n)) {
                board[row][col] = 'Q';
                backtrackRow(row + 1, board, solutions, n);
                board[row][col] = '.';
            }
        }
    }

    private boolean isSafe(char[][] board, int row, int col, int n) {
        // check column above
        for (int i = 0; i < row; i++)
            if (board[i][col] == 'Q') return false;
        // check upper-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 'Q') return false;
        // check upper-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++)
            if (board[i][j] == 'Q') return false;
        return true;
    }

    private List<String> construct(char[][] board) {
        List<String> res = new ArrayList<>();
        for (char[] row : board) res.add(new String(row));
        return res;
    }
    

    public static void main(String[] args) {
        NQueens nq = new NQueens();
        int n = 4;
        List<List<String>> solutions = nq.solveNQueens(n);
        for (List<String> sol : solutions) {
            for (String r : sol) System.out.println(r);
            System.out.println();
        }
    }
}