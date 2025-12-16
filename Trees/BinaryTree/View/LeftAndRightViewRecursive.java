public class LeftAndRightViewRecursive {

    /**
     * A static inner class to represent a node in the binary tree.
     */
    static class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }
    int maxLevelLeft = 0;
    void leftViewUtil(Node node, int level) {
        // Base Case
        if (node == null) {
            return;
        }
        // If this is the first node of its level
        if (level > maxLevelLeft) {
            System.out.print(node.data + " ");
            maxLevelLeft = level;
        }
        // Recur for left and right subtrees
        leftViewUtil(node.left, level + 1);
        leftViewUtil(node.right, level + 1);
    }
    int maxLevelRight = 0;
    void rightViewUtil(Node node, int level) {
        // Base Case
        if (node == null) {
            return;
        }
        // If this is the first node of its level
        if (level > maxLevelRight) {
            System.out.print(node.data + " ");
            maxLevelRight = level;
        }
        // Recur for right and left subtrees
        rightViewUtil(node.right, level + 1);
        rightViewUtil(node.left, level + 1);
    }
    void leftView(Node root) {
        leftViewUtil(root, 1);
    }
    void rightView(Node root) {
        rightViewUtil(root, 1);
    }
    public static void main(String[] args) {
        LeftAndRightViewRecursive tree = new LeftAndRightViewRecursive();
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.right = new Node(4);
        root.left.right.right = new Node(5);
        root.left.right.right.right = new Node(6);

        System.out.print("Left view: ");
        tree.leftView(root);
        System.out.println();

        System.out.print("Right view: ");
        tree.rightView(root);
        System.out.println();
    }
}