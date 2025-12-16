import java.util.ArrayList;
import java.util.List;

public class MorrisTraversal {

    // TreeNode class definition
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public List<Integer> morrisInorderTraversal(TreeNode root){
        ArrayList<Integer> inorder = new ArrayList<>();
        TreeNode cur = root;
        while(cur != null){
            if(cur.left == null){
                inorder.add(cur.val);
                cur = cur.right;
            }else{
                TreeNode prev = cur.left;
                while (prev.right != null && prev.right != cur) {
                    prev = prev.right;
                }
                if (prev.right == null) {
                    prev.right = cur;
                    cur = cur.left;
                } else {
                    prev.right = null;
                    inorder.add(cur.val);
                    cur = cur.right;
                }
            }
        }
        return inorder;
    }

    public static void main(String[] args) {
        MorrisTraversal mt = new MorrisTraversal();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        List<Integer> result = mt.morrisInorderTraversal(root);
        System.out.println(result); // Expected output: [1, 3, 2]
    }
}