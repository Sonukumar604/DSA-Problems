public class SudokuSolver {

    // Solution 1: Standard backtracking with boolean return
    public boolean solveSudokuWithReturn(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') {
                    for (char num = '1'; num <= '9'; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solveSudokuWithReturn(board)) {
                                return true;
                            }
                            board[row][col] = '.'; // backtrack
                        }
                    }
                    return false; // no valid number found
                }
            }
        }
        return true; // solved
    }

    // Solution 2: Backtracking without boolean return, uses a field
    private boolean solved = false;

    public void solveSudokuNoReturn(char[][] board) {
        solved = false;
        solve(board, 0, 0);
    }

    private void solve(char[][] board, int row, int col) {
        if (row == 9) {
            solved = true;
            return;
        }
        if (board[row][col] != '.') {
            next(board, row, col);
        } else {
            for (char num = '1'; num <= '9'; num++) {
                if (isValid(board, row, col, num)) {
                    board[row][col] = num;
                    next(board, row, col);
                    if (solved) return;
                    board[row][col] = '.';
                }
            }
        }
    }
    private void next(char[][] board, int row, int col) {
        if (col == 8) {
            solve(board, row + 1, 0);
        } else {
            solve(board, row, col + 1);
        }
    }

    // Common validity check for both solutions
    private boolean isValid(char[][] board, int row, int col, char num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) return false;
            if (board[i][col] == num) return false;
            int subRow = 3 * (row / 3) + i / 3;
            int subCol = 3 * (col / 3) + i % 3;
            if (board[subRow][subCol] == num) return false;
        }
        return true;
    }

    // Example usage
    public static void main(String[] args) {
        char[][] board1 = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        char[][] board2 = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };

        SudokuSolver solver = new SudokuSolver();

        // Solution 1: With boolean return
        System.out.println("Solution 1 (with boolean return):");
        if (solver.solveSudokuWithReturn(board1)) {
            printBoard(board1);
        } else {
            System.out.println("No solution exists.");
        }

        // Solution 2: Without boolean return
        System.out.println("\nSolution 2 (without boolean return):");
        solver.solveSudokuNoReturn(board2);
        printBoard(board2);
    }

    private static void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}