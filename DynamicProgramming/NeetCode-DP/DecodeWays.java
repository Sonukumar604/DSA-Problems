import java.util.Arrays;

/**
 * Given a string containing only digits, this class calculates the number of ways to decode it.
 * The decoding map is 'A' -> 1, 'B' -> 2, ..., 'Z' -> 26.
 */
public class DecodeWays {
    /**
     * Approach 1: Recursion with Memoization (Top-Down DP)
     *
     * ### Explanation
     * We can think of this problem as finding the number of paths in a decision tree. At each index, we decide whether to decode one digit or two digits.
     * 1.  **Base Cases:**
     *     - If we reach the end of the string (`index == s.length()`), we have found one valid decoding path. Return 1.
     *     - If the current character is '0' (`s.charAt(index) == '0'`), it cannot be decoded by itself, so this path is invalid. Return 0.
     * 2.  **Recursive Step:**
     *     - **One-Digit Decode:** We can always decode the current digit as a single character (if it's not '0'). The number of ways for this choice is the number of ways to decode the rest of the string, which is `decodeWays(s, index + 1)`.
     *     - **Two-Digit Decode:** If we are not at the end of the string and the two-digit number formed by `s[index]` and `s[index+1]` is between 10 and 26, we can decode it as a single character. The number of ways for this choice is `decodeWays(s, index + 2)`.
     * 3.  **Memoization:** To avoid recomputing results for the same index, we use a `memo` array. `memo[index]` stores the number of ways to decode the substring starting from `index`.
     *
     * ### Dry Run (s = "226")
     * `decodeWays("226", 0)`
     * ├── `count = decodeWays("226", 1)`
     * │   ├── `count = decodeWays("226", 2)`
     * │   │   ├── `count = decodeWays("226", 3)` -> returns 1 (base case: end of string)
     * │   │   └── (Two-digit check for "6" fails)
     * │   │   -> `memo[2] = 1`. Returns 1.
     * │   └── `count += decodeWays("226", 3)` (since "26" is valid) -> returns 1.
     * │   -> `memo[1] = 1 + 1 = 2`. Returns 2.
     * └── `count += decodeWays("226", 2)` (since "22" is valid)
     *     └── returns `memo[2]`, which is 1.
     * -> `memo[0] = 2 + 1 = 3`. Returns 3.
     *
     * ### Complexity
     * - **Time Complexity:** O(N), where N is the length of the string. Each index is computed once.
     * - **Space Complexity:** O(N) for the memoization array and recursion stack.
     */
    public int numDecodings(String s){
        if(s == null || s.length() == 0){
            return 0;
        }
        int n = s.length();
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return decodeWays(s, 0, memo);
    }

    private int decodeWays(String s, int index, int[] memo){
        // Base case: Reached the end, one valid decoding found.
        if(index == s.length()){
            return 1;
        }
        // Base case: '0' cannot be a single-digit decoding.
        if(s.charAt(index) == '0'){
            return 0;
        }
        // Return cached result if available.
        if(memo[index] != -1){
            return memo[index];
        }
        
        // Decode the current digit as a single character.
        int count = decodeWays(s, index + 1, memo);
        
        // Check if a two-digit decoding is possible.
        if(index + 1 < s.length()){
            int twoDigit = Integer.parseInt(s.substring(index, index + 2));
            if(twoDigit >= 10 && twoDigit <= 26){
                count += decodeWays(s, index + 2, memo);
            }
        }
        // Cache and return the result.
        return memo[index] = count;
    }

    /**
     * Approach 2: Iterative DP (Bottom-Up)
     *
     * ### Explanation
     * We use a `dp` array where `dp[i]` stores the number of ways to decode the prefix of the string of length `i`.
     * - `dp[0] = 1` (An empty string has one way to be decoded: by doing nothing).
     * - `dp[1]` is 1 if `s[0]` is not '0', otherwise 0.
     * - We iterate from `i = 2` to `n`. For each `i`, we look at the last one or two characters of the substring `s.substring(0, i)`.
     *   - **One-digit:** If `s[i-1]` is not '0', it can be decoded alone. We add `dp[i-1]` ways to `dp[i]`.
     *   - **Two-digits:** If the number formed by `s[i-2]` and `s[i-1]` is between 10 and 26, it can be decoded as a pair. We add `dp[i-2]` ways to `dp[i]`.
     *
     * ### Complexity
     * - **Time Complexity:** O(N).
     * - **Space Complexity:** O(N) for the DP array. (Can be optimized to O(1)).
     */
    public int numDecodingsIterative(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 2; i <= n; i++) {
            int oneDigit = Integer.parseInt(s.substring(i - 1, i));
            int twoDigits = Integer.parseInt(s.substring(i - 2, i));

            if (oneDigit >= 1) {
                dp[i] += dp[i - 1];
            }
            if (twoDigits >= 10 && twoDigits <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        DecodeWays dw = new DecodeWays();
        String[] testCases = {"226", "12", "06", "10", "27", "11106"};
        for (String s : testCases) {
            System.out.println("Ways to decode '" + s + "':");
            System.out.println("  - Memoization: " + dw.numDecodings(s));
            System.out.println("  - Iterative:   " + dw.numDecodingsIterative(s));
            System.out.println();
        }
    }
}
