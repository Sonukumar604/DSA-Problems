/*
 * Problem: Flatten Binary Tree to Linked List
 * Approach: Morris Traversal (O(1) Space)
 * Traversal: Preorder (Root → Left → Right)
 * Time Complexity: O(N)
 * Space Complexity: O(1)
 *
 * Source: Striver DSA Tree Series (L38)
 */

public class MorrisFlattenABinaryTreeToLinkedList {
    public void flatten(TreeNode root) {
    TreeNode cur = root;
    while(cur != null){
        if(cur.left != null){
            TreeNode prev = cur.left;
            while(prev.right != null){
                prev = prev.right;
            }
            prev.right = cur.right;
            cur.right = cur.left;
            cur.left = null;
        }
        cur = cur.right;
    }
    }
    public static void main(String[] args) {
        MorrisFlattenABinaryTreeToLinkedList flattener = new MorrisFlattenABinaryTreeToLinkedList();
        TreeNode root = new TreeNode();
        root.val = 1;
        root.left = new TreeNode();
        root.left.val = 2;
        root.right = new TreeNode();
        root.right.val = 5;
        root.left.left = new TreeNode();
        root.left.left.val = 3;
        root.left.right = new TreeNode();
        root.left.right.val = 4;
        root.right.right = new TreeNode();
        root.right.right.val = 6;

        flattener.flatten(root);

        // Print flattened tree
        TreeNode cur = root;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
        // Expected output: 1 2 3 4 5 6
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
}
