public class MergeStringsAlternately {

    // Merges two strings by adding letters in alternating order.
    // Uses Two Pointers approach.
    public static String mergeAlternately(String word1, String word2) {
        StringBuilder result = new StringBuilder();
        int i = 0; // Pointer for word1
        int j = 0; // Pointer for word2
        
        while (i < word1.length() || j < word2.length()) {
            if (i < word1.length()) {
                result.append(word1.charAt(i++));
            }
            if (j < word2.length()) {
                result.append(word2.charAt(j++));
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(mergeAlternately("abc", "pqr")); // "apbqcr"
        System.out.println(mergeAlternately("ab", "pqrs")); // "apbqrs"
    }
}