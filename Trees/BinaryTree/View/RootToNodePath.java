import java.util.ArrayList;

public class RootToNodePath {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }
    private boolean getPath(TreeNode root, ArrayList<Integer> arr, int x){
        if(root == null){
            return false; // If root is null, there's no path
        }
        arr.add(root.val);
        if(root.val == x){
            return true;
        }
        if(getPath(root.left, arr, x) || (getPath(root.right, arr, x))){
            return true;
        } else {
            arr.remove(arr.size() - 1); // Remove if not in path
        }        
        return false;
    }
    public ArrayList<Integer> solve(TreeNode A, int B){
        ArrayList<Integer> arr = new ArrayList<>();
        if(A == null) return arr;
        getPath(A, arr, B);
        return arr; // Return the path
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        RootToNodePath solution = new RootToNodePath();
        System.out.println(solution.solve(root, 2));
    }
}
