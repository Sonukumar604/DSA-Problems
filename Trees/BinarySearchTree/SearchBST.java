public class SearchBST {
      public TreeNode searchBSTIterative(TreeNode root, int val) {

        while (root != null && root.val != val) {
            root = (val < root.val) ? root.left : root.right;
        }

        return root;
    }
    public TreeNode searchBSTRecursive(TreeNode root, int val){
        if (root == null || val == root.val) {
            return root;
        }
        if(val < root.val){
            return searchBSTRecursive(root.left, val);
        } else {
            return searchBSTRecursive(root.right, val);
        }
    }
    public class TreeNode {
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
        SearchBST bst = new SearchBST();

        // Let's create a sample Binary Search Tree:
        //      4
        //     / \
        //    2   7
        //   / \
        //  1   3
        TreeNode root = bst.new TreeNode(4);
        root.left = bst.new TreeNode(2);
        root.right = bst.new TreeNode(7);
        root.left.left = bst.new TreeNode(1);
        root.left.right = bst.new TreeNode(3);

        System.out.println("--- Testing Recursive Search ---");
        // --- Test Case 1: Search for a value that exists ---
        int valueToFind1 = 2;
        TreeNode result1 = bst.searchBSTRecursive(root, valueToFind1);
        System.out.println("Searching for " + valueToFind1 + ": " + (result1 != null ? "Found node with value " + result1.val : "Not Found"));

        // --- Test Case 2: Search for a value that does NOT exist ---
        int valueToFind2 = 5;
        TreeNode result2 = bst.searchBSTRecursive(root, valueToFind2);
        System.out.println("Searching for " + valueToFind2 + ": " + (result2 != null ? "Found node with value " + result2.val : "Not Found"));

        System.out.println("\n--- Testing Iterative Search ---");
        // --- Test Case 3: Search for a value that exists ---
        TreeNode result3 = bst.searchBSTIterative(root, valueToFind1);
        System.out.println("Searching for " + valueToFind1 + ": " + (result3 != null ? "Found node with value " + result3.val : "Not Found"));

        // --- Test Case 4: Search for a value that does NOT exist ---
        TreeNode result4 = bst.searchBSTIterative(root, valueToFind2);
        System.out.println("Searching for " + valueToFind2 + ": " + (result4 != null ? "Found node with value " + result4.val : "Not Found"));
    }
}
