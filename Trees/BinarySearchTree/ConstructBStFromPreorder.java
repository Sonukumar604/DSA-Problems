import java.util.Stack;
public class ConstructBStFromPreorder {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val){
            this.val = val;
            left = right = null;
        }
    }
    public static TreeNode constructBSTFromPreorder(int[] preorder){
        if(preorder.length == 0){
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(preorder[0]);
        stack.push(root);
        for(int i = 1; i < preorder.length; i++){
            TreeNode node = new TreeNode(preorder[i]);
            if(preorder[i] < stack.peek().val){
                stack.peek().left = node;
                stack.push(node);
            }else{
                TreeNode parent = null;
                while(!stack.isEmpty() && preorder[i] > stack.peek().val){
                    parent = stack.pop();
                }
                parent.right = node;
                stack.push(node);
            }
        }
        return root;
    }
    public static void inorderTraversal(TreeNode root){
        if(root == null){
            return;
        }
        inorderTraversal(root.left);
        System.out.print(root.val + " ");
        inorderTraversal(root.right);
    }
    public static void main(String[] args) {
        int[] preorder = {10, 5, 1, 7, 40, 50};
        TreeNode root = constructBSTFromPreorder(preorder);
        System.out.println("Inorder Traversal of the constructed BST:");
        inorderTraversal(root);
    }
}
