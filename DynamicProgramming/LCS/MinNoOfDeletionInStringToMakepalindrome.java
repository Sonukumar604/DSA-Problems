/**
 * This class calculates the minimum number of deletions required to make a string a palindrome.
 * The solution is based on finding the Longest Palindromic Subsequence (LPS).
 * The number of deletions = length of string - length of LPS.
 * The LPS is found by calculating the Longest Common Subsequence (LCS) of the string and its reverse.
 */
public class MinNoOfDeletionInStringToMakepalindrome {
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
        // This is the standard bottom-up DP approach for LCS.
        for(int i = 0; i <= m; i++){
            for(int j = 0; j <= n; j++){
                if(i == 0 || j == 0){
                    t[i][j] = 0;
                } else if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    t[i][j] = 1 + t[i - 1][j - 1];
                } else {
                    t[i][j] = Math.max(t[i - 1][j], t[i][j - 1]);
                }
            }
        }
        return t[m][n];
    }

    /**
     * Computes the length of the Longest Palindromic Subsequence (LPS) of a string.
     */
    public static int lps(String s){
        String rev = new StringBuilder(s).reverse().toString();
        return lcs(s, rev, s.length(), rev.length());
    }

    public static int minDeletionsToMakePalindrome(String s){
        int lpsLength = lps(s);
        return s.length() - lpsLength;
    }
    public static void main(String[] args) {
        String s = "agbcba";
        // For "agbcba", the LPS is "abcba" (length 5).
        // Deletions needed = 6 - 5 = 1 (remove 'g').
        System.out.println("Minimum deletions to make palindrome: " + minDeletionsToMakePalindrome(s));
    }
}
