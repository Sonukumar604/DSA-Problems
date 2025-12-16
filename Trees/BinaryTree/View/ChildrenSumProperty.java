public class ChildrenSumProperty {
    public static void changeTree(Node root){
        if(root == null) return;

        // Calculate the sum of children's data
        int childSum = 0;
        if(root.left != null) childSum += root.left.data;
        if(root.right != null) childSum += root.right.data;

        // If the node's data is greater than the sum of its children,
        // update the children's data to match the parent's.
        // This ensures we can only increment values to satisfy the property.
        if(childSum < root.data){
            if(root.left != null) root.left.data = root.data;
            if(root.right != null) root.right.data = root.data;
        }

        // Recur for left and right subtrees
        changeTree(root.left);
        changeTree(root.right);

        // After the recursive calls return (bottom-up), update the parent's data
        // to be the sum of its (potentially updated) children.
        // This is skipped for leaf nodes.
        int finalChildSum = 0;
        if(root.left != null) finalChildSum += root.left.data;
        if(root.right != null) finalChildSum += root.right.data;
        if(root.left != null || root.right != null){
            root.data = finalChildSum;
        }
    }
    static class Node{
        int data;
        Node left, right;
        Node(int data){
            this.data = data;
            left = right = null;
        }
    }
    public static void main(String[] args) {
        Node root = new Node(2);
        root.left = new Node(35);
        root.right = new Node(10);
        root.left.left = new Node(2);
        root.left.right = new Node(3);
        root.right.right = new Node(5);
        root.right.left = new Node(2);

        // The original logic would give root.data = 40, which is incorrect.
        // The sum of its children would be 38 + 10 = 48.
        changeTree(root);

        // After correction, the final tree will satisfy the children sum property at every node.
        // The root's value will be the sum of its children's final values.
        // Expected: root.left becomes 70, root.right becomes 10. root becomes 80.
        System.out.println("Root after applying Children Sum Property: " + root.data);
    }
}
