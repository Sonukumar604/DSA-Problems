/**
 * This class determines if one string is a subsequence of another.
 * The logic is based on the Longest Common Subsequence (LCS).
 * If string 'x' is a subsequence of string 'y', then the LCS of 'x' and 'y'
 * must be 'x' itself. Therefore, the length of the LCS will be equal to the length of 'x'.
 */
public class SequencePatternMatching {
    /**
     * Computes the length of the Longest Common Subsequence (LCS) of two strings.
     *
     * @param x The first string.
     * @param y The second string.
     * @param m The length of string x.
     * @param n The length of string y.
     * @return The length of the LCS.
     */
    public static int lcs(String x, String y, int m, int n){
        int[][] t = new int[m + 1][n + 1];
        // The loops must go up to and including m and n to fill the entire DP table.
        for(int i = 0 ; i <= m; i++){
            for(int j = 0; j <= n; j++){
                if(i == 0 || j == 0){
                    t[i][j] = 0;
                }
                else if (x.charAt(i - 1) == y.charAt(j - 1)){
                    t[i][j] = 1 + t[i - 1][j - 1];
                }
                else{
                    t[i][j] = Math.max(t[i - 1][j], t[i][j - 1]);
                }
            }
        }
        // The result is in the bottom-right cell of the table.
        return t[m][n];
    }
    /**
     * Checks if string 'x' is a subsequence of string 'y'.
     * @return true if 'x' is a subsequence of 'y', false otherwise.
     */
    public static boolean isSubsequence(String x, String y){
        int m = x.length();
        int n = y.length();
        int lcsLen = lcs(x, y, m, n);
        // If the length of the LCS is equal to the length of the first string,
        // it means the entire first string is present as a subsequence in the second.
        return lcsLen == m;
    }
    public static void main(String[] args) {
        String x = "abc";
        String y = "axbycz";
        if(isSubsequence(x, y)){
            System.out.println("\"" + x + "\" is a subsequence of: \"" + y + "\"");
        }else{
            System.out.println("\"" + x + "\" is not a subsequence of: \"" + y + "\"");
        }
    }
}
