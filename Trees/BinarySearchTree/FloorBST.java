public class FloorBST {
    public static int findFloorInBST(TreeNode root, int key){
        if(root == null){
            return -1;
        }
        int floor = -1;
        while(root != null){
            if(root.val == key){
                return root.val;
            }
            else if(root.val > key){
                root = root.left;
            }
            else{
                floor = root.val;
                root = root.right;
            }
        }
        return floor;
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
        int floor = findFloorInBST(root, key);
        if(floor != -1){
            System.out.println("Floor of " + key + " is: " + floor);
        } else {
            System.out.println("Floor of " + key + " does not exist in the BST.");
        }

    }
}