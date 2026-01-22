import java.util.Arrays;

public class EvaluateExpressionToTrueBooleanParanthesis {
    public static int countWays(String s, int i, int j, boolean isTrue){
        if(i > j) return 0;
        if(i == j) {
            if(isTrue) return(s.charAt(i) == 'T') ? 1 : 0;
            else return(s.charAt(i) == 'F') ? 1 : 0;
        }
        int ways = 0;
        for(int k = i + 1; k <= j - 1; k += 2){ // Operators are at odd indices, so step by 2
            char op = s.charAt(k);
            int lt = countWays(s, i, k-1, true);
            int lf = countWays(s, i, k-1, false);
            int rt = countWays(s, k+1, j, true);
            int rf = countWays(s, k+1, j, false);
            if(op =='&'){
                if(isTrue) ways += lt * rt;
                else ways += lf * rf + lf * rt + lt * rf;
            }
            else if(op == '|'){
                if(isTrue) ways += lt * rt + lf * rt + lt * rf;
                else ways += lf * rf;
            }
            else if(op == '^'){
                if(isTrue) ways += lt * rf + rt * lf;
                else ways += lt * rt + lf * rf;
            }
        }
        return ways;
    }

    // Memoization Approach
    static int[][][] dp;
    public static int countWaysMemo(String s, int i, int j, boolean isTrue){
        if(i > j) return 0;
        if(i == j) {
            if(isTrue) return(s.charAt(i) == 'T') ? 1 : 0;
            else return(s.charAt(i) == 'F') ? 1 : 0;
        }
        int isTrueIndex = isTrue ? 1 : 0;
        if(dp[i][j][isTrueIndex] != -1) return dp[i][j][isTrueIndex];

        int ways = 0;
        for(int k = i + 1; k <= j - 1; k += 2){
            char op = s.charAt(k);
            int lt = countWaysMemo(s, i, k-1, true);
            int lf = countWaysMemo(s, i, k-1, false);
            int rt = countWaysMemo(s, k+1, j, true);
            int rf = countWaysMemo(s, k+1, j, false);

            if(op =='&'){
                if(isTrue) ways += lt * rt;
                else ways += lf * rf + lf * rt + lt * rf;
            }
            else if(op == '|'){
                if(isTrue) ways += lt * rt + lf * rt + lt * rf;
                else ways += lf * rf;
            }
            else if(op == '^'){
                if(isTrue) ways += lt * rf + rt * lf;
                else ways += lt * rt + lf * rf;
            }
        }
        return dp[i][j][isTrueIndex] = ways;
    }

    public static void main(String[] args) {
        String expr = "T|F&T^T";
        int n = expr.length();
        System.out.println("Recursive: Ways to Parenthesis True: " + countWays(expr, 0, n-1, true));

        dp = new int[n][n][2];
        for(int[][] row : dp) for(int[] col : row) Arrays.fill(col, -1);
        System.out.println("Memoization: Ways to Parenthesis True: " + countWaysMemo(expr, 0, n-1, true));
    }
}
