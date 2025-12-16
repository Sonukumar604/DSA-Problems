public class InOrder {
    public static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    /**
     * Performs In-order traversal on a binary tree using recursion.
     * The order is: Left -> Root -> Right.
     *
     * @param node The root node of the tree/subtree to traverse.
     */
    public static void inOrderRecursive(TreeNode node) {
        // Base case: If the node is null, we've reached the end of a branch.
        if (node == null) {
            return;
        }
        // 1. Traverse the left subtree
        inOrderRecursive(node.left);
        // 2. Visit the current node (Root)
        System.out.print(node.data + " ");
        // 3. Traverse the right subtree
        inOrderRecursive(node.right);
    }
    /**
     * Performs In-order traversal on a binary tree using an iterative approach with a Stack.
     * The order is: Left -> Root -> Right.
     *
     * @param root The root node of the tree to traverse.
     */
    public static void inOrderIterative(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentNode = root;
        while (currentNode != null || !stack.isEmpty()) {
            // Reach the leftmost node of the current node
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            // Current must be null at this point, so we pop the top node
            currentNode = stack.pop();
            // Visit the node
            System.out.print(currentNode.data + " ");
            // Now, we need to visit the right subtree
            currentNode = currentNode.right;
        }
    }
    public static void main(String[] args) {
        /*
         * Construct the following binary tree:
         *         1
         *        / \
         *       2   3
         *      / \
         *     4   5
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        System.out.println("In-order Traversal (Recursive):");
        inOrderRecursive(root);
        System.out.println("\nIn-order Traversal (Iterative):");
        inOrderIterative(root);
    }
}