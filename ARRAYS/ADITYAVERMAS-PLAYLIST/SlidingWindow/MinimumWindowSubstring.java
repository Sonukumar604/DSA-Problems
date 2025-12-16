public class MinimumWindowSubstring {
    public static String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        int[] charCount = new int[128]; // ASCII character set
        for (char c : t.toCharArray()) {
            charCount[c]++;
        }

        int left = 0, right = 0, required = t.length();
        int minLen = Integer.MAX_VALUE;
        int minStart = 0;

        while (right < s.length()) {
            char rightChar = s.charAt(right);
            if (charCount[rightChar] > 0) {
                required--;
            }
            charCount[rightChar]--;
            right++;

            while (required == 0) {
                if (right - left < minLen) {
                    minLen = right - left;
                    minStart = left;
                }

                char leftChar = s.charAt(left);
                charCount[leftChar]++;
                if (charCount[leftChar] > 0) {
                    required++;
                }
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
    public static void main(String[] args) {
        String s = "ADITYAVERMAS-PLAYLIST";
        String t = "SLIDINGWINDOW";
        String result = minWindow(s, t);
        System.out.println("The minimum window substring is: " + result);
        String s2 = "aabdec";
        String t2 = "abc";
        String result2 = minWindow(s2, t2);
        System.out.println("The minimum window substring is: " + result2);
    }
}
