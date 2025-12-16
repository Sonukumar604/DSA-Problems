/**
 * This class calculates the minimum number of insertions and deletions
 * required to convert one string into another. The solution is based on finding
 * the Longest Common Subsequence (LCS).
 */
public class MinInsertionDeletion {
    /**
     * Computes the length of the Longest Common Subsequence (LCS) of two strings
     * using a bottom-up dynamic programming approach.
     *
     * @param x The first string.
     * @param y The second string.
     * @param m The length of string x.
     * @param n The length of string y.
     * @return The length of the LCS.
     */
    public static int lcs(String x, String y, int m, int n){
        int[][] t = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i++){
            for(int j = 0; j <= n; j++){
                if(i == 0 || j == 0){
                    t[i][j] = 0;
                } else if(x.charAt(i - 1) == y.charAt(j - 1)){
                    t[i][j] = 1 + t[i - 1][j - 1];
                } else {
                    t[i][j] = Math.max(t[i-1][j], t[i][j - 1]);
                }
            }
        }
        // The result is in the bottom-right cell of the DP table.
        return t[m][n];
    }
    /**
     * Calculates and prints the minimum insertions and deletions required
     * to convert string 'a' to string 'b'.
     */
    public static void minInsertDelete(String a, String b){
        int m = a.length();
        int n = b.length(); // Corrected typo from 'lenght' to 'length'
        int lcsLen = lcs(a, b, m, n); // Corrected to use 'a' and 'b'

        int minDeletions = m - lcsLen;
        int minInsertions = n - lcsLen;
        System.out.println("Minimum number of deletions: " + minDeletions);
        System.out.println("Minimum number of insertions: " + minInsertions);
    }
    public static void main(String[] args) {
        String a = "heap";
        String b = "pea";
        minInsertDelete(a, b);
    }
}
