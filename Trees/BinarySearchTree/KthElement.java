import java.util.Stack;

public class KthElement {
    //Kth Largest Element in a BST
    static int count = 0;
    static int result = -1;
    public static int kthLargest(TreeNode root, int k){
        count = 0; // Reset state for every new call
        result = -1;
        reverseInorder(root, k);
        return result;
    }
    private static void reverseInorder(TreeNode root, int k){
        // Optimization: Stop recursion if we have already found the kth element
        if(root == null || count >= k) return;
        reverseInorder(root.right, k);
        count++;
        if(count == k){
            result = root.val;
            return;
        }
        reverseInorder(root.left, k);
    }

    // Iterative Kth Largest
    public static int kthLargestIterative(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        int cnt = 0;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.right;
            }
            node = stack.pop();
            cnt++;
            if (cnt == k) return node.val;
            node = node.left;
        }
        return -1;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
        TreeNode(int x, TreeNode left, TreeNode right){
            val = x;
            this.left = left;
            this.right = right;
        }
    }
    //Kth Smallest Element in a BST
    static int smallCount = 0;
    static int smallResult = -1;
    public static int kthSmallest(TreeNode root, int k){
        smallCount = 0; // Reset state for every new call
        smallResult = -1;
        inorder(root, k);
        return smallResult;
    }
    private static void inorder(TreeNode root, int k){
        // Optimization: Stop recursion if we have already found the kth element
        if(root == null || smallCount >= k) return;
        inorder(root.left, k);
        smallCount++;
        if(smallCount == k){
            smallResult = root.val;
            return;
        }
        inorder(root.right, k);
    }

    // Iterative Kth Smallest
    public static int kthSmallestIterative(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        int cnt = 0;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            cnt++;
            if (cnt == k) return node.val;
            node = node.right;
        }
        return -1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(9);

        int k = 3;
        int kthLargestElement = kthLargest(root, k);
        System.out.println(k + "th largest element in the BST is: " + kthLargestElement);

        int kthLargestIterative = kthLargestIterative(root, k);
        System.out.println(k + "th largest element (Iterative) is: " + kthLargestIterative);

        int kthSmallestElement = kthSmallest(root, k);
        System.out.println(k + "th smallest element in the BST is: " + kthSmallestElement);

        int kthSmallestIterative = kthSmallestIterative(root, k);
        System.out.println(k + "th smallest element (Iterative) is: " + kthSmallestIterative);
    }
}
