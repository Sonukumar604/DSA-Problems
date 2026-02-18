public class LongestPalindromeSubstring {
    // Expand Around Center approach
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        int start = 0;
        int maxLen = 1;

        for (int i = 0; i < s.length(); i++) {
            // Odd length palindrome
            int len1 = expandFromCenter(s, i, i);
            // Even length palindrome
            int len2 = expandFromCenter(s, i, i + 1);

            int len = Math.max(len1, len2);

            if (len > maxLen) {
                maxLen = len;
                start = i - (len - 1) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }

    private int expandFromCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length()
                && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    public static void main(String[] args) {
        LongestPalindromeSubstring solution = new LongestPalindromeSubstring();

        String s1 = "babad";
        System.out.println("Input: \"" + s1 + "\" | Longest Palindrome: " + solution.longestPalindrome(s1));

        String s2 = "cbbd";
        System.out.println("Input: \"" + s2 + "\" | Longest Palindrome: " + solution.longestPalindrome(s2));
    }
}
