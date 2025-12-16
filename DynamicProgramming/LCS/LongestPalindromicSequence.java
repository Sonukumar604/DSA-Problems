public class LongestPalindromicSequence {
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
    //function to compute LPS using LCS(s, reverse(s))
    public static int lps(String s){
        String rev = new StringBuilder(s).reverse().toString();
        return lcs(s, rev, s.length(), rev.length());
    }
    public static void main(String[] args) {
        String s = "agbcba";
        int lpsLength = lps(s);
        System.out.println("Length of the longest palindromic subsequence: " + lpsLength);
    }
}
