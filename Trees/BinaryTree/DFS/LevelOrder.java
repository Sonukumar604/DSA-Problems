import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrder {

    // Define the structure of a tree node
    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Performs Level Order Traversal (BFS) and returns the result as a list of lists,
     * where each inner list represents a level in the tree.
     *
     * @param root The root node of the tree.
     * @return A list of lists containing the node values for each level.
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            // Get the number of nodes at the current level
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            // Process all nodes for the current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll(); // Dequeue the node
                currentLevel.add(currentNode.data);  // Add its data to the current level list

                // Enqueue its children for the next level
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
            // Add the completed level to the final result
            result.add(currentLevel);
        }

        return result;
    }

    public static void main(String[] args) {
        /*
         * Construct the following binary tree:
         *        1
         *       / \
         *      2   3
         *     / \
         *    4   5
         *
         * Expected Level Order Traversal:
         * Level 0: [1]
         * Level 1: [2, 3]
         * Level 2: [4, 5]
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("Level Order Traversal (BFS):");
        List<List<Integer>> result = levelOrder(root);

        // Print the result in a readable format
        for (int i = 0; i < result.size(); i++) {
            System.out.println("Level " + i + ": " + result.get(i));
        }
    }
}