public class DeleteInBST {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public TreeNode deleteNode(TreeNode root, int key){
        if(root == null){
            return null;
        }
        if(root.val == key){
            return helper(root);
        }
        TreeNode dummy = root;
        while(root != null){
            if(key < root.val){
                if(root.left != null && root.left.val == key){
                    root.left = helper(root.left);
                    break;
                }else{
                    root = root.left;
                }
            }
            else{
                if(root.right != null && root.right.val == key){
                    root.right = helper(root.right);
                    break;
                }else{
                    root = root.right;
                }
            }
        }
        return dummy;
    }
    private TreeNode helper(TreeNode root){
        if(root.left == null){
            return root.right;
        }
        if(root.right == null){
            return root.left;
        }
        TreeNode rightChild = root.right;
        TreeNode lastRight = findLastRight(root.left);
        lastRight.right = rightChild;
        return root.left;
    }
    private TreeNode findLastRight(TreeNode root){
        if(root.right == null){
            return root;
        }
        return findLastRight(root.right);
    }

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

    public void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    public static void main(String[] args) {
        DeleteInBST bst = new DeleteInBST();
        int[] valuesToInsert = {5, 3, 7, 2, 4, 6, 8};
        TreeNode root = null;
        for (int val : valuesToInsert) {
            root = bst.recursivelyInsertIntoBST(root, val);
        }
        System.out.println("Original BST (Inorder):");
        bst.inorder(root);
        int keyToDelete = 3;
        root = bst.deleteNode(root, keyToDelete);
        System.out.println("\nBST after deleting " + keyToDelete + " (Inorder):");
        bst.inorder(root);
        System.out.println();
    }
}
