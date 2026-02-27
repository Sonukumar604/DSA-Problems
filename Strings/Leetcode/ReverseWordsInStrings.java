
public class ReverseWordsInStrings {
    /**
     * Reverses the order of words in a given string.
     * 
     * Approach: Built-in String Methods
     * 1. Trim the input string to remove leading and trailing spaces.
     * 2. Split the string by one or more spaces ("\\s+") to get individual words.
     * 3. Iterate through the words array in reverse order and append them to a StringBuilder.
     * 
     * Dry Run 1:
     * Input: s = "the sky is blue"
     * 1. s.trim() -> "the sky is blue"
     * 2. split("\\s+") -> words = ["the", "sky", "is", "blue"]
     * 3. Loop starts from end:
     *    - i=3: append "blue", append " " -> sb: "blue "
     *    - i=2: append "is", append " " -> sb: "blue is "
     *    - i=1: append "sky", append " " -> sb: "blue is sky "
     *    - i=0: append "the" -> sb: "blue is sky the"
     * 4. Return "blue is sky the"
     * 
     * Dry Run 2:
     * Input: s = "  hello world  "
     * 1. s.trim() -> "hello world"
     * 2. split("\\s+") -> words = ["hello", "world"]
     * 3. Loop starts from end:
     *    - i=1: append "world", append " " -> sb: "world "
     *    - i=0: append "hello" -> sb: "world hello"
     * 4. Return "world hello"
     * 
     * Time Complexity: O(N) - Splitting and rebuilding the string takes linear time relative to the string length.
     * Space Complexity: O(N) - To store the array of words and the resulting string.
     */
    public String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);
            if (i != 0) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ReverseWordsInStrings solution = new ReverseWordsInStrings();

        System.out.println("Reverse words of 'the sky is blue': \"" + solution.reverseWords("the sky is blue") + "\""); // Expected: "blue is sky the"
        System.out.println("Reverse words of '  hello world  ': \"" + solution.reverseWords("  hello world  ") + "\""); // Expected: "world hello"
        System.out.println("Reverse words of 'a good   example': \"" + solution.reverseWords("a good   example") + "\""); // Expected: "example good a"
    }
}
