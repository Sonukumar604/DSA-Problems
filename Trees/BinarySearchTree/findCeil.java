public class findCeil {
    public static int findCeilInBST(TreeNode root, int key){
        if(root == null){
            return -1;
        }
        int ceil = -1;
        while(root != null){
            if(root.val == key){
                return root.val;
            }
            else if(root.val < key){
                root = root.right;
            }
            else{
                ceil = root.val;
                root = root.left;
            }
        }
        return ceil;
    }
    public static class TreeNode {
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
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(4);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);
        root.right.right = new TreeNode(3);
        int key = 5;
        int ceil = findCeilInBST(root, key);
        if(ceil != -1){
            System.out.println("Ceil of " + key + " is: " + ceil);
        } else {
            System.out.println("Ceil of " + key + " does not exist in the BST.");
        }

    }
}
