
public class MergeStringsAlternatively {
    /**
     * Merges two strings by adding letters in alternating order.
     * 
     * Approach: Two Pointers
     * We iterate through both strings simultaneously using two pointers.
     * 
     * Time Complexity: O(N + M) - We iterate through both strings once, where N and M are their lengths.
     * Space Complexity: O(N + M) - To store the resulting merged string.
     */
    public String mergeAlternately(String word1, String word2) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        int j = 0;
        while(i < word1.length() || j < word2.length()){
            if(i < word1.length()){
                result.append(word1.charAt(i++));
            }
            if(j < word2.length()){
                result.append(word2.charAt(j++));
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        MergeStringsAlternatively solution = new MergeStringsAlternatively();

        // Test cases
        System.out.println("Merged 'abc' and 'pqr': " + solution.mergeAlternately("abc", "pqr")); // Expected: apbqcr
        System.out.println("Merged 'ab' and 'pqrs': " + solution.mergeAlternately("ab", "pqrs")); // Expected: apbqrs
        System.out.println("Merged 'abcd' and 'pq': " + solution.mergeAlternately("abcd", "pq")); // Expected: apbqcd
    }
}
