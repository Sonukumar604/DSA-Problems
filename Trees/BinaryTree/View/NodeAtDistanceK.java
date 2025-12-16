import java.util.*;
public class NodeAtDistanceK {
    private void markParents(TreeNode root, Map<TreeNode, TreeNode> parent){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode current = queue.poll();
            if(current.left != null){
                parent.put(current.left, current);
                queue.offer(current.left);
            }
            if(current.right != null){
                parent.put(current.right, current);
                queue.offer(current.right);
            }
        }
    }
    public ArrayList<Integer> distanceK(TreeNode root, TreeNode target, int k){
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        markParents(root, parent);
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        queue.offer(target);
        visited.add(target);
        int currentLevel = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            if(currentLevel == k) break;
            currentLevel++;
            for(int i = 0; i < size; i++){
                TreeNode current = queue.poll(); // No need to check for null, it's handled by visited set
                if(current.left != null && !visited.contains(current.left)){
                    queue.offer(current.left);
                    visited.add(current.left);
                }
                if(current.right != null && !visited.contains(current.right)){
                    queue.offer(current.right);
                    visited.add(current.right);
                }
                if(parent.get(current) != null && !visited.contains(parent.get(current))){
                    queue.offer(parent.get(current));
                    visited.add(parent.get(current));
                }
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        while(!queue.isEmpty()){
            result.add(queue.poll().val);
        }
        return result;
    }
    public static void main(String[] args) {
        // Example usage can be added here
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        NodeAtDistanceK solution = new NodeAtDistanceK();
        ArrayList<Integer> result = solution.distanceK(root, root.left, 2);
        System.out.println(result); // Expected: [7, 4, 1]
    }
}
