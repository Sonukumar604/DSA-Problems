public class LongestPalindromicSubstring {
    /**
     * Finds the longest palindromic substring using Manacher's Algorithm.
     *
     * ### Explanation
     * Manacher's Algorithm finds the longest palindromic substring in linear time.
     * 1. **Transform String:** Convert `s` into a new string `T` by inserting a separator `#` between characters and adding unique start/end sentinels (`^` and `$`). This handles even-length palindromes uniformly with odd-length ones and avoids boundary checks.
     *    - Example: "aba" -> "^#a#b#a#$"
     * 2. **P Array:** Create an array `P` where `P[i]` is the radius of the palindrome centered at `T[i]`.
     * 3. **Center & Right:** Maintain `center` (C) and `right` (R) of the palindrome that extends furthest to the right.
     * 4. **Iterate:** For each index `i`:
     *    - If `i < R`, initialize `P[i]` using the mirror property: `P[i] = min(R - i, P[2*C - i])`.
     *    - Attempt to expand the palindrome centered at `i`.
     *    - If the new palindrome extends beyond `R`, update `C` and `R`.
     * 5. **Extract:** Find the maximum element in `P`, calculate the start index in the original string, and return the substring.
     *
     * ### Dry Run
     * Input: `s = "babad"`
     * Transformed `str`: `^ # b # a # b # a # d # $`
     * Indices: `0 1 2 3 4 5 6 7 8 9 10 ...`
     *
     * - **i=4 ('a'):** Suppose we are here. `C` was updated previously.
     *   - Expand around 'a': `#a#`, `b#a#b`.
     *   - `P[4]` becomes 3 (radius in `str` corresponds to length "bab").
     *   - `R` updates to `4 + 3 = 7`. `C` becomes 4.
     * - **i=5 ('#'):** `i < R` (5 < 7). Mirror of 5 around 4 is `2*4 - 5 = 3`.
     *   - `P[3]` (at first '#') is 0.
     *   - `P[5] = min(7-5, P[3]) = 0`.
     *   - Try expand: `str[6] ('b') != str[4] ('a')`. No expansion.
     * - **i=6 ('b'):** `i < R` (6 < 7). Mirror of 6 around 4 is `2`.
     *   - `P[2]` (at 'b') is 1 (from "b").
     *   - `P[6] = min(7-6, P[2]) = min(1, 1) = 1`.
     *   - Try expand: `str[8] ('a') == str[4] ('a')`. Match.
     *   - `str[9] ('#') == str[3] ('#')`. Match.
     *   - `str[10] ('d') != str[2] ('b')`. Stop.
     *   - `P[6]` becomes 3. Palindrome "aba".
     *   - `i + P[i] = 9 > R`. Update `C=6`, `R=9`.
     *
     * Max `P[i]` is 3. Corresponds to "bab" or "aba".
     *
     * ### Complexity
     * - **Time Complexity:** O(N). Although there is a nested while loop, `R` only increases (monotonically). Each character is touched a constant number of times.
     * - **Space Complexity:** O(N) for the transformed string and the `P` array.
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";

        // 1. Transform S into T
        StringBuilder sb = new StringBuilder();
        sb.append("^");
        for (int i = 0; i < s.length(); i++) {
            sb.append("#").append(s.charAt(i));
        }
        sb.append("#$");

        String str = sb.toString();
        int[] p = new int[str.length()];

        int center = 0, right = 0;

        for (int i = 1; i < str.length() - 1; i++) {
            int mirror = 2 * center - i;

            if (i < right)
                p[i] = Math.min(right - i, p[mirror]);

            // Attempt to expand palindrome centered at i
            while (str.charAt(i + 1 + p[i]) == str.charAt(i - 1 - p[i])) {
                p[i]++;
            }

            // If palindrome centered at i expands past right,
            // adjust center and right boundary
            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }
        }

        // Find the maximum element in P.
        int maxLen = 0, centerIndex = 0;
        for (int i = 1; i < p.length - 1; i++) {
            if (p[i] > maxLen) {
                maxLen = p[i];
                centerIndex = i;
            }
        }

        int start = (centerIndex - maxLen) / 2;
        return s.substring(start, start + maxLen);
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring solution = new LongestPalindromicSubstring();

        String s1 = "babad";
        System.out.println("Input: \"" + s1 + "\" -> Output: " + solution.longestPalindrome(s1));

        String s2 = "cbbd";
        System.out.println("Input: \"" + s2 + "\" -> Output: " + solution.longestPalindrome(s2));

        String s3 = "a";
        System.out.println("Input: \"" + s3 + "\" -> Output: " + solution.longestPalindrome(s3));
    }
}
