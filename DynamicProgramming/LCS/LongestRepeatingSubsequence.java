public class LongestRepeatingSubsequence {
    public static int longestRepeatingSubsequence(String x, int n){
        // We need to find LCS of x and x itself, but with the constraint
        // that characters at the same index in x cannot be considered a match.
        // So, if x[i-1] == y[j-1] (where y is also x), then i must not be equal to j.
        int[][] t = new int[n + 1][n + 1];
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= n; j++){
                if(i == 0 || j == 0){
                    t[i][j] = 0;
                }
                // If characters match AND their indices are different
                else if(x.charAt(i - 1) == x.charAt(j - 1) && i != j){
                    t[i][j] = 1 + t[i - 1][j - 1];
                }
                // If characters don't match or indices are the same
                else{
                    t[i][j] = Math.max(t[i - 1][j], t[i][j - 1]);
                }
            }
        }
        return t[n][n];
    }

    public static void main(String[] args) {
        String x = "AABEBCDD"; // Example: "AAB" -> "AB" (A at index 0, B at index 2)
        int n = x.length();
        System.out.println("Length of Longest Repeating Subsequence: " + longestRepeatingSubsequence(x, n));
         // Expected: 3 (ABD)
        
    }
}
