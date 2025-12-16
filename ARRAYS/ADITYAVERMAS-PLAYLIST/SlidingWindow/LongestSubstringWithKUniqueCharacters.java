import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithKUniqueCharacters {
    /**
     * Finds the length of the longest substring with exactly k unique characters.
     *
     * @param s The input string.
     * @param k The number of unique characters.
     * @return The length of the longest substring, or -1 if no such substring exists.
     */
    public static int longestKUniqueSubstring(String s, int k) {
        if (s == null || s.isEmpty() || k <= 0) {
            return -1;
        }

        Map<Character, Integer> map = new HashMap<>();
        int left = 0, maxLen = -1; // Initialize maxLen to -1 for cases where no valid substring is found

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            map.put(rightChar, map.getOrDefault(rightChar, 0) + 1);

            // Shrink the window from the left if the number of unique characters exceeds k
            while (map.size() > k) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                left++;
            }

            // If the window has exactly k unique characters, update the max length
            if (map.size() == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        // The string literal must be enclosed in double quotes
        String s = "harharmahadevdevokdevmahadev";
        int k = 3;
        int result = longestKUniqueSubstring(s, k);
        System.out.println("The length of the longest substring with " + k + " unique characters is: " + result);

        // Another test case
        String s2 = "aabacbebebe";
        int k2 = 3;
        int result2 = longestKUniqueSubstring(s2, k2);
        System.out.println("The length of the longest substring with " + k2 + " unique characters is: " + result2);
    }
}
