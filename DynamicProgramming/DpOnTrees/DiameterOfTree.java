class Node{
    int data;
    Node left;
    Node right;
    Node(int value){
        data = value;
        left = right = null;
    }
}
public class DiameterOfTree{
    int result;
    int solve(Node root){
        if(root == null) return 0;

        int left = solve(root.left);
        int right = solve(root.right);
        int temp = 1 + Math.max(left, right);
        int ans = Math.max(temp, 1 + left + right);
        result = Math.max(result, ans);
        return temp;
    }
    int diameter (Node root){
        result = Integer.MIN_VALUE;
        // The initial call to solve will populate the 'result' field.
        // We need to handle the case of a single node tree, where solve returns 1 but diameter is 0.
        // The logic inside solve correctly calculates diameter, so we just need to call it.
        solve(root);
        // The result is stored in the instance variable 'result'.
        // If the tree has only one node, the diameter is 0.
        return result == Integer.MIN_VALUE ? 0 : result -1;
    }
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        DiameterOfTree tree = new DiameterOfTree();
        int res = tree.diameter(root);
        System.out.println("Diameter of tree: " + res); // Expected: 3 (path 4-2-1-3)
    }
}
