public class ReversedLinkedList {
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
     * Input: list = 10 -> 20, data = 30
     * 1. newNode(30) created.
     * 2. head is not null (10).
     * 3. temp = head (10).
     * 4. Loop: temp.next (20) != null. temp moves to 20.
     * 5. Loop: temp.next is null. Loop ends.
     * 6. temp.next = newNode(30).
     * Result: 10 -> 20 -> 30 -> null
     *
     * Time Complexity: O(N) - Traverses the entire list to find the last node.
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
     * Displays the linked list elements.
     *
     * Dry Run:
     * Input: list = 10 -> 20 -> null
     * 1. temp initialized to head (10).
     * 2. While loop:
     *    - Print 10 ->. temp becomes 20.
     *    - Print 20 ->. temp becomes null.
     * 3. Loop terminates.
     * 4. Print "null".
     *
     * Time Complexity: O(N) - Visits every node once.
     * Space Complexity: O(1) - Constant extra space used.
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
     * Reverses the linked list iteratively.
     *
     * Dry Run:
     * Input: 10 -> 20 -> 30 -> null
     * Initial: head=10, current=10, prev=null
     *
     * Iteration 1:
     * 1. temp = current.next (20)
     * 2. current.next = prev (null)  [10 -> null]
     * 3. prev = current (10)
     * 4. current = temp (20)
     *
     * Iteration 2:
     * 1. temp = current.next (30)
     * 2. current.next = prev (10)    [20 -> 10 -> null]
     * 3. prev = current (20)
     * 4. current = temp (30)
     *
     * Iteration 3:
     * 1. temp = current.next (null)
     * 2. current.next = prev (20)    [30 -> 20 -> 10 -> null]
     * 3. prev = current (30)
     * 4. current = temp (null)
     *
     * Termination:
     * current is null. Loop ends.
     * head = prev (30).
     * Result: 30 -> 20 -> 10 -> null
     *
     * Time Complexity: O(N) - Traverses the list once.
     * Space Complexity: O(1) - Uses three pointers regardless of list size.
     */
    public void reverseIterative(){
        Node current = head;
        Node prev = null;
        while(current != null){
            Node temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        head = prev;
    }
    public static void main(String[] args) {
        ReversedLinkedList Rll = new ReversedLinkedList();
        Rll.insert(10);
        Rll.insert(20);
        Rll.insert(30);
        Rll.insert(40);
        Rll.insert(50);
        Rll.display();
        Rll.reverseIterative();
        System.out.println("Reversed Linked List (Iterative): ");
        Rll.display();


    }

    /*
     * Complexity Summary for Reversed Linked List (Iterative):
     *
     * Time Complexity:
     * - Insert: O(N) (Traversing to end)
     * - Display: O(N) (Traversing all nodes)
     * - Reverse (Iterative): O(N) (One pass through the list)
     *
     * Space Complexity:
     * - Overall: O(N) to store the elements.
     * - Auxiliary Space: O(1) for all operations (pointers only).
     */
}
