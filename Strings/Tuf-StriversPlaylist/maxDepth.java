public class maxDepth {
    /**
     * Calculates the maximum nesting depth of the parentheses.
     *
     * ### Explanation
     * The depth of a valid parentheses string is the maximum number of open parentheses at any given point.
     * We can track this by iterating through the string:
     * 1. Initialize `depth` to 0 to track the current nesting level.
     * 2. Initialize `maxDepth` to 0 to track the highest depth encountered.
     * 3. Loop through each character:
     *    - If '(', increment `depth` and update `maxDepth` with the larger of `maxDepth` or `depth`.
     *    - If ')', decrement `depth`.
     *    - Ignore other characters.
     *
     * ### Dry Run
     * Input: `s = "(1+(2*3)+((8)/4))+1"`
     * - Start: `depth`=0, `maxDepth`=0
     * - `(`: `depth` becomes 1. `maxDepth` becomes 1.
     * - `1`, `+`: Ignored.
     * - `(`: `depth` becomes 2. `maxDepth` becomes 2.
     * - `2`, `*`, `3`: Ignored.
     * - `)`: `depth` becomes 1.
     * - `+`: Ignored.
     * - `(`: `depth` becomes 2.
     * - `(`: `depth` becomes 3. `maxDepth` becomes 3.
     * - `8`: Ignored.
     * - `)`: `depth` becomes 2.
     * - `/`, `4`: Ignored.
     * - `)`: `depth` becomes 1.
     * - `)`: `depth` becomes 0.
     * Result: 3
     *
     * ### Complexity
     * - **Time Complexity:** O(N), where N is the length of the string.
     * - **Space Complexity:** O(1), using constant extra space.
     */
    public int maxDepth(String s){
        int depth = 0, maxDepth = 0;
        for(char ch : s.toCharArray()){
            if(ch == '('){
                depth++;
                maxDepth = Math.max(maxDepth, depth);
            } else if(ch == ')'){
                depth--;
            }
        }
        return maxDepth;
    }

    public static void main(String[] args) {
        maxDepth solution = new maxDepth();

        String s1 = "(1+(2*3)+((8)/4))+1";
        System.out.println("Input: \"" + s1 + "\" -> Max Depth: " + solution.maxDepth(s1));

        String s2 = "(1)+((2))+(((3)))";
        System.out.println("Input: \"" + s2 + "\" -> Max Depth: " + solution.maxDepth(s2));
    }
}
