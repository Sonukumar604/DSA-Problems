import java.util.*;

public class AllTraversalsInOnePass {

    static class Node {
        int data;
        Node left, right;
        Node(int val) {
            data = val;
            left = right = null;
        }
    }

    static class Pair {
        Node node;
        int num;
        Pair(Node node, int num) {
            this.node = node;
            this.num = num;
        }
    }

    static void allTraversals(Node root,
                              List<Integer> preorder,
                              List<Integer> inorder,
                              List<Integer> postorder) {

        Stack<Pair> st = new Stack<>();
        st.push(new Pair(root, 1));

        while (!st.isEmpty()) {
            Pair it = st.pop();

            // num = 1 → Preorder
            if (it.num == 1) {
                preorder.add(it.node.data);
                it.num++;
                st.push(it);

                if (it.node.left != null)
                    st.push(new Pair(it.node.left, 1));
            }

            // num = 2 → Inorder
            else if (it.num == 2) {
                inorder.add(it.node.data);
                it.num++;
                st.push(it);

                if (it.node.right != null)
                    st.push(new Pair(it.node.right, 1));
            }

            // num = 3 → Postorder
            else {
                postorder.add(it.node.data);
            }
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(5);
        root.left.left = new Node(3);
        root.left.right = new Node(4);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        List<Integer> pre = new ArrayList<>();
        List<Integer> in = new ArrayList<>();
        List<Integer> post = new ArrayList<>();

        allTraversals(root, pre, in, post);

        System.out.println("Preorder:  " + pre);
        System.out.println("Inorder:   " + in);
        System.out.println("Postorder: " + post);
    }
}
