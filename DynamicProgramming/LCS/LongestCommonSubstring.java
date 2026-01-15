import java.util.Arrays;

/**
 * This class finds the length of the Longest Common Substring between two strings.
 * This is a standard dynamic programming problem.
 */
public class LongestCommonSubstring {

    // 1. Plain Recursion
    // Note: This is highly inefficient due to re-computation of subproblems.
    // The state needs to track the length of the current matching suffix.
    public static int lcsRecursive(String x, String y, int m, int n, int count) {
        // Base case: if we've reached the end of either string.
        if (m == 0 || n == 0) {
            return count;
        }
        // If the last characters match, we extend the current substring.
        if (x.charAt(m - 1) == y.charAt(n - 1)) {
            count = lcsRecursive(x, y, m - 1, n - 1, count + 1);
        }
        // We also need to check for other substrings that might not include the current characters.
        // The current suffix is broken, so we start a new search with count = 0.
        int count1 = lcsRecursive(x, y, m, n - 1, 0);
        int count2 = lcsRecursive(x, y, m - 1, n, 0);
        // The result is the maximum of the current extended suffix and any other substrings found.
        return Math.max(count, Math.max(count1, count2));
    }

    // 2. Memoization (Top-Down DP)
    // We use a DP table to store results of subproblems.
    // A separate variable is needed to track the max length found globally.
    static int maxLength = 0;
    public static int lcsMemoized(String x, String y, int m, int n, int[][] dp) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (dp[m][n] != -1) {
            return dp[m][n];
        }

        // We must still explore other possibilities even if we find a match.
        lcsMemoized(x, y, m, n - 1, dp);
        lcsMemoized(x, y, m - 1, n, dp);

        if (x.charAt(m - 1) == y.charAt(n - 1)) {
            dp[m][n] = 1 + lcsMemoized(x, y, m - 1, n - 1, dp);
            // Update the global max length.
            maxLength = Math.max(maxLength, dp[m][n]);
            return dp[m][n];
        } else {
            return dp[m][n] = 0;
        }
    }


    /**
     * 3. Tabulation (Bottom-Up DP)
     * Calculates the length of the longest common substring. This is the most
     * efficient and standard approach.
     *
     * @param x The first string.
     * @param y The second string.
     * @param m The length of string x.
     * @param n The length of string y.
     * @return The length of the longest common substring.
     */
    public static int lcsTabulation(String x, String y, int m, int n){
        int[][] t = new int[m + 1][n + 1];
        int result = 0;
        // Build the DP table. t[i][j] stores the length of the common substring
        // ending at x[i-1] and y[j-1].
        for(int i = 0; i <= m; i++){
            for(int j = 0; j <= n; j++){
                // If characters match, extend the common substring length.
                if (i == 0 || j == 0) {
                    t[i][j] = 0;
                } else if(x.charAt(i - 1) == y.charAt(j - 1)){
                    t[i][j] = 1 + t[i - 1][j - 1];
                    // Update the max length found so far.
                    result = Math.max(result, t[i][j]);
                }else{
                    // If characters don't match, the common substring is broken. Reset length to 0.
                    t[i][j] = 0;
                }
            }
        }
        return result;
    }
    public static void main(String[] args) {
        String x = "abcde";
        String y = "abfce";
        int m = x.length();
        int n = y.length();

        System.out.println("Tabulation Result: " + lcsTabulation(x, y, m, n));

        // --- Memoization ---
        int[][] dp = new int[m + 1][n + 1];
        for(int[] row : dp) Arrays.fill(row, -1);
        maxLength = 0; // Reset global max length
        lcsMemoized(x, y, m, n, dp);
        System.out.println("Memoization Result: " + maxLength);

        // --- Recursion ---
        // Note: This can be very slow for larger strings.
        System.out.println("Recursive Result: " + lcsRecursive(x, y, m, n, 0));
    }
}
