import java.util.*;
// Corrected the class name typo from "PlaimdromePartitioning"
public class PalindromePartitioning {
    public List<List<String>> partitions(String s){
        List<List<String>> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }
    private void backtrack(String s, int start, List<String> current, List<List<String>> result){
        if(start == s.length()){
            result.add(new ArrayList<>(current));
            return;
        }
        // The loop must go up to s.length() to include substrings that end at the last character.
        for(int end = start + 1; end <= s.length(); end++){
            String subString = s.substring(start, end);
            // Corrected typo in method name
            if(isPalindrome(subString)){
                current.add(subString);
                backtrack(s, end, current, result);
                // Corrected index for removing the last element
                current.remove(current.size() - 1);
            }
        }
    }
    // Corrected typo in method name
    private boolean isPalindrome(String s){
        int left = 0, right = s.length() - 1;
        while(left < right){
            if(s.charAt(left++) != s.charAt(right--)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromePartitioning pp = new PalindromePartitioning();
        String s = "aab";
        List<List<String>> partitions = pp.partitions(s);
        for(List<String> part: partitions){
            System.out.print(part);
        }
    }
}