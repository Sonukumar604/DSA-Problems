/**
 * Counts the number of non-empty, contiguous substrings that have the same number of 0's and 1's,
 * where all the 0's and all the 1's in these substrings are grouped consecutively.
 *
 * ### Explanation
 * The algorithm iterates through the string, counting the lengths of consecutive groups of identical characters ('0's or '1's).
 * The core idea is that for any two adjacent groups of different characters (e.g., "00" and "111"), the number of valid substrings that can be formed across their boundary is determined by the minimum of their lengths.
 *
 * For example, with "00" (length 2) and "111" (length 3), we can form "01" and "0011". The number of these is `min(2, 3) = 2`.
 *
 * We use three variables:
 * - `prevCount`: Stores the length of the previous group of identical characters.
 * - `currCount`: Stores the length of the current group we are scanning.
 * - `result`: Accumulates the total count of valid substrings.
 *
 * When we encounter a character change (e.g., from '0' to '1'), it signifies the end of one group and the beginning of another. At this point, we add `min(prevCount, currCount)` to our result. We then update `prevCount` with the length of the group that just ended (`currCount`) and reset `currCount` to 1 for the new group.
 *
 * ### Dry Run (s = "0011100")
 * Initialize: `prevCount = 0`, `currCount = 1`, `result = 0`.
 *
 * 1. **i = 1:** `s[1] == s[0]` ('0' == '0'). `currCount` becomes 2.
 * 2. **i = 2:** `s[2] != s[1]` ('1' != '0'). Group changes.
 *    - `result += min(prevCount, currCount)` -> `result += min(0, 2)` -> `result` is 0.
 *    - `prevCount` becomes 2, `currCount` resets to 1.
 * 3. **i = 3:** `s[3] == s[2]` ('1' == '1'). `currCount` becomes 2.
 * 4. **i = 4:** `s[4] == s[3]` ('1' == '1'). `currCount` becomes 3.
 * 5. **i = 5:** `s[5] != s[4]` ('0' != '1'). Group changes.
 *    - `result += min(prevCount, currCount)` -> `result += min(2, 3)` -> `result` is 2. (For "00" and "111")
 *    - `prevCount` becomes 3, `currCount` resets to 1.
 * 6. **i = 6:** `s[6] == s[5]` ('0' == '0'). `currCount` becomes 2.
 *
 * **End of Loop:**
 * - We must account for the last pair of groups.
 * - `result += min(prevCount, currCount)` -> `result += min(3, 2)` -> `result` is `2 + 2 = 4`. (For "111" and "00")
 *
 * Final Result: 4
 *
 * ### Complexity
 * - **Time Complexity:** O(N), where N is the length of the string. We perform a single pass through the string.
 * - **Space Complexity:** O(1). We only use a few constant-space variables.
 */
public class CountBinarySubstrings {
    public int countBinarySubstrings(String s) {
        int prevCount = 0;
        int currCount = 1;
        int result = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                currCount++;
            } else {
                result += Math.min(prevCount, currCount);
                prevCount = currCount;
                currCount = 1;
            }
        }
        // Add last group pair
        result += Math.min(prevCount, currCount);
        return result;
    }

    public static void main(String[] args) {
        CountBinarySubstrings solution = new CountBinarySubstrings();

        String s1 = "00110011";
        System.out.println("Input: \"" + s1 + "\" -> Output: " + solution.countBinarySubstrings(s1)); // Expected: 6

        String s2 = "10101";
        System.out.println("Input: \"" + s2 + "\" -> Output: " + solution.countBinarySubstrings(s2)); // Expected: 4

        String s3 = "0011100";
        System.out.println("Input: \"" + s3 + "\" -> Output: " + solution.countBinarySubstrings(s3)); // Expected: 4
    }
}
