public class RemoveParanthesis {
    /**
     * Removes the outermost parentheses of every primitive string in the valid parenthesis string decomposition.
     * 
     * Approach: Counting/Depth Tracking
     * We maintain a counter `depth` to track the balance of open parentheses.
     * - When we encounter '(', if `depth > 0`, it means this is not an outermost parenthesis, so we append it. Then we increment `depth`.
     * - When we encounter ')', we decrement `depth`. If `depth > 0` after decrementing, it means this is not an outermost parenthesis, so we append it.
     * 
     * Dry Run 1:
     * Input: s = "(()())(())"
     * 
     * 1. ch='(': depth=0. Skip. depth->1.
     * 2. ch='(': depth=1 > 0. Append '('. depth->2. Res: "("
     * 3. ch=')': depth->1 > 0. Append ')'. Res: "()"
     * 4. ch='(': depth=1 > 0. Append '('. depth->2. Res: "()("
     * 5. ch=')': depth->1 > 0. Append ')'. Res: "()()"
     * 6. ch=')': depth->0. Skip. Res: "()()"
     * -- First primitive "(()())" processed to "()()" --
     * 7. ch='(': depth=0. Skip. depth->1.
     * 8. ch='(': depth=1 > 0. Append '('. depth->2. Res: "()()("
     * 9. ch=')': depth->1 > 0. Append ')'. Res: "()()()"
     * 10. ch=')': depth->0. Skip. Res: "()()()"
     * Final Result: "()()()"
     * 
     * Dry Run 2:
     * Input: s = "(()())"
     * 
     * 1. ch='(': depth=0. Skip. depth->1.
     * 2. ch='(': depth=1 > 0. Append '('. depth->2. Res: "("
     * 3. ch=')': depth->1 > 0. Append ')'. Res: "()"
     * 4. ch='(': depth=1 > 0. Append '('. depth->2. Res: "()("
     * 5. ch=')': depth->1 > 0. Append ')'. Res: "()()"
     * 6. ch=')': depth->0. Skip.
     * Final Result: "()()"
     * 
     * Time Complexity: O(N) - We iterate through the string once.
     * Space Complexity: O(N) - To store the result in StringBuilder.
     */
    public String removeOuterParentheses(String s) {
        
        StringBuilder result = new StringBuilder();
        int depth = 0;
        
        for (char ch : s.toCharArray()) {
            
            if (ch == '(') {
                if (depth > 0) {
                    result.append(ch);
                }
                depth++;
            } else { // ')'
                depth--;
                if (depth > 0) {
                    result.append(ch);
                }
            }
        }
        
        return result.toString();
    }

    public static void main(String[] args) {
        RemoveParanthesis solution = new RemoveParanthesis();

        String s1 = "(()())(())";
        System.out.println("Input: \"" + s1 + "\" | Output: \"" + solution.removeOuterParentheses(s1) + "\""); // Expected: "()()()"

        String s2 = "(()())(())(()(()))";
        System.out.println("Input: \"" + s2 + "\" | Output: \"" + solution.removeOuterParentheses(s2) + "\""); // Expected: "()()()()(())"

        String s3 = "()()";
        System.out.println("Input: \"" + s3 + "\" | Output: \"" + solution.removeOuterParentheses(s3) + "\""); // Expected: ""
    }
}