import java.util.HashMap;

public class LongestSubstringWithoutRepeatingCharsHashMap {
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0, maxLen = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (map.containsKey(c)) {
                // Move left pointer to the right of the last occurrence of c
                left = Math.max(left, map.get(c) + 1);
            }
            map.put(c, right);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        String s1 = "abcabcbb";
        System.out.println("Input: \"" + s1 + "\", Length: " + lengthOfLongestSubstring(s1)); // Expected: 3

        String s2 = "bbbbb";
        System.out.println("Input: \"" + s2 + "\", Length: " + lengthOfLongestSubstring(s2)); // Expected: 1

        String s3 = "pwwkew";
        System.out.println("Input: \"" + s3 + "\", Length: " + lengthOfLongestSubstring(s3)); // Expected: 3
    }
}