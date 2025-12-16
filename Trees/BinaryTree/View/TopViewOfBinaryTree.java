import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class TopViewOfBinaryTree {
    public static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // Helper class to store a node and its horizontal distance
    static class Pair {
        TreeNode node;
        int hd;

        Pair(TreeNode node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }
    public static ArrayList<Integer> topView(TreeNode root){
        ArrayList<Integer> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<Pair>();
        q.add(new Pair(root, 0));
        while(!q.isEmpty()){
            Pair it = q.poll();// it is a pair of node and its horizontal distance
            int hd = it.hd; // horizontal distance
            TreeNode temp = it.node; // current node
            if(map.get(hd) == null){
                map.put(hd, temp.data);
            }
            if(temp.left != null){
                q.add(new Pair(temp.left, hd - 1));
            }
            if(temp.right != null){
                q.add(new Pair(temp.right, hd + 1));
            }
        }
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            result.add(entry.getValue());
        }
        return result;
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.left.right.right = new TreeNode(5);
        root.left.right.right.right = new TreeNode(6);

        ArrayList<Integer> topViewResult = topView(root);
        System.out.println("Top View of Binary Tree: " + topViewResult);
    }
}
