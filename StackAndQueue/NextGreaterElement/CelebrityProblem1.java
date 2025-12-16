public class CelebrityProblem1 {
    public static int findCelebrity(int[][] M){
        int n = M.length;
        int[] iKnow = new int[n];
        int[] knowsMe = new int[n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(M[i][j] == 1){
                    iKnow[i]++;
                    knowsMe[j]++;
                }
            }
        }
        // A celebrity is known by everyone else but knows no one
        for(int i = 0; i < n; i++){
            if(iKnow[i] == 0 && knowsMe[i] == n-1){
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[][] M = {
            {0, 1, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 1},
            {1, 0, 0, 0}
        };
        int celebrity = findCelebrity(M);
        if (celebrity != -1) {
            System.out.println("Celebrity found at index: " + celebrity);
        } else {
            System.out.println("No celebrity found.");
        }
    }
}
