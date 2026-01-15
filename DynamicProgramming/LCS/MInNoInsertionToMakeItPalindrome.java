/**
 * This class calculates the minimum number of insertions required to make a string a palindrome.
 * The logic is based on finding the Longest Palindromic Subsequence (LPS).
 * The number of insertions needed is equal to the number of characters that are not part of the LPS.
 * Number of insertions = length of string - length of LPS.
 * The LPS is found by calculating the Longest Common Subsequence (LCS) of the string and its reverse.
 */
public class MInNoInsertionToMakeItPalindrome {
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
        int[][] dp = new int[m+1][n+1];
        // The loops must go up to and including m and n to fill the entire DP table.
        for(int i = 0 ; i <= m; i++){
            for(int j = 0; j <= n; j++){
                if(i == 0 || j == 0){
                    dp[i][j] = 0;
                }
                else if(x.charAt(i - 1) == y.charAt(j - 1)){
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }
                else{
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        // The result is in the bottom-right cell of the table.
        return dp[m][n];
    }

    /**
     * Calculates the minimum number of insertions to make a string a palindrome.
     * @param s The input string.
     * @return The minimum number of insertions required.
     */
    public static int minInsertions(String s){
        String rev = new StringBuilder(s).reverse().toString();
        int lps = lcs(s, rev, s.length(), rev.length());
        return s.length() - lps;
    }
    public static void main(String[] args){
        String str = "abcda"; // LPS is "aca" (length 3). Insertions needed = 5 - 3 = 2 (to make "adcb cda").
        int result = minInsertions(str);
        System.out.println("Minimum insertions to make \"" + str + "\" a palindrome: " + result);
    }
}
