public class GcdOfStrings {

    // Solves the Greatest Common Divisor of Strings problem.
    // Approach: Mathematical (Euclidean Algorithm for lengths).
    // Time Complexity: O(N + M) due to string concatenation checks.
    // Space Complexity: O(N + M) for the concatenated strings.
    //
    // Dry Run Example:
    // Input: str1 = "ABABAB", str2 = "ABAB"
    // 1. Check: (str1 + str2).equals(str2 + str1)
    //    "ABABABABAB".equals("ABABABABAB") -> true
    // 2. GCD Length: gcd(6, 4) -> 2
    // 3. Result: str1.substring(0, 2) -> "AB"
    public static String gcdOfStrings(String str1, String str2) {
        // 1. Check if they have a common pattern at all.
        // If s1 + s2 != s2 + s1, no common divisor exists.
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }

        // 2. The length of the GCD string is the GCD of the lengths of str1 and str2.
        int gcdLength = gcd(str1.length(), str2.length());

        // 3. Return the prefix of that length.
        return str1.substring(0, gcdLength);
    }

    // Helper method to find GCD of two numbers
    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        System.out.println(gcdOfStrings("ABCABC", "ABC")); // Output: "ABC"
        System.out.println(gcdOfStrings("ABABAB", "ABAB")); // Output: "AB"
        System.out.println(gcdOfStrings("LEET", "CODE"));   // Output: ""
    }
}