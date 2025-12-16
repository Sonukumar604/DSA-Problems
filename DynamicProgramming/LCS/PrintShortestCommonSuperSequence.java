public class PrintShortestCommonSuperSequence {

    /**
     * Calculates ONLY the length of the Shortest Common Supersequence.
     * This is more efficient if you don't need the actual string.
     *
     * @return The length of the SCS.
     */
    public static int getSCSLength(String x, String y, int m, int n) {
        int[][] t = new int[m + 1][n + 1];
        // This part is the same: build the LCS DP table to find the LCS length.
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    t[i][j] = 0;
                } else if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    t[i][j] = 1 + t[i - 1][j - 1];
                } else {
                    t[i][j] = Math.max(t[i - 1][j], t[i][j - 1]);
                }
            }
        }
        // The length of the LCS is in the bottom-right cell.
        int lcsLength = t[m][n];
        // Apply the formula: len(x) + len(y) - len(LCS)
        return m + n - lcsLength;
    }

    public static String printSCS(String x, String y, int m, int n) {
        int[][] t = new int[m + 1][n + 1];
        // Build the LCS DP table
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
        int i = m, j = n;
        StringBuilder sb = new StringBuilder();
        // Backtrack through the DP table to construct the SCS
        while(i > 0 && j > 0){
            if(x.charAt(i - 1) == y.charAt(j - 1)){
                // If characters match, append one and move diagonally up-left
                sb.append(x.charAt(i - 1));
                i--;
                j--;
            }else if(t[i - 1][j] > t[i][j - 1]){
                // If characters don't match, and LCS came from x[i-1], append x[i-1]
                // and move up (decrement i)
                sb.append(x.charAt(i - 1));
                i--;
            }else{
                // If characters don't match, and LCS came from y[j-1], append y[j-1]
                // and move left (decrement j)
                sb.append(y.charAt(j - 1));
                j--;
            }
        }
        // Append any remaining characters from string x
        while(i > 0){
            sb.append(x.charAt(i - 1));
            i--;
        }
        // Append any remaining characters from string y
        while(j > 0){
            sb.append(y.charAt(j - 1));
            j--;
        }
        return sb.reverse().toString();
    }
    public static void main(String[] args) {
        String x = "AGGTAB";
        String y = "GXTXAYB";
        int m = x.length();
        int n = y.length();

        // --- To get ONLY the length (more efficient) ---
        int length = getSCSLength(x, y, m, n);
        System.out.println("Length of SCS (calculated directly): " + length);

        // --- To get the full string (as in your original code) ---
        String scs = printSCS(x, y, m, n);
        System.out.println("Shortest common supersequence: " + scs);
    }
}
