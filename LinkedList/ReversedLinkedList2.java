public class ReversedLinkedList2 {
     class Node{
        int data;
        Node next;
        Node(int data){
            this.data = data;
            this.next = null;
        }
    }
    Node head;

    /**
     * Inserts a new node at the end of the list.
     *
     * Dry Run:
     * Input: list = 10 -> null, data = 20
     * 1. newNode(20) created.
     * 2. head (10) is not null.
     * 3. temp = head (10).
     * 4. Loop: temp.next is null. Loop terminates.
     * 5. temp.next = newNode(20).
     * Result: 10 -> 20 -> null
     *
     * Time Complexity: O(N) - Traverses to the end of the list.
     * Space Complexity: O(1) - Constant extra space.
     */
    public void insert(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            return;
        }
        Node temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = newNode;
    }

    /**
     * Displays the linked list.
     *
     * Dry Run:
     * Input: 10 -> 20 -> null
     * 1. temp = head (10).
     * 2. Print 10 ->. temp moves to 20.
     * 3. Print 20 ->. temp moves to null.
     * 4. Loop ends. Print "null".
     *
     * Time Complexity: O(N) - Visits every node.
     * Space Complexity: O(1) - Constant auxiliary space.
     */
    public void display(){
        Node temp = head;
        while(temp != null){
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    /**
     * Reverses the linked list recursively.
     *
     * Dry Run:
     * Input: 10 -> 20 -> null
     *
     * 1. Call reverseRecursive(10):
     *    - head (10) != null, head.next (20) != null.
     *    - Recurse: newHead = reverseRecursive(20).
     *
     *    2. Call reverseRecursive(20):
     *       - head (20) != null, head.next is null.
     *       - Base case reached. Return head (20).
     *
     *    - Back in call (1): newHead = 20.
     *    - head (10).next is 20.
     *    - head.next.next (20.next) = head (10). (Sets 20 -> 10)
     *    - head.next = null. (Sets 10 -> null)
     *    - Return newHead (20).
     *
     * Final Result: 20 -> 10 -> null.
     *
     * Time Complexity: O(N) - Visits every node once.
     * Space Complexity: O(N) - Recursion stack space proportional to the number of nodes.
     */
    public Node reverseRecursive(Node head){
        if(head == null || head.next == null){
            return head;
        }
        Node newHead = reverseRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
    public static void main(String[] args) {
        ReversedLinkedList2 rll = new ReversedLinkedList2();
        rll.insert(10);
        rll.insert(20);
        rll.insert(30);
        rll.insert(40);
        rll.insert(50);
        rll.display();
        rll.head = rll.reverseRecursive(rll.head);
        System.out.println("Reversed Linked List(recursive): ");
        rll.display();

    }

    /*
     * Complexity Summary for Reversed Linked List (Recursive):
     *
     * Time Complexity:
     * - Insert: O(N) (Traversing to end)
     * - Display: O(N) (Traversing all nodes)
     * - Reverse (Recursive): O(N) (One pass through the list)
     *
     * Space Complexity:
     * - Overall: O(N) to store the elements.
     * - Auxiliary Space: O(N) for recursion stack in reverseRecursive; O(1) for others.
     */
}
