
import java.util.Stack;

public class TwoSumBSTIterator{
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    static class BSTIterator {
        private Stack<TreeNode> stack = new Stack<>();
        private boolean reverse; // false = inorder, true = reverse inorder

        public BSTIterator(TreeNode root, boolean reverse) {
            this.reverse = reverse;
            pushAll(root);
        }

        private void pushAll(TreeNode node) {
            while (node != null) {
                stack.push(node);
                if (reverse)
                    node = node.right;
                else
                    node = node.left;
            }
        }

        public int next() {
            TreeNode node = stack.pop();
            if (reverse)
                pushAll(node.left);
            else
                pushAll(node.right);
            return node.val;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }

    public static boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;

        BSTIterator l = new BSTIterator(root, false); // smallest → largest
        BSTIterator r = new BSTIterator(root, true);  // largest → smallest

        int i = l.next();
        int j = r.next();

        while (i < j) {
            if (i + j == k)
                return true;
            else if (i + j < k)
                i = l.next();
            else
                j = r.next();
        }
        return false;
    }

    // ---------- main for testing ----------
    public static void main(String[] args) {
        /*
                 7
               /   \
              3     10
             / \      \
            2   6      11
               /
              5
        */

        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(6);
        root.left.right.left = new TreeNode(5);
        root.right.right = new TreeNode(11);

        int k = 9; // 3 + 6 = 9
        System.out.println(findTarget(root, k)); // true
    }
}