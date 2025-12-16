import java.util.HashMap;
import java.util.Map;

public class BooleanParanthesisMemo {
    static Map<String, Integer> dp = new HashMap<>();
    static int countWays(String s, int i, int j, boolean isTrue){
        if(i > j) return 0;
        if(i == j) {
            if(isTrue) return(s.charAt(i) == 'T') ? 1 : 0;
            else return(s.charAt(i) == 'F') ? 1 : 0;
        }

        String key = i + " " + j + " " + isTrue;
        if(dp.containsKey(key)) return dp.get(key);

        int ways = 0;
        for(int k = i+1; k <= j-1; k += 2){
            // Recursive calls to get counts for left and right sub-expressions
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
        // Store the result in the map before returning
        dp.put(key, ways);
        return dp.get(key);
    }
    public static void main(String[] args) {
        String expr = "T|F&T^T";
        int n = expr.length();
        System.out.println("Ways to Parathensis True: " + countWays(expr, 0, n-1, true));
    }
}
