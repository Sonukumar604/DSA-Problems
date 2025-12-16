import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public static String minWindow(String s, String t) {
        // 1. Handle edge cases where a solution is impossible.
        if (s == null || t == null || s.length() < t.length() || t.isEmpty()) {
            return "";
        }
        // 2. Create a frequency map of the target string 't'. This is what we need to find.
        Map<Character, Integer> tMap = new HashMap<>();
        for(char c : t.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);// Create frequency map for t
        }
        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int minStart = 0; // Start index of the minimum window found
        int required = tMap.size(); // The number of unique characters we need in the window
        int formed = 0; // The number of unique characters we have satisfied in the window
        Map<Character, Integer> windowMap = new HashMap<>();

        // Expand the window by moving the 'right' pointer
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // Add the new character to the window
            windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);

            // Check if the current character is needed and if its count in the window now matches the target
            if (tMap.containsKey(c) && windowMap.get(c).equals(tMap.get(c))) {
                formed++;
            }

            // Try to contract the window from the left once we have a valid window
            while (left <= right && formed == required) {
                // Update the minimum window size found so far
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }

                // Remove the character at the 'left' of the window
                char leftChar = s.charAt(left);
                windowMap.put(leftChar, windowMap.get(leftChar) - 1);
                if (tMap.containsKey(leftChar) && windowMap.get(leftChar) < tMap.get(leftChar)) {
                    formed--;
                }
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        String result = minWindow(s, t);
        System.out.println("Minimum window substring: " + result);
    }
}