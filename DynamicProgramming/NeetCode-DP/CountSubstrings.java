public class CountSubstrings {
    /**
     * Counts all palindromic substrings in a given string.
     * This implementation uses the "Expand Around Center" approach, which is efficient.
     * The original recursive solution was correct for small inputs but highly inefficient (exponential time)
     * and would fail on larger test cases.
     *
     * Time Complexity: O(n^2) - We iterate through each character and expand outwards.
     * Space Complexity: O(1) - We only use a few variables.
     *
     * @param s The input string.
     * @return The total number of palindromic substrings.
     */
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int count = 0;
        // Iterate through each character of the string to consider it as a center.
        for (int i = 0; i < s.length(); i++) {
            // Count odd-length palindromes with center at i.
            count += expandAroundCenter(s, i, i);
            // Count even-length palindromes with center between i and i+1.
            count += expandAroundCenter(s, i, i + 1);
        }
        return count;
    }

    /**
     * Helper function to expand from a center and count palindromes.
     */
    private int expandAroundCenter(String s, int left, int right) {
        int count = 0;
        // Expand as long as the pointers are within bounds and characters match.
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++; // A valid palindrome is found.
            left--;  // Expand leftwards.
            right++; // Expand rightwards.
        }
        return count;
    }
    //Recursion + Memoization approach
    public int countSubstringsMemo(String s) {
        int n = s.length();
        int count = 0;
        Boolean[][] memo = new Boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isPalindrome(s, i, j, memo)) {
                    count++;
                }
            }
        }
        return count;
    }
    private boolean isPalindrome(String s, int start, int end, Boolean[][] memo) {
        if (start >= end) return true;
        if (memo[start][end] != null) return memo[start][end];

        if (s.charAt(start) == s.charAt(end)) {
            return memo[start][end] = isPalindrome(s, start + 1, end - 1, memo);
        }
        return memo[start][end] = false;
    }

    public static void main(String[] args) {
        CountSubstrings cs = new CountSubstrings();
        String s1 = "abc";
        System.out.println("Total palindromic substrings in \"" + s1 + "\": " + cs.countSubstrings(s1)); // Output: 3

        String s2 = "aaa";
        System.out.println("Total palindromic substrings in \"" + s2 + "\": " + cs.countSubstrings(s2)); // Output: 6
    }
}
