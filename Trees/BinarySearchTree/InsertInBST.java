public class InsertInBST {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    //Recursive Approach
    public TreeNode recursivelyInsertIntoBST(TreeNode root, int val) {
        if(root == null){
            return new TreeNode(val);
        }
        if(val < root.val){
            root.left = recursivelyInsertIntoBST(root.left, val);
        }else{
            root.right = recursivelyInsertIntoBST(root.right, val);
        }
        return root;
    }
    //Iterative Approach
    public TreeNode iterativelyInsertIntoBST(TreeNode root, int val) {
        if(root == null){
            return new TreeNode(val);
        }
        TreeNode current = root;
        while(true){
            if(val < current.val){
                if(current.left == null){
                    current.left = new TreeNode(val);
                    break;
                }else{
                    current = current.left;
                }
            }else{
                if(current.right == null){
                    current.right = new TreeNode(val);
                    break;
                }else{
                    current = current.right;
                }
            }
        }
        return root;
    }

    public void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    public static void main(String[] args) {
        InsertInBST bst = new InsertInBST();
        
        int[] valuesToInsert = {5, 3, 7, 2, 4, 6, 8};

        // Test Recursive Approach
        System.out.println("Recursive Approach:");
        TreeNode rootRecursive = null;
        for (int val : valuesToInsert) {
            rootRecursive = bst.recursivelyInsertIntoBST(rootRecursive, val);
        }
        bst.inorder(rootRecursive);
        System.out.println();

        // Test Iterative Approach
        System.out.println("Iterative Approach:");
        TreeNode rootIterative = null;
        for (int val : valuesToInsert) {
            rootIterative = bst.iterativelyInsertIntoBST(rootIterative, val);
        }
        bst.inorder(rootIterative);
        System.out.println();
    }
}
