import java.util.*;

public class ConstructBinaryTreeFromPostAndIn {
    public TreeNode buildTree(int[] inorder, int[] postorder){
        if(inorder == null || postorder == null || inorder.length != postorder.length){
            return null;
        }
        Map<Integer, Integer> hm = new HashMap<>();
        for(int i = 0; i < inorder.length; i++){
            hm.put(inorder[i], i);
        }
        return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, hm);
    }
    private TreeNode build(int[] inorder, int inStart, int inEnd, int[] postorder, int pStart, int pEnd, Map<Integer, Integer> hm){
        if(inStart > inEnd || pStart > pEnd){
            return null;
        }
        TreeNode root = new TreeNode(postorder[pEnd]);
        int inRoot = hm.get(postorder[pEnd]);
        int numsLeft = inRoot - inStart;
        root.left = build(inorder, inStart, inRoot - 1, postorder, pStart, pStart + numsLeft - 1, hm);
        root.right = build(inorder, inRoot + 1, inEnd, postorder, pStart + numsLeft, pEnd - 1, hm);
        return root;
    }

    // Helper method to print the tree (pre-order) to verify the result
    public static void printTree(TreeNode root){
        if(root == null){
            return;
        }
        System.out.print(root.val + " ");
        printTree(root.left);
        printTree(root.right);
    }

    public static void main(String[] args) {
        ConstructBinaryTreeFromPostAndIn treeBuilder = new ConstructBinaryTreeFromPostAndIn();
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        TreeNode root = treeBuilder.buildTree(inorder, postorder);
        System.out.print("Pre-order traversal of constructed tree: ");
        printTree(root); // Expected output: 3 9 20 15 7
        System.out.println();
    }
}
