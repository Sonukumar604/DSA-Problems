import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class wordBreak {
    /**
     * Approach 1: Brute Force Recursion
     *
     * Time Complexity: O(2^n)
     * Explanation: In the worst case (e.g., s="aaaa", dict=["a", "aa", ...]), the recursion tree
     * has a branching factor proportional to the string length, leading to exponential growth.
     *
     * Space Complexity: O(n)
     * Explanation: The maximum depth of the recursion stack is n.
     */
    public boolean wordBreakRecursion(String s, List<String> wordDict){
        if(s.length() == 0) return true;

        for(int i = 1; i <= s.length(); i++){
            String prefix = s.substring(0, i);
            
            if(wordDict.contains(prefix)){
                String suffix = s.substring(i);
                if(wordBreakRecursion(suffix, wordDict)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Approach 2: Iterative Dynamic Programming (Bottom-Up)
     *
     * Time Complexity: O(n^3)
     * Explanation:
     * - The two nested loops run in O(n^2).
     * - The substring operation takes O(n).
     * - The dictionary lookup takes O(n) on average if using a HashSet (hashing the string).
     *   (If using a List, it would be slower, so we convert to a Set first).
     *   Total: O(n^2 * n) = O(n^3).
     *
     * Space Complexity: O(n)
     * Explanation: We use a boolean array 'dp' of size n+1 and a HashSet for the dictionary.
     */
    public boolean wordBreak(String s, List<String> wordDict){
        // Optimization: Convert List to Set for O(1) average time complexity lookups.
        Set<String> wordSet = new HashSet<>(wordDict);

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for(int i = 1; i <= s.length(); i++){
            for(int j = 0; j < i; j++){
                if(dp[j] && wordSet.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        wordBreak wb = new wordBreak();

        // Test Case 1
        String s1 = "leetcode";
        List<String> dict1 = Arrays.asList("leet", "code");
        System.out.println("Input: s = \"" + s1 + "\", wordDict = " + dict1);
        System.out.println("Recursion: " + wb.wordBreakRecursion(s1, dict1));
        System.out.println("DP: " + wb.wordBreak(s1, dict1));
        System.out.println();

        // Test Case 2
        String s2 = "applepenapple";
        List<String> dict2 = Arrays.asList("apple", "pen");
        System.out.println("Input: s = \"" + s2 + "\", wordDict = " + dict2);
        System.out.println("Recursion: " + wb.wordBreakRecursion(s2, dict2));
        System.out.println("DP: " + wb.wordBreak(s2, dict2));
        System.out.println();

        // Test Case 3
        String s3 = "catsandog";
        List<String> dict3 = Arrays.asList("cats", "dog", "sand", "and", "cat");
        System.out.println("Input: s = \"" + s3 + "\", wordDict = " + dict3);
        System.out.println("Recursion: " + wb.wordBreakRecursion(s3, dict3));
        System.out.println("DP: " + wb.wordBreak(s3, dict3));
    }
}
