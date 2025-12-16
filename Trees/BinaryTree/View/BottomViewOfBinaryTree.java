import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class BottomViewOfBinaryTree {
    public ArrayList<Integer> bottomView(Node root){
        ArrayList<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Node> q  = new LinkedList<Node>();
        q.add(new Pair(root, 0));

        while (!q.isEmpty()) {
            Pair temp = q.poll();
            Node node = temp.node;
            int hd = temp.hd;

            // For bottom view → overwrite (take the last node at each HD)
            map.put(hd, node.data);

            if (node.left != null)
                q.add(new Pair(node.left, hd - 1));

            if (node.right != null)
                q.add(new Pair(node.right, hd + 1));
        }

        // After BFS: extract values in sorted order of HD
        for (int value : map.values())
            ans.add(value);

        return ans;
    }

    // Node class
    static class Node {
        int data;
        Node left, right;
        Node(int val) {
            data = val;
            left = right = null;
        }
    }

    // Helper class to store a node and its horizontal distance
    static class Pair {
        Node node;
        int hd;

        Pair(Node node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }

    public static void main(String[] args) {
        // Create a sample binary tree
        //       20
        //      /  \
        //     8   22
        //    / \  / \
        //   5  3 4  25
        //     / \
        //    10 14
        Node root = new Node(20);
        root.left = new Node(8);
        root.right = new Node(22);
        root.left.left = new Node(5);
        root.left.right = new Node(3);
        root.right.left = new Node(4);
        root.right.right = new Node(25);
        root.left.right.left = new Node(10);
        root.left.right.right = new Node(14);

        BottomViewOfBinaryTree tree = new BottomViewOfBinaryTree();
        ArrayList<Integer> bottomViewResult = tree.bottomView(root);
        System.out.println("Bottom View of Binary Tree: " + bottomViewResult); // Expected: [5, 10, 4, 14, 25]
    }
}
