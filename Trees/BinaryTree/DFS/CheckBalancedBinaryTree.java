public class CheckBalancedBinaryTree {
    int checkBalance(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = checkBalance(root.left);
        if (leftHeight == -1) {
            return -1; // Left subtree is unbalanced
        }

        int rightHeight = checkBalance(root.right);
        if (rightHeight == -1) {
            return -1; // Right subtree is unbalanced
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1; // Current node is unbalanced
        }

        return Math.max(leftHeight, rightHeight) + 1; // Return height if balanced
    }
    boolean isBalanced(TreeNode root) {
        return checkBalance(root) != -1;
    }
    public static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        root.data = 1;
        root.left = new TreeNode();
        root.left.data = 2;
        root.right = new TreeNode();
        root.right.data = 3;
        root.left.left = new TreeNode();
        root.left.left.data = 4;
        root.left.right = new TreeNode();
        root.left.right.data = 5;
        CheckBalancedBinaryTree treeChecker = new CheckBalancedBinaryTree();
        System.out.println("Is the binary tree balanced? " + treeChecker.isBalanced(root));
    }
}
class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;
}