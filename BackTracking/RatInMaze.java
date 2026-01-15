import java.util.ArrayList;
import java.util.Collections;
public class RatInMaze{
    static String dir = "DLRU";//Down, Left, Right, Up
    static int[] dr = {1,0,0,-1};//row change
    static int[] dc = {0,-1,1,0};//column change
    static boolean isValid(int r, int c, int n, int[][] maze){
        return r >= 0 && c >= 0 && r < n && c < n && maze[r][c] == 1;
    }
    static void findPath(int r, int c, int[][] maze, StringBuilder path, ArrayList<String> result){
        int n = maze.length;
        if(r == n - 1 && c == n - 1){
            result.add(path.toString());
            return;
        }
        maze[r][c] = 0;
        for(int i = 0; i < 4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            // Corrected the parameters passed to isValid
            if(isValid(nr, nc, n, maze)){
                path.append(dir.charAt(i));
                findPath(nr, nc, maze, path, result);
                path.deleteCharAt(path.length() - 1);
            }
        }
        maze[r][c] = 1;
    }
    static ArrayList<String> ratInMaze(int[][] maze){
        ArrayList<String> result = new ArrayList<>();
        int n = maze.length;
        StringBuilder path = new StringBuilder();
        if(maze[0][0] == 1 && maze[n-1][n-1] == 1){
            findPath(0, 0, maze, path, result);
        }
        Collections.sort(result);
        return result;
    }
    public static void main(String[] args){
        int[][] maze = {
            {1,0,0,0},
            {1,1,0,1},
            {0,1,0,0},
            {0,1,1,1}
        };
        // Corrected method call and variable names
        ArrayList<String> paths = ratInMaze(maze);
        for(String path : paths){
            System.out.println(path);
        }
    }
}