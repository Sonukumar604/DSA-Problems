import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

public class PostOrder {

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
     * Performs Post-order traversal on a binary tree using recursion.
     * The order is: Left -> Right -> Root.
     *
     * @param node The root node of the tree/subtree to traverse.
     */
    public static void postOrderRecursive(TreeNode node) {
        // Base case: If the node is null, we've reached the end of a branch.
        if (node == null) {
            return;
        }

        // 1. Traverse the left subtree
        postOrderRecursive(node.left);

        // 2. Traverse the right subtree
        postOrderRecursive(node.right);

        // 3. Visit the current node (Root)
        System.out.print(node.data + " ");
    }

    /**
     * Performs Post-order traversal on a binary tree using an iterative approach with two stacks.
     * This approach is intuitive and easy to follow.
     *
     * @param root The root node of the tree to traverse.
     */
    public static void postOrderIterativeTwoStacks(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        stack1.push(root);

        // This loop effectively creates a reversed Pre-order traversal (Root -> Right -> Left) in stack2.
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);

            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }

        // Pop all elements from stack2 to get the Post-order traversal.
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().data + " ");
        }
    }
    public static void postOrderIterativeOneStack(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        LinkedList<Integer> result = new LinkedList<>();
        stack.push(root);

        // This loop creates a reversed Post-order traversal (Root -> Right -> Left) in the result list.
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.addFirst(node.data);

            // Push left and right children to the stack
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }

        // Print the result
        for (int value : result) {
            System.out.print(value + " ");
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
         * Expected Post-order Traversal: 4 -> 5 -> 2 -> 3 -> 1
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("Post-order Traversal (Recursive):");
        postOrderRecursive(root);
        System.out.println(); // For a new line

        System.out.println("\nPost-order Traversal (Iterative with 2 Stacks):");
        postOrderIterativeTwoStacks(root);
        System.out.println(); // For a new line
    }
}