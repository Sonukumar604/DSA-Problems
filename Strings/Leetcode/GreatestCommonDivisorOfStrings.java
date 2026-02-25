
public class GreatestCommonDivisorOfStrings {
    /**
     * Computes the greatest common divisor of two strings.
     * 
     * Approach:
     * 1. Check if the strings have a common pattern (str1 + str2 == str2 + str1).
     * 2. If they do, the length of the GCD string is the GCD of the lengths of str1 and str2.
     * 
     * Time Complexity: O(N + M)
     * - Concatenation and comparison take O(N + M).
     * - GCD calculation takes O(log(min(N, M))).
     * 
     * Space Complexity: O(N + M)
     * - For the concatenated strings used in the check.
     */
    public String gcdOfStrings(String str1, String str2) {
        if(!(str1 + str2).equals(str2 + str1)){
            return "";
        }
        int gcdLength = gcd(str1.length(), str2.length());
        return str1.substring(0, gcdLength);
    }
    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        GreatestCommonDivisorOfStrings solution = new GreatestCommonDivisorOfStrings();

        System.out.println("GCD of 'ABCABC' and 'ABC': " + solution.gcdOfStrings("ABCABC", "ABC")); // Output: ABC
        System.out.println("GCD of 'ABABAB' and 'ABAB': " + solution.gcdOfStrings("ABABAB", "ABAB")); // Output: AB
        System.out.println("GCD of 'LEET' and 'CODE': " + solution.gcdOfStrings("LEET", "CODE"));   // Output: ""
    }
}
