public class WordSearch {
    // Returns true if the word exists in the board using backtracking
    public static boolean exist(char[][] board, String word){
        if(board == null || board.length == 0 || word == null || word.length == 0) return false;
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(board[i][j] == word.charAt(0) && backtrack(board, word, 0, i, j, visited)) 
                    return true;
            }
        }
        return false;
    }

    // Helper function for backtracking
    public static boolean backtrack(char[][] board, String word, int index, int row, int col, boolean[][] visited){
        if(index == word.length()) return true;
        if(row < 0 || row >= board.length || col < 0 || col >= board[0].length) return false;
        if(visited[row][col] || board[row][col] != word.charAt(index)) return false;

        visited[row][col] = true;
        // Explore all 4 directions: Down, Up, Right, Left
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        for(int i = 0; i < 4; i++){
            int newRow = row + dr[i];
            int newCol = col + dc[i];
            if(backtrack(board, word, index + 1, newRow, newCol, visited)) return true;
        }
        visited[row][col] = false; // Backtrack
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {
            {'A','B','C','E'},
            {'S','F','C','S'},
            {'A','D','E','E'}
        };
        String word = "SEE";
        System.out.println("Does the word \"" + word + "\" exist in the board? " + exist(board, word));
        String word2 = "ABCB";
        System.out.println("Does the word \"" + word2 + "\" exist in the board? " + exist(board, word2));
    }
}