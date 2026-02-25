import java.util.Stack;

public class SumRootToLeaf {
    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Calculates the sum of root-to-leaf binary numbers.
     * 
     * Approach: DFS (Preorder Traversal)
     * We traverse the tree from the root to the leaves. As we move down, we construct the binary number.
     * Moving to a child is equivalent to shifting the current binary number to the left by 1 bit (multiplying by 2)
     * and adding the child's value.
     * 
     * Formula: current_val = (previous_val * 2) + node.val
     * OR using bitwise operators: current_val = (previous_val << 1) | node.val
     * 
     * Time Complexity: O(N) - We visit every node exactly once.
     * Space Complexity: O(H) - The recursion stack depth is equal to the height of the tree (H).
     *                        In the worst case (skewed tree), H = N. In a balanced tree, H = log N.
     * 
     * Dry Run 1:
     * Input: root = [1, 0, 1]
     *        1
     *       / \
     *      0   1
     * 
     * 1. dfs(Node(1), 0) -> current = 1
     * 2. Left: dfs(Node(0), 1) -> current = (1<<1)|0 = 2 (Binary 10). Leaf -> Return 2.
     * 3. Right: dfs(Node(1), 1) -> current = (1<<1)|1 = 3 (Binary 11). Leaf -> Return 3.
     * 4. Total Sum = 2 + 3 = 5.
     * 
     * Dry Run 2:
     * Input: root = [1, 1]
     *        1
     *       /
     *      1
     * 
     * 1. dfs(Node(1), 0) -> current = 1
     * 2. Left: dfs(Node(1), 1) -> current = (1<<1)|1 = 3 (Binary 11). Leaf -> Return 3.
     * 3. Right: dfs(null, 1) -> Return 0.
     * 4. Total Sum = 3 + 0 = 3.
     * 
     * Dry Run 3:
     * Input: root = [1, 0, 1, 0, 1, 0]
     *        1
     *       / \
     *      0   1
     *     / \ /
     *    0  1 0
     * 
     * 1. dfs(Node(1), 0) -> current = 1
     * 2. Left Subtree (Node 0):
     *    - dfs(Node(0), 1) -> current = 2 (10)
     *    - Left: dfs(Node(0), 2) -> current = 4 (100). Leaf -> Return 4.
     *    - Right: dfs(Node(1), 2) -> current = 5 (101). Leaf -> Return 5.
     *    - Sum = 4 + 5 = 9.
     * 3. Right Subtree (Node 1):
     *    - dfs(Node(1), 1) -> current = 3 (11)
     *    - Left: dfs(Node(0), 3) -> current = 6 (110). Leaf -> Return 6.
     *    - Right: null -> Return 0.
     *    - Sum = 6.
     * 4. Total Sum = 9 + 6 = 15.
     */
    public int sumRootToLeafRecursive(TreeNode root) {
        return dfs(root, 0);
    }

    /**
     * Iterative Approach using Stacks.
     * 
     * Time Complexity: O(N)
     * Space Complexity: O(H)
     */
    public int sumRootToLeafIterative(TreeNode root) {
        if (root == null) return 0;

        int totalSum = 0;
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> valStack = new Stack<>();

        nodeStack.push(root);
        valStack.push(0);

        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int currentVal = valStack.pop();

            // Shift left and add current node's bit
            currentVal = (currentVal << 1) | node.val;

            if (node.left == null && node.right == null) {
                totalSum += currentVal;
            }

            if (node.right != null) {
                nodeStack.push(node.right);
                valStack.push(currentVal);
            }
            if (node.left != null) {
                nodeStack.push(node.left);
                valStack.push(currentVal);
            }
        }
        return totalSum;
    }

    private int dfs(TreeNode node, int current) {
        if (node == null) {
            return 0;
        }

        // Shift left and add current node's bit
        current = (current << 1) | node.val;

        // If leaf node, return the formed number
        if (node.left == null && node.right == null) {
            return current;
        }

        // Otherwise, sum left and right subtrees
        return dfs(node.left, current) + dfs(node.right, current);
    }

    public static void main(String[] args) {
        SumRootToLeaf solution = new SumRootToLeaf();

        // Test Case 1: [1,0,1] -> 10(2) + 11(3) = 5
        TreeNode root1 = new TreeNode(1, new TreeNode(0), new TreeNode(1));
        System.out.println("Sum Recursive (Test Case 1): " + solution.sumRootToLeafRecursive(root1)); // Expected: 5
        System.out.println("Sum Iterative (Test Case 1): " + solution.sumRootToLeafIterative(root1)); // Expected: 5

        // Test Case 2: [1,1] -> 11(3) = 3
        TreeNode root2 = new TreeNode(1, new TreeNode(1), null);
        System.out.println("Sum Recursive (Test Case 2): " + solution.sumRootToLeafRecursive(root2)); // Expected: 3
        System.out.println("Sum Iterative (Test Case 2): " + solution.sumRootToLeafIterative(root2)); // Expected: 3

        // Test Case 3: [1, 0, 1, 0, 1, 0] -> 100(4) + 101(5) + 110(6) = 15
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(0);
        root3.right = new TreeNode(1);
        root3.left.left = new TreeNode(0);
        root3.left.right = new TreeNode(1);
        root3.right.left = new TreeNode(0);
        System.out.println("Sum Recursive (Test Case 3): " + solution.sumRootToLeafRecursive(root3)); // Expected: 15
        System.out.println("Sum Iterative (Test Case 3): " + solution.sumRootToLeafIterative(root3)); // Expected: 15
    }
}
