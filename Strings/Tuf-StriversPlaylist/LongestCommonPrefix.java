public class LongestCommonPrefix {
    /**
     * Finds the longest common prefix amongst an array of strings.
     * 
     * Approach: Vertical Scanning
     * We take the first string as a reference and iterate through its characters.
     * For each character at index 'i', we compare it with the character at index 'i' of all other strings.
     * If we find a mismatch or if we reach the end of any string, we return the prefix found so far.
     * 
     * Dry Run:
     * strs = {"flower", "flow", "flight"}
     * first = "flower"
     * 
     * i = 0, ch = 'f'
     *   j = 1 ("flow"): strs[1].charAt(0) == 'f'. Match.
     *   j = 2 ("flight"): strs[2].charAt(0) == 'f'. Match.
     * i = 1, ch = 'l'
     *   j = 1 ("flow"): strs[1].charAt(1) == 'l'. Match.
     *   j = 2 ("flight"): strs[2].charAt(1) == 'l'. Match.
     * i = 2, ch = 'o'
     *   j = 1 ("flow"): strs[1].charAt(2) == 'o'. Match.
     *   j = 2 ("flight"): strs[2].charAt(2) == 'i'. Mismatch. Return first.substring(0, 2) -> "fl".
     * 
     * Time Complexity: O(N * M) - Where N is the number of strings and M is the length of the shortest string.
     * Space Complexity: O(1) - We use constant extra space (excluding the result string).
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }
        String first = strs[0];
        for(int i = 0; i < first.length(); i++){
            char ch = first.charAt(i);
            for(int j = 1; j < strs.length; j++){
                if(i >= strs[j].length() || strs[j].charAt(i) != ch){
                    return first.substring(0, i);
                }
            }
        }
        return first;
    }
    public static void main(String[] args) {
        LongestCommonPrefix solution = new LongestCommonPrefix();

        String[] strs1 = {"flower", "flow", "flight"};
        System.out.println("Longest Common Prefix: " + solution.longestCommonPrefix(strs1)); // Expected: "fl"

        String[] strs2 = {"dog", "racecar", "car"};
        System.out.println("Longest Common Prefix: " + solution.longestCommonPrefix(strs2)); // Expected: ""

        String[] strs3 = {"interview", "intermediate", "internal"};
        System.out.println("Longest Common Prefix: " + solution.longestCommonPrefix(strs3)); // Expected: "inter"
    }
}
