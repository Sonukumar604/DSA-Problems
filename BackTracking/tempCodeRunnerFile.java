if(board == null || board.length == 0 || word == null || word.length == 0) return false;
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        // Iterate through each cell of the board to use as a starting point.
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                // Optimization: Only start backtracking if the first character matches.
                if(board[i][j] == word.charAt(0) && backtrack(board, word, 0, i, j, visited)) 
                    return true;
            }
        }
        return false;
    }
    public static boolean backtrack(char[][] board, String word, int index, int row, int col, boolean[][] visited){
        // Base case: If we have found all characters in the word, we are done.
        if(index == word.length()) return true;
        // Boundary checks: Ensure we are within the board's limits.
        if(row < 0 || row >= board.length || col < 0 || col >= board[0].length) return false;
        // Condition checks: The cell must not be visited, and the character must match.
        if(visited[row][col] || board[row][col] != word.charAt(index)) return false;

        // Mark the current cell as visited.
        visited[row][col] = true;

        // Explore all 4 directions (Down, Up, Right, Left).
        for(int i = 0; i < 4; i++){
            int newRow = row + dr[i];
            int newCol = col + dc[i];
            // If the recursive call finds the rest of the word, propagate true up.
            if(backtrack(board, word, index + 1, newRow, newCol, visited)) return true;
        }

        // Backtrack: Un-mark the cell so it can be used in other paths.
        visited[row][col] = false;
        return false;
    }
    // Direction vectors for Down, Up, Right, Left
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};