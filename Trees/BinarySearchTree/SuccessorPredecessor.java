public class SuccessorPredecessor {
    /**
     * Finds the Inorder Predecessor and Successor of a given key in a BST.
     *
     * Time Complexity: O(H) - Where H is the height of the tree. In worst case O(N).
     * Space Complexity: O(1) - No extra space used for recursion or data structures.
     */
    static class TreeNode{
        int val;
        TreeNode Left, Right;
        TreeNode(int val){
            this.val = val;
            this.Left = this.Right = null;
        }
    }
    static class Result {
        TreeNode predecessor, successor;
    }
    public static Result PredeSuccessor(TreeNode root, int key){
        Result res = new Result();
        
        TreeNode current = root;
        while(current != null){
            if(current.val == key){
                // Find Predecessor
                if(current.Left != null){
                    TreeNode temp = current.Left;
                    while(temp.Right != null){
                        temp = temp.Right;
                    }
                    res.predecessor = temp;
                }
                // Find Successor
                if(current.Right != null){
                    TreeNode temp = current.Right;
                    while(temp.Left != null){
                        temp = temp.Left;
                    }
                    res.successor = temp;
                }
                break;
            }
            else if(key < current.val){
                res.successor = current;
                current = current.Left;
            }
            else{
                res.predecessor = current;
                current = current.Right;
            }
        }
        return res;
    }
    public static void main(String[] args) {
        // Constructing a sample BST
        //       50
        //      /  \
        //    30    70
        //   / \    / \
        //  20 40  60 80
        TreeNode root = new TreeNode(50);
        root.Left = new TreeNode(30);
        root.Right = new TreeNode(70);
        root.Left.Left = new TreeNode(20);
        root.Left.Right = new TreeNode(40);
        root.Right.Left = new TreeNode(60);
        root.Right.Right = new TreeNode(80);

        int key = 65;
        Result res = PredeSuccessor(root, key);
        System.out.println("Key: " + key);
        System.out.println("Predecessor: " + (res.predecessor != null ? res.predecessor.val : "null"));
        System.out.println("Successor: " + (res.successor != null ? res.successor.val : "null"));
    }
}
