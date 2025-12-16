import java.util.Stack;

public class PreOrder {

    // 1. Define the structure of a tree node
    static class TreeNode {
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
     * Performs Pre-order traversal on a binary tree using recursion.
     * The order is: Root -> Left -> Right.
     *
     * @param node The root node of the tree/subtree to traverse.
     */
    public static void preOrderRecursive(TreeNode node) {
        // Base case: If the node is null, we've reached the end of a branch.
        if (node == null) {
            return;
        }

        // 1. Visit the current node (Root)
        System.out.print(node.data + " ");

        // 2. Traverse the left subtree
        preOrderRecursive(node.left);

        // 3. Traverse the right subtree
        preOrderRecursive(node.right);
    }
    /**
     * Performs Pre-order traversal on a binary tree using an iterative approach with a Stack.
     * The order is: Root -> Left -> Right.
     *
     * @param root The root node of the tree to traverse.
     */
    public static void preOrderIterative(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        // Continue until there are no more nodes to process
        while (!stack.isEmpty()) {
            // Pop a node and process it
            TreeNode currentNode = stack.pop();
            System.out.print(currentNode.data + " ");

            // IMPORTANT: Push the right child first, so the left child is processed first (LIFO).
            if (currentNode.right != null) {
                stack.push(currentNode.right);
            }
            if (currentNode.left != null) {
                stack.push(currentNode.left);
            }
        }
    }

    public static void main(String[] args) {
        /*
         * Construct the following binary tree:
         *        1
         *       / \
         *      2   3
         *     / \
         *    4   5
         *
         * Expected Pre-order Traversal: 1 -> 2 -> 4 -> 5 -> 3
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("Pre-order Traversal (Recursive):");
        preOrderRecursive(root);
        System.out.println(); // For a new line

        System.out.println("\nPre-order Traversal (Iterative):");
        preOrderIterative(root);
        System.out.println(); // For a new line
    }
    
}
