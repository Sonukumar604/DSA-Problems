
public class SinglyLinkedList {
    static class Node{
        int data;
        Node next;
        Node(int data){
            this.data = data;
            this.next = null;
        }
    }
    private Node head;
    private Node tail; 
    private int size = 0;

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Inserts a node at the beginning of the list.
     *
     * Dry Run:
     * Input: list = 10 -> 20, data = 5
     * 1. newNode created with data 5.
     * 2. isEmpty() is false.
     * 3. newNode.next = head (10).
     * 4. head = newNode (5).
     * 5. size increments.
     * Result: list = 5 -> 10 -> 20
     *
     * Time Complexity: O(1) - Constant time operation.
     * Space Complexity: O(1) - Constant extra space used.
     */
    public void insertAtBeginning(int data){
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
        newNode.next = head;
        head = newNode;
        }
        size++;
    }

    /**
     * Inserts a node at the end of the list.
     *
     * Dry Run:
     * Input: list = 10 -> 20, tail -> 20, data = 30
     * 1. newNode created with data 30.
     * 2. isEmpty() is false.
     * 3. tail.next (20.next) = newNode (30).
     * 4. tail = newNode (30).
     * 5. size increments.
     * Result: list = 10 -> 20 -> 30
     *
     * Time Complexity: O(1) - Because we maintain a tail pointer.
     * Space Complexity: O(1) - Constant extra space used.
     */
    public void insertAtEnd(int data){
        Node newNode = new Node(data);
        if(isEmpty()){
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /**
     * Inserts a node at a specific position.
     *
     * Dry Run:
     * Input: list = 10 -> 20 -> 30, data = 25, position = 3
     * 1. Position check passes.
     * 2. position != 1 and position != size + 1 (4).
     * 3. newNode created with data 25.
     * 4. temp = head (10).
     * 5. Loop i=1 to position-1 (2):
     *    i=1: temp = temp.next (20).
     * 6. Loop ends. temp is at node 20 (before insertion point).
     * 7. newNode.next = temp.next (30).
     * 8. temp.next = newNode (25).
     * 9. size increments.
     * Result: list = 10 -> 20 -> 25 -> 30
     *
     * Time Complexity: O(N) - In the worst case, we traverse the list.
     * Space Complexity: O(1) - Constant extra space used.
     */
    public void insertAtPosition(int data, int position){
        if (position < 1 || position > size + 1) {
            // Throwing an exception is better practice
            throw new IndexOutOfBoundsException("Position out of range. Valid positions are 1 to " + (size + 1) + ".");
        }
        if(position == 1){
            insertAtBeginning(data);
            return;
        }
        if(position == size + 1){
            insertAtEnd(data);
            return;
        }
        Node newNode = new Node(data);
        Node temp = head;
        // No need for '&& temp != null' with the improved bounds check
        for(int i = 1; i < position - 1; i++){
            temp = temp.next;
        }
        newNode.next = temp.next;
        temp.next = newNode;
        size++;
    }

    /**
     * Deletes the first node of the list.
     *
     * Dry Run:
     * Input: list = 10 -> 20
     * 1. head is not null.
     * 2. head = head.next (20).
     * 3. size decrements.
     * Result: list = 20
     *
     * Time Complexity: O(1) - Constant time operation.
     * Space Complexity: O(1) - Constant extra space used.
     */
    public void deleteFromBeginning(){
        if(head == null){
            System.out.println("List is Empty.");
            return;
        }
        head = head.next;
        size--;
        if (isEmpty()) {
            tail = null;
        }
    }

    /**
     * Deletes the last node of the list.
     *
     * Dry Run:
     * Input: list = 10 -> 20 -> 30, tail -> 30
     * 1. List is not empty.
     * 2. size decrements.
     * 3. head.next is not null.
     * 4. temp = head (10).
     * 5. Loop while temp.next.next != null:
     *    - temp (10).next (20).next (30) != null. temp becomes 20.
     *    - temp (20).next (30).next is null. Loop ends.
     * 6. temp is now node 20.
     * 7. temp.next = null (removes link to 30).
     * 8. tail = temp (20).
     * Result: list = 10 -> 20
     *
     * Time Complexity: O(N) - We have to traverse to the second to last element.
     * Space Complexity: O(1) - Constant extra space used.
     */
    public void deleteFromEnd(){
        if(isEmpty()){
            System.out.println("List is Empty.");
            return;
        }
        size--; // Decrement size first, as it happens in all non-empty cases.
        if(head.next == null){ // Case: List had only one element.
            head = null;
            tail = null;
            return;
        }
        // Case: List has more than one element.
        Node temp = head;
        while(temp.next.next != null){
            temp = temp.next;
        }
        temp.next = null;
        tail = temp; // Update the tail pointer
    }

    /**
     * Deletes a node at a specific position.
     *
     * Dry Run:
     * Input: list = 10 -> 20 -> 30 -> 40, position = 3
     * 1. Position checks pass.
     * 2. temp = head (10).
     * 3. Loop i=1 to position-1 (2):
     *    i=1: temp = temp.next (20).
     * 4. Loop ends. temp is at node 20 (node before target).
     * 5. temp.next = temp.next.next (skips 30, links to 40).
     * 6. size decrements.
     * Result: list = 10 -> 20 -> 40
     *
     * Time Complexity: O(N) - In worst case, we traverse to the position.
     * Space Complexity: O(1) - Constant extra space used.
     */
    public void deleteFromPosition(int position){
        if (position < 1 || position > size) {
            throw new IndexOutOfBoundsException("Position out of range. Valid positions are 1 to " + size + ".");
        }
        if (position == 1) {
            deleteFromBeginning();
            return;
        }
        if (position == size) {
            deleteFromEnd();
            return;
        }
        Node temp = head;
        for(int i = 1; i < position - 1; i++){
            temp = temp.next;
        }
        temp.next = temp.next.next;
        size--;
    }

    /**
     * Displays the linked list.
     *
     * Dry Run:
     * Input: list = 10 -> 20 -> null
     * 1. head is not null.
     * 2. temp = head (10).
     * 3. Loop while temp != null:
     *    - Print 10 ->
     *    - temp = temp.next (20)
     *    - Print 20 ->
     *    - temp = temp.next (null)
     * 4. Loop ends.
     * 5. Print "null".
     * Result: 10 -> 20 -> null
     *
     * Time Complexity: O(N) - We traverse every node.
     * Space Complexity: O(1) - Constant extra space used.
     */
    public void display(){
        if(head == null){
            System.out.println("List is Empty.");
            return;
        }
        Node temp = head;
        while(temp != null){
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        list.insertAtBeginning(10);
        list.insertAtEnd(20);
        list.insertAtPosition(15, 2);
        list.display();
        list.deleteFromPosition(2);
        list.display();
    }

    /*
     * Complexity Summary for Singly Linked List:
     * 
     * Time Complexity:
     * - Insert at Beginning: O(1)
     * - Insert at End: O(1) (with tail pointer)
     * - Insert at Position: O(N)
     * - Delete from Beginning: O(1)
     * - Delete from End: O(N) (needs to find second to last node)
     * - Delete from Position: O(N)
     * - Display/Traversal: O(N)
     * 
     * Space Complexity:
     * - O(N) overall to store the elements.
     * - O(1) auxiliary space for operations (pointers).
     */
}
