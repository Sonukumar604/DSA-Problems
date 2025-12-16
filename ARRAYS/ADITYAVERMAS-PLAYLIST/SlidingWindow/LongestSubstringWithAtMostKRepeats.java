public class LongestSubstringWithAtMostKRepeats {
    public static int longestSubstringWithAtMostKRepeats(String s, int k) {
        if (s == null || s.isEmpty() || k <= 0) {
            return -1;
        }

        int[] charCount = new int[256]; // Assuming ASCII character set
        int left = 0, maxLen = -1, uniqueCount = 0;

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            charCount[rightChar]++;
            if (charCount[rightChar] == 1) {
                uniqueCount++;
            }

            // Shrink the window from the left if unique characters exceed k
            while (uniqueCount > k) {
                char leftChar = s.charAt(left);
                charCount[leftChar]--;
                if (charCount[leftChar] == 0) {
                    uniqueCount--;
                }
                left++;
            }

            // Update max length for valid windows
            if (uniqueCount <= k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }
        return maxLen;
    }
    public static void main(String[] args) {
        String s = "aabacbebebe";
        int k = 3;
        int result = longestSubstringWithAtMostKRepeats(s, k);
        System.out.println("The length of the longest substring with at most " + k + " unique characters is: " + result);

    }
}
