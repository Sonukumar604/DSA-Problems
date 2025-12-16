import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithNoUniqueCharacters {

    /**
     * Checks if the given frequency map contains any character with a count of 1.
     *
     * @param map The frequency map of characters in the current window.
     * @return true if a unique character (count == 1) is found, false otherwise.
     */
    private static boolean hasUniqueCharacter(Map<Character, Integer> map) {
        for (int count : map.values()) {
            if (count == 1) {
                return true; // Found a character that appears only once
            }
        }
        return false; // No unique characters found
    }

    /**
     * Finds the length of the longest substring where every character appears more than once.
     *
     * @param s The input string.
     * @return The length of the longest valid substring, or -1 if none exists.
     */
    public static int findLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>();
        int left = 0, maxLen = -1;

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            map.put(rightChar, map.getOrDefault(rightChar, 0) + 1);

            // While the current window contains a unique character (count == 1),
            // shrink the window from the left.
            while (hasUniqueCharacter(map)) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                left++;
            }

            // After shrinking, the window is guaranteed to have no unique characters.
            // We can calculate its length and update the maximum.
            // A check `left <= right` ensures we don't calculate length for an invalid window.
            if (left <= right) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        String s = "aabacbebebe";
        // The method name was changed to follow Java conventions.
        int result = findLongestSubstring(s);
        System.out.println("The length of the longest substring with no unique characters is: " + result); // Expected: 6 (for "bebebe")

        String s2 = "abacaba";
        int result2 = findLongestSubstring(s2);
        System.out.println("The length of the longest substring with no unique characters is: " + result2); // Expected: -1 (no such substring)
    }
}
