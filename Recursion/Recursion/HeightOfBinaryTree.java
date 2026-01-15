class Node{
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
public class HeightOfBinaryTree{
    public static int height(Node root){
        //Base case
        if(root == null) return 0;
        //Hypothesis
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        //Induction
        return Math.max(leftHeight, rightHeight) + 1;
    }
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        System.out.println("Height of the tree :" + height(root));
    }
}
