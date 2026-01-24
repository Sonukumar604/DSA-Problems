import java.util.Stack;

/**
 * Implements an iterator for a Binary Search Tree (BST) that performs an in-order traversal.
 * This allows iterating through the nodes of the BST from the smallest to the largest value.
 *
 * Time Complexity:
 * - next(): Amortized O(1). Each node is pushed and popped once over the entire traversal.
 * - hasNext(): O(1).
 *
 * Space Complexity: O(H), where H is the height of the tree. This is the space used by the stack.
 */
public class BSTIterator {

    // TreeNode class definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            left = right = null;
        }
    }

    private Stack<TreeNode> stack = new Stack<>();

    /**
     * Constructor to initialize the iterator.
     * It pushes the root and all its left children to the stack to start at the smallest element.
     * @param root The root of the BST.
     */
    public BSTIterator(TreeNode root) {
        pushLeft(root);
    }

    /**
     * Helper method to push a node and all its left children onto the stack.
     * @param node The starting node.
     */
    private void pushLeft(TreeNode node){
        while(node != null){
            stack.push(node);
            node = node.left;
        }
    }

    /**
     * @return the next smallest number in the BST.
     */
    public int next(){
        TreeNode node = stack.pop();
        // After visiting a node, we must process its right subtree.
        if(node.right != null){
            pushLeft(node.right);
        }
        return node.val;
    }

    /**
     * @return whether we have a next smallest number.
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);

        BSTIterator iterator = new BSTIterator(root);
        System.out.println("In-order traversal using BSTIterator:");
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " "); // Expected output: 3 7 9 15 20
        }
    }
}
