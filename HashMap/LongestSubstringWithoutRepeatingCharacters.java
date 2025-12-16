import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {
    public static int longestSubstringWithoutRepeatingCharacters(String s){
        // It's more conventional to return 0 for null/empty strings than to throw an exception.
        if (s == null || s.length() == 0) {
            return 0;
        }
        int left = 0;
        int maxLen = 0;
        Map<Character, Integer> lastSeenIndex = new HashMap<>();
        for(int right = 0; right < s.length(); right++){// Iterate over the string
            char c = s.charAt(right);// Get the current character

            if(lastSeenIndex.containsKey(c)){// If the character was seen before
                left = Math.max(left, lastSeenIndex.get(c) + 1);// Move left pointer
            }
            lastSeenIndex.put(c, right);// Update the last seen index of the character
            maxLen = Math.max(maxLen, right - left + 1);// Update the maximum length
        }
        return maxLen;// Return the maximum length found
    }
    public static void main(String[] args) {
        String s = "abcabcbb";
        int result = longestSubstringWithoutRepeatingCharacters(s);
        // Expected: 3 (for "abc")
        System.out.println("Length of the longest substring without repeating characters: " + result);

        String s2 = "pwwkew";
        int result2 = longestSubstringWithoutRepeatingCharacters(s2);
        // Expected: 3 (for "wke")
        System.out.println("Length of the longest substring without repeating characters: " + result2);
    }
}
