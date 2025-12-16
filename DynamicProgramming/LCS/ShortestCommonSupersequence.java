/**
 * This class finds the length and prints the Shortest Common Supersequence (SCS) of two strings.
 * The length of SCS = (length of str1) + (length of str2) - (length of LCS).
 * The SCS string is constructed by backtracking through the LCS DP table.
 */
public class ShortestCommonSuperSequence {
    /**
     * Computes the DP table for the Longest Common Subsequence (LCS).
     * The value at t[m][n] will be the length of the LCS.
     *
     * @param x The first string.
     * @param y The second string.
     * @param m The length of string x.
     * @param n The length of string y.
     * @return A 2D array representing the LCS DP table.
     */
    public static int[][] lcsTable(String x, String y, int m, int n){
        int[][] t = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i++){
            for(int j = 0; j <= n; j++){
                if(i == 0 || j == 0){
                    t[i][j] = 0;
                }else if(x.charAt(i - 1) == y.charAt(j - 1)){
                    t[i][j] = 1 + t[i - 1][j - 1];
                }else{
                    t[i][j] = Math.max(t[i - 1][j], t[i][j - 1]);
                }
            }
        }
        return t;
    }

    /**
     * Calculates the length of the Shortest Common Supersequence (SCS).
     * The length is calculated using the formula: len(x) + len(y) - len(LCS(x, y)).
     *
     * @param x The first string.
     * @param y The second string.
     * @return The length of the SCS.
     */
    public static int getSCSLength(String x, String y) {
        int m = x.length();
        int n = y.length();
        int[][] lcsDPTable = lcsTable(x, y, m, n);
        int lcsLength = lcsDPTable[m][n];
        return m + n - lcsLength;
    }

    /**
     * Constructs the SCS string by backtracking through a pre-computed LCS table.
     * @param x The first string.
     * @param y The second string.
     * @param m The length of string x.
     * @param n The length of string y.
     * @param t The pre-computed LCS DP table.
     */
    public static String getSCSString(String x, String y, int m, int n, int[][] t){
        int i = m, j = n;
        StringBuilder sb = new StringBuilder();
        while(i > 0 && j > 0){
            if(x.charAt(i - 1) == y.charAt(j - 1)){
                sb.append(x.charAt(i - 1));
                i--;
                j--;
            } else if (t[i - 1][j] > t[i][j - 1]) {
                // If we move up, it means x.charAt(i-1) is not in the LCS.
                // So, we must add it to the supersequence.
                sb.append(x.charAt(i - 1));
                i--;
            } else {
                // If we move left, it means y.charAt(j-1) is not in the LCS.
                // So, we must add it to the supersequence.
                sb.append(y.charAt(j - 1));
                j--;
            }
        }
        // After the main loop, one of the strings might still have characters left.
        // Append the remaining characters from x, if any.
        while (i > 0) {
            sb.append(x.charAt(i - 1));
            i--;
        }
        // Append the remaining characters from y, if any.
        while (j > 0) {
            sb.append(y.charAt(j - 1));
            j--;
        }
        // The string was built backwards, so we need to reverse it.
        return sb.reverse().toString();
    }
    public static void main(String[] args) {
        String x = "AGGTAB";
        String y = "GXTXAYB";
        int m = x.length();
        int n = y.length();

        // --- Calculate and Print Length ---
        int scsLength = getSCSLength(x, y);
        System.out.println("Length of shortest common supersequence: " + scsLength);

        // --- Calculate and Print the SCS String ---
        // We can reuse the lcsTable method to avoid re-computing the DP table.
        int[][] lcsDPTable = lcsTable(x, y, m, n);
        String scs = getSCSString(x, y, m, n, lcsDPTable);
        System.out.println("Shortest common supersequence: " + scs);
    }
}
