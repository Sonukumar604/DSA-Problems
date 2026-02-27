import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Checks if the string s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * ### Approach: Dynamic Programming (Bottom-Up)
 * We use a boolean array `dp` where `dp[i]` represents whether the substring `s[0...i]` can be segmented into words from the dictionary.
 * - Base Case: `dp[0] = true` (an empty string is a valid segmentation).
 * - Transition: For each index `i` from 1 to `n`, we check all possible split points `j` (0 to `i-1`).
 *   If `dp[j]` is true (meaning `s[0...j]` is valid) AND the substring `s[j...i]` is in the dictionary, then `dp[i]` becomes true.
 *
 * ### Time and Space Complexity
 * - **Time Complexity:** O(N^3), where N is the length of string `s`.
 *   - The nested loops run in O(N^2).
 *   - The `substring` method and hash set lookup take O(N) in the worst case.
 * - **Space Complexity:** O(N) for the `dp` array and O(M) for the HashSet where M is the total characters in `wordDict`.
 *
 * ### Dry Run
 * Input: `s = "leetcode"`, `wordDict = ["leet", "code"]`
 * Length `n = 8`. `dp` array of size 9 initialized to false. `dp[0] = true`.
 *
 * 1. **i = 1 to 3:** No valid words found ending at these positions. `dp[1..3]` remain false.
 * 2. **i = 4 (substring "leet"):**
 *    - Check `j = 0`: `dp[0]` is true. Substring `s.substring(0, 4)` is "leet".
 *    - "leet" is in `wordDict`.
 *    - Set `dp[4] = true`. Break inner loop.
 * 3. **i = 5 to 7:**
 *    - Inner loop checks `j` from 0 to `i-1`.
 *    - For `j=4`, `dp[4]` is true, but substrings "c", "co", "cod" are not in dict.
 *    - `dp[5..7]` remain false.
 * 4. **i = 8 (substring "leetcode"):**
 *    - `j = 0`: `dp[0]` is T, "leetcode" not in dict.
 *    - ...
 *    - `j = 4`: `dp[4]` is T. Substring `s.substring(4, 8)` is "code".
 *    - "code" is in `wordDict`.
 *    - Set `dp[8] = true`. Break.
 *
 * Final Result: Return `dp[8]` which is `true`.
 */
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
