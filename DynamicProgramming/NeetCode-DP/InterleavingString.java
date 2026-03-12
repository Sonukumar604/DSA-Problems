/**
 * This class provides a recursive solution to the "Interleaving String" problem (LeetCode 97).
 */
public class InterleavingString {

    /**
     * Checks if s3 is formed by an interleaving of s1 and s2.
     * This is the entry point that calls the recursive helper function.
     *
     * @param s1 The first string.
     * @param s2 The second string.
     * @param s3 The string to check for interleaving.
     * @return true if s3 is an interleaving of s1 and s2, false otherwise.
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        // If the combined length of s1 and s2 is not equal to the length of s3,
        // it's impossible for s3 to be an interleaving.
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        // Start the recursion from the beginning of all strings.
        return solveRecursive(s1, 0, s2, 0, s3, 0);
    }

    /**
     * A recursive helper function to check for interleaving.
     * It explores all possibilities by trying to match the current character of s3
     * with either the current character of s1 or s2.
     *
     * Time Complexity: O(2^(m+n)), where m and n are the lengths of s1 and s2.
     * Space Complexity: O(m+n) for the recursion stack.
     *
     * @param s1 The first string.
     * @param i  The current pointer/index for s1.
     * @param s2 The second string.
     * @param j  The current pointer/index for s2.
     * @param s3 The target string.
     * @param k  The current pointer/index for s3.
     * @return true if a valid interleaving is found down this path, false otherwise.
     */
    private boolean solveRecursive(String s1, int i, String s2, int j, String s3, int k) {
        // Base Case: If we have successfully reached the end of s3, it means we have
        // found a valid interleaving. We also need to ensure we've used all characters
        // from s1 and s2.
        if (k == s3.length()) {
            return i == s1.length() && j == s2.length();
        }

        // Choice 1: Try to match the current character of s3 with the current character of s1.
        if (i < s1.length() && s1.charAt(i) == s3.charAt(k)) {
            // If it matches, recurse by moving the pointers for s1 and s3.
            // If this path leads to a solution, we can return true immediately.
            if (solveRecursive(s1, i + 1, s2, j, s3, k + 1)) {
                return true;
            }
        }

        // Choice 2: Try to match the current character of s3 with the current character of s2.
        if (j < s2.length() && s2.charAt(j) == s3.charAt(k)) {
            // If it matches, recurse by moving the pointers for s2 and s3.
            if (solveRecursive(s1, i, s2, j + 1, s3, k + 1)) {
                return true;
            }
        }

        // If neither character matched or neither path led to a solution, return false.
        return false;
    }

    // --- Memoization Approach ---

    /**
     * Checks if s3 is formed by an interleaving of s1 and s2 using Memoization.
     * Time Complexity: O(m*n)
     * Space Complexity: O(m*n)
     */
    public boolean isInterleaveMemo(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        // memo[i][j] stores the result for s1[i...] and s2[j...]
        // null: not computed, true: possible, false: not possible
        Boolean[][] memo = new Boolean[s1.length() + 1][s2.length() + 1];
        return solveMemo(s1, 0, s2, 0, s3, 0, memo);
    }

    private boolean solveMemo(String s1, int i, String s2, int j, String s3, int k, Boolean[][] memo) {
        if (k == s3.length()) {
            return true;
        }
        if (memo[i][j] != null) {
            return memo[i][j];
        }

        boolean valid = false;
        if (i < s1.length() && s1.charAt(i) == s3.charAt(k)) {
            valid = solveMemo(s1, i + 1, s2, j, s3, k + 1, memo);
        }
        if (!valid && j < s2.length() && s2.charAt(j) == s3.charAt(k)) {
            valid = solveMemo(s1, i, s2, j + 1, s3, k + 1, memo);
        }

        return memo[i][j] = valid;
    }

    /**
     * Main method for testing the recursive solution.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        InterleavingString solution = new InterleavingString();

        // Test Case 1: Valid interleaving
        String s1_1 = "aabcc", s2_1 = "dbbca", s3_1 = "aadbbcbcac";
        System.out.println("--- Test Case 1 ---");
        System.out.println("s1 = \"" + s1_1 + "\", s2 = \"" + s2_1 + "\", s3 = \"" + s3_1 + "\"");
        System.out.println("Recursive:   " + solution.isInterleave(s1_1, s2_1, s3_1)); // Expected: true
        System.out.println("Memoization: " + solution.isInterleaveMemo(s1_1, s2_1, s3_1)); // Expected: true
        System.out.println();

        // Test Case 2: Invalid interleaving
        String s1_2 = "aabcc", s2_2 = "dbbca", s3_2 = "aadbbbaccc";
        System.out.println("--- Test Case 2 ---");
        System.out.println("s1 = \"" + s1_2 + "\", s2 = \"" + s2_2 + "\", s3 = \"" + s3_2 + "\"");
        System.out.println("Recursive:   " + solution.isInterleave(s1_2, s2_2, s3_2)); // Expected: false
        System.out.println("Memoization: " + solution.isInterleaveMemo(s1_2, s2_2, s3_2)); // Expected: false
        System.out.println();
    }
}
