/**
 * Removes the outermost parentheses of every primitive valid parentheses string in the decomposition of S.
 *
 * ### Explanation
 * A primitive valid parentheses string is a non-empty string that cannot be split into two non-empty valid parentheses strings.
 * For example, "(()())" is primitive, but "()()" is not (it's "()" + "()").
 *
 * The core idea is to track the nesting depth of the parentheses. We use a counter, `depth`.
 * 1. We iterate through the input string character by character.
 * 2. When we encounter an opening parenthesis `(`:
 *    - If the current `depth` is greater than 0, it means this `(` is not the outermost one of a primitive component. So, we append it to our result.
 *    - We then increment the `depth` to signify we've gone one level deeper.
 * 3. When we encounter a closing parenthesis `)`:
 *    - We first decrement the `depth` because we are moving out one level.
 *    - If the new `depth` is still greater than 0, it means this `)` is not the outermost one. So, we append it to our result.
 *
 * By only appending characters when `depth > 0`, we effectively ignore the first opening parenthesis (when `depth` is 0) and the last closing parenthesis (which makes `depth` become 0) of each primitive component.
 *
 * ### Dry Run
 * Input: `s = "(()())(())"`
 * Expected Output: `"()()()"`
 *
 * Initialize `result = ""` and `depth = 0`.
 *
 * - **i=0, ch='('**: `depth` is 0. Don't append. `depth` becomes 1.
 * - **i=1, ch='('**: `depth` is 1 (>0). Append '('. `result` is "(". `depth` becomes 2.
 * - **i=2, ch=')'**: `depth` becomes 1. `depth` is 1 (>0). Append ')'. `result` is "()".
 * - **i=3, ch='('**: `depth` is 1 (>0). Append '('. `result` is "()(". `depth` becomes 2.
 * - **i=4, ch=')'**: `depth` becomes 1. `depth` is 1 (>0). Append ')'. `result` is "()()".
 * - **i=5, ch=')'**: `depth` becomes 0. `depth` is not >0. Don't append. (End of first primitive part)
 * - **i=6, ch='('**: `depth` is 0. Don't append. `depth` becomes 1.
 * - **i=7, ch='('**: `depth` is 1 (>0). Append '('. `result` is "()()(". `depth` becomes 2.
 * - **i=8, ch=')'**: `depth` becomes 1. `depth` is 1 (>0). Append ')'. `result` is "()()()".
 * - **i=9, ch=')'**: `depth` becomes 0. `depth` is not >0. Don't append. (End of second primitive part)
 *
 * Final result: "()()()"
 *
 * ### Complexity
 * - **Time Complexity:** O(N), where N is the length of the string. We iterate through the string once.
 * - **Space Complexity:** O(N), for the `StringBuilder` which can store up to N characters in the worst case.
 */
public class RemoveOuterMostParanthesis {
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
        RemoveOuterMostParanthesis solution = new RemoveOuterMostParanthesis();
        String s1 = "(()())(())";
        System.out.println("Input: " + s1 + " -> Output: " + solution.removeOuterParentheses(s1)); // Expected: "()()()"
        String s2 = "(()())(())(()(()))";
        System.out.println("Input: " + s2 + " -> Output: " + solution.removeOuterParentheses(s2)); // Expected: "()()()()(())"
        String s3 = "()()";
        System.out.println("Input: " + s3 + " -> Output: " + solution.removeOuterParentheses(s3)); // Expected: ""
    }
}
