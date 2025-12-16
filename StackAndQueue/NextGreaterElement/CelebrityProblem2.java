public class CelebrityProblem2 {
    public static int findCelebrity(int[][] M){
        int n = M.length;
        int top = 0;
        int down = n-1;
        while(top < down){
            if(M[top][down] == 1){
                // If top knows down, top cannot be a celebrity.
                top++;
            }else{
                // If top does not know down, down cannot be a celebrity.
                down--;
            }
        }
        // At the end of the loop, 'top' is the only candidate for celebrity
        for(int i = 0; i < n; i++){
            if(i != top){
                if(M[top][i] == 1 || M[i][top] == 0){
                    return -1; // 'top' knows someone or someone doesn't know 'top', so 'top' is not a celebrity
            }
            }
        }
        return top; // 'top' is the celebrity
    }
    public static void main(String[] args) {
        // Example usage:
        // In this MODIFIED example, person 1 (index 1) is now a celebrity.
        // They know no one (row 1 is all 0s) and everyone else knows them (column 1 is all 1s).
        int[][] M = {
            {0, 1, 0, 0},
            {0, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0}
        };
        int celebrity = findCelebrity(M);
        if (celebrity != -1) {
            System.out.println("Celebrity found at index: " + celebrity);
        } else {
            System.out.println("No celebrity found.");
        }
    }
}
