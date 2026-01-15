public class CheckTreeBSTorBT {
    public static boolean isBSTUtil(TreeNode node, Integer min, Integer max){
        if(node == null){
            return true;
        }
        if((min != null && node.val <= min) || (max != null && node.val >= max)){
            return false;
        }
        return isBSTUtil(node.left, min, node.val) && isBSTUtil(node.right, node.val, max);
    }
    public static boolean isBinarySearchTree(TreeNode root){
        return isBSTUtil(root, null, null);
    }
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
    public static void main(String[] args) {
        // Creating a sample Binary Search Tree:
        //      5
        //     / \
        //    3   7
        //   / \   \
        //  2   4   8
        TreeNode rootBST = new TreeNode(5);
        rootBST.left = new TreeNode(3);
        rootBST.right = new TreeNode(7);
        rootBST.left.left = new TreeNode(2);
        rootBST.left.right = new TreeNode(4);
        rootBST.right.right = new TreeNode(8);

        // Creating a sample Binary Tree that is NOT a BST:
        //      5
        //     / \
        //    3   7
        //   / \   \
        //  6   4   8
        TreeNode rootBT = new TreeNode(5);
        rootBT.left = new TreeNode(3);
        rootBT.right = new TreeNode(7);
        rootBT.left.left = new TreeNode(6); // Violates BST property
        rootBT.left.right = new TreeNode(4);
        rootBT.right.right = new TreeNode(8);

        System.out.println("Is the first tree a BST? " + isBinarySearchTree(rootBST)); // Expected: true
        System.out.println("Is the second tree a BST? " + isBinarySearchTree(rootBT)); // Expected: false
    }
}
