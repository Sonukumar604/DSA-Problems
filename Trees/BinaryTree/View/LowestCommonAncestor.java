public class LowestCommonAncestor {

    /**
     * A static inner class to represent a node in the binary tree.
     */
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        if(root == null || root == p || root == q){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if(left != null && right != null){
            return root;
        }
        
        return left != null ? left : right;
    }
    public static void main(String[] args) {
        /*
               3
              / \
             5   1
            / \ / \
           6  2 0  8
             / \
            7   4
        */
        TreeNode root = new TreeNode(3);
        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(1);
        root.left = p;
        root.right = q;
        p.left = new TreeNode(6);
        p.right = new TreeNode(2);
        p.right.left = new TreeNode(7);
        p.right.right = new TreeNode(4);
        q.left = new TreeNode(0);
        q.right = new TreeNode(8);

        LowestCommonAncestor lcaFinder = new LowestCommonAncestor();

        // Test case 1: LCA of 5 and 1 is 3
        TreeNode lca1 = lcaFinder.lowestCommonAncestor(root, p, q);
        System.out.println("Lowest Common Ancestor of 5 and 1: " + (lca1 != null ? lca1.val : "null"));

        // Test case 2: LCA of 5 and 4 is 5
        TreeNode lca2 = lcaFinder.lowestCommonAncestor(root, p, p.right.right);
        System.out.println("Lowest Common Ancestor of 5 and 4: " + (lca2 != null ? lca2.val : "null"));
    }
}
