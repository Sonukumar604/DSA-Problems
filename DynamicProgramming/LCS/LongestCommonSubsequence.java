import java.util.Arrays;

/**
 * This class provides three different methods (Recursive, Memoized, and Tabulation)
 * to find the length of the Longest Common Subsequence (LCS) between two strings.
 * Note: The original filename had a typo "LongesCommomSequence".
 */
public class LongestCommonSubsequence {

    // 1. Plain Recursion (Top-Down)
    public static int lcsRecursive(String x, String y, int m, int n) {
        // Base case: If either string is empty, the LCS length is 0.
        if (m == 0 || n == 0) {
            return 0;
        }

        // If the last characters of both strings match
        if (x.charAt(m - 1) == y.charAt(n - 1)) {
            // The character is part of the LCS. Add 1 and recur for the remaining strings.
            return 1 + lcsRecursive(x, y, m - 1, n - 1);
        } else {
            // If the last characters don't match, find the maximum LCS by either:
            // a) Ignoring the last character of string x
            // b) Ignoring the last character of string y
            return Math.max(lcsRecursive(x, y, m, n - 1), lcsRecursive(x, y, m - 1, n));
        }
    }

    // 2. Memoization (Top-Down Dynamic Programming)
    public static int lcsMemoized(String x, String y, int m, int n, int[][] dp) {
        // Base case
        if (m == 0 || n == 0) {
            return 0;
        }

        // If this subproblem has already been solved, return the stored result.
        if (dp[m][n] != -1) {
            return dp[m][n];
        }

        // If the last characters match
        if (x.charAt(m - 1) == y.charAt(n - 1)) {
            // Compute, store, and then return the result.
            return dp[m][n] = 1 + lcsMemoized(x, y, m - 1, n - 1, dp);
        } else {
            // Compute, store, and then return the result.
            return dp[m][n] = Math.max(lcsMemoized(x, y, m, n - 1, dp), lcsMemoized(x, y, m - 1, n, dp));
        }
    }

    // 3. Tabulation (Bottom-Up Dynamic Programming)
    public static int lcsTabulation(String x, String y, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];

        // Build the DP table from the smallest subproblems upwards.
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0; // Initialization for base cases
                } else if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        // The final answer is in the bottom-right cell of the table.
        return dp[m][n];
    }

    // 4. Print LCS String
    public static String printLCS(String x, String y, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];

        // Build the DP table
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Backtrack to find the LCS string
        int i = m, j = n;
        StringBuilder sb = new StringBuilder();
        while (i > 0 && j > 0) {
            if (x.charAt(i - 1) == y.charAt(j - 1)) {
                sb.append(x.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String x = "AGGTAB";
        String y = "GXTXAYB";
        int m = x.length();
        int n = y.length();

        System.out.println("Tabulation LCS Length: " + lcsTabulation(x, y, m, n));

        int[][] dp = new int[m + 1][n + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        System.out.println("Memoization LCS Length: " + lcsMemoized(x, y, m, n, dp));

        System.out.println("Recursive LCS Length: " + lcsRecursive(x, y, m, n));

        System.out.println("LCS String: " + printLCS(x, y, m, n));
    }
}
