public class TimeComplexityOfRecursiveTree {
    /**
     * This function demonstrates a recursive pattern where each call generates two more calls.
     * The recurrence relation is T(n) = 2 * T(n-1) + c, where 'c' is constant work.
     *
     * Time Complexity: O(2^n)
     * - The number of nodes in the recursion tree grows exponentially.
     * - For n=3, the calls are: T(3) -> 2*T(2) -> 4*T(1).
     * - The total number of operations is proportional to 1 + 2 + 4 + ... + 2^(n-1), which is a geometric series
     *   that sums to 2^n - 1. Therefore, the complexity is O(2^n).
     *
     * Space Complexity: O(n)
     * - This is determined by the maximum depth of the recursion stack.
     * - The function calls itself with `n-1` until `n` reaches 1. The maximum depth of the stack
     *   will be `n`.
     *
     * @param n The input integer.
     * @return The total number of nodes in the conceptual recursion tree.
     */
    public static int recursiveTree(int n){
        if(n<=1) return 1;
        // This line represents the recurrence: T(n) = 1 (current node) + 2 * T(n-1) (two children)
        return 1 + 2 * recursiveTree(n-1);
    }

    public static void main(String[] args){
        int n = 3;
        int totalNodes = recursiveTree(n);
        System.out.println("Total nodes in the recursive tree for n = " + n + " is: " + totalNodes);

        // The function actually calculates the sum of nodes in a full binary tree of height (n-1),
        // which is 2^n - 1. The original formula was incorrect.
        int formulaNodes = (int)(Math.pow(2, n) - 1);
        System.out.println("Total nodes by formula (2^n - 1): " + formulaNodes);
    }
}
