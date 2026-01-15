import java.util.ArrayList;
import java.util.List;

public class BalancedParanthesis {

    /**
     * A helper method that recursively generates parenthesis combinations.
     *
     * @param open   The number of remaining open parentheses to add.
     * @param close  The number of remaining close parentheses to add.
     * @param output The current string being built.
     * @param result The list to store the final combinations.
     */
    public static void solve(int open, int close, String output, List<String> result){
        if(open == 0 && close == 0){
            result.add(output);
            return;
        }
        if(open > 0){
            solve(open - 1, close, output + "(", result);
        }
        if(close > open){
            solve(open, close - 1, output + ")", result);
        }
    }
    public static void main(String[] args){
        int n = 3;
        List<String> result = new ArrayList<>();
        solve(n,n,"", result);
        System.out.println("Balanced paranthesis combinations for n = " + n + " are: " + result);
    }
}