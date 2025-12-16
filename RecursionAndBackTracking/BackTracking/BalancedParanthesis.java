import java.util.ArrayList;
import java.util.List;

public class BalancedParanthesis {
    public List<String> generateParanthesis(int n){
        List<String> result = new ArrayList<>();
        backtrack(result, new StringBuilder(), 0, 0 , n);
        return result;
    }
    private void backtrack(List<String> result, StringBuilder current, int open, int close, int max){
        if(current.length() == max * 2){
            result.add(current.toString());
            return;
        }

        if(open < max){
            backtrack(result, current.append("("), open + 1, close, max);
            current.deleteCharAt(current.length() - 1); // backtrack
        }
        if(close < open){
            backtrack(result, current.append(")"), open, close + 1, max);
            current.deleteCharAt(current.length() - 1); // backtrack
        }
    }
    public static void main(String[] args) {
        BalancedParanthesis bp = new BalancedParanthesis();
        int n = 3;
        List<String> paranthesisCombinations = bp.generateParanthesis(n);
        System.out.println("Balanced Paranthesis combinations for n = " + n + ": " + paranthesisCombinations);
    }
}
