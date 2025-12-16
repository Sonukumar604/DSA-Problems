public class LongestRepeatingCharacterReplacement {
    public static int characterReplacement(String s, int k) {
        if (s == null || s.isEmpty() || k < 0) {
            return 0;
        }

        int[] charCount = new int[26]; // Assuming only uppercase English letters
        int left = 0, maxCount = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            charCount[rightChar - 'A']++;
            maxCount = Math.max(maxCount, charCount[rightChar - 'A']);

            // Current window size is (right - left + 1)
            // Number of characters to change is (window size - maxCount)
            while ((right - left + 1) - maxCount > k) {
                char leftChar = s.charAt(left);
                charCount[leftChar - 'A']--;
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
    
    public static void main(String[] args) {
        String s = "AABABBA";
        int k = 1;
        int result = characterReplacement(s, k);
        System.out.println("The length of the longest substring after replacement is: " + result);
    }
}
