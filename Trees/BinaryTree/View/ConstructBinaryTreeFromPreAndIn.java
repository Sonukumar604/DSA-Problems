import java.util.*;
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val){
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
public class ConstructBinaryTreeFromPreAndIn {
    public TreeNode buildTree(int[] preOrder, int[] inOrder){
        Map<Integer, Integer> inMap = new HashMap<>();
        for(int i = 0; i < inOrder.length; i++){
            inMap.put(inOrder[i], i);
        }
        return build(preOrder, 0, preOrder.length - 1, inOrder, 0, inOrder.length - 1, inMap);
    }
    public TreeNode build(int[] preOrder, int preStart, int preEnd, int[] inOrder, int inStart, int inEnd, Map<Integer, Integer> inMap){
        if(preStart > preEnd || inStart > inEnd){
            return null;
        }
        TreeNode root = new TreeNode(preOrder[preStart]);
        int inRoot = inMap.get(root.val);
        int numsLeft = inRoot - inStart;
        root.left = build(preOrder, preStart + 1, preStart + numsLeft, inOrder, inStart, inRoot - 1, inMap);
        root.right = build(preOrder, preStart + numsLeft + 1, preEnd, inOrder, inRoot + 1, inEnd, inMap);
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

    public static void main(String[] args){
        ConstructBinaryTreeFromPreAndIn treeBuilder = new ConstructBinaryTreeFromPreAndIn();
        int[] preOrder = {3,9,20,15,7};
        int[] inOrder = {9,3,15,20,7};
        TreeNode root = treeBuilder.buildTree(preOrder, inOrder);
        printTree(root);
    }
}
