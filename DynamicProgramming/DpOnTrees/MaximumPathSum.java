class Node{
    int data;
    Node left, right;
    Node(int value){
        data = value;
        left = right = null;
    }
}
public class MaximumPathSum {
    int result;
    int solve(Node root){
        if(root == null)return 0;
        int left = Math.max(0, solve(root.left));
        int right = Math.max(0, solve(root.right));
        int temp = root.data + Math.max(left, right);
        int ans = root.data + left + right;
        result = Math.max(result, ans);
        return temp;
    }
    int maxPathSum(Node root){
        result = Integer.MIN_VALUE;
        solve(root);
        return result;
    }
    public static void main(String[] args) {
        Node root = new Node(-10);
        root.left = new Node(9);
        root.right = new Node(20);
        root.right.left = new Node(15);
        root.right.right = new Node(7);
 
        MaximumPathSum s = new MaximumPathSum();
        int res = s.maxPathSum(root);
        System.out.println("Maximum Path sum : " + res); // Expected: 42 (15 + 20 + 7)
    }
}
