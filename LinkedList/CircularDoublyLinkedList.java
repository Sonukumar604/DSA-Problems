public class CircularDoublyLinkedList {
    // Node class is now private and static for better encapsulation.
    private static class Node {
        int data;
        Node next, prev;
        Node(int data){
            this.data = data;
            // next and prev are null by default
        }
    }
    private Node head = null;
    private int size = 0; // Added size for O(1) size checking and robust position validation.

    /**
     * Checks if the list is empty.
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the list.
     * @return the size of the list.
     */
    public int size() {
        return size;
    }

    /**
     * Inserts an element at the beginning of the list. O(1) operation.
     * @param data The data to insert.
     */
    public void insertAtBeginning(int data){
        Node newNode = new Node(data);
        if(isEmpty()){
            head = newNode;
            newNode.next = newNode;
            newNode.prev = newNode;
        }else{
            Node tail = head.prev;// Get the current tail
            newNode.next = head;// Point newNode to current head
            head.prev = newNode;// Point current head's prev to newNode
            newNode.prev = tail;// Point newNode's prev to current tail
            tail.next = newNode;// Point current tail's next to newNode
            head = newNode;// Update head to newNode
        }
        size++;
    }
    /**
     * Inserts an element at the end of the list. O(1) operation.
     * @param data The data to insert.
     */
    public void insertAtEnd(int data){
        // If the list is empty, inserting at the end is the same as inserting at the beginning.
        if(isEmpty()){
            insertAtBeginning(data);
            return;
        }else{
            Node newNode = new Node(data);
            Node tail = head.prev;
            newNode.next = head;
            head.prev = newNode;
            newNode.prev = tail;
            tail.next = newNode;
            size++;
        }
    }
    /**
     * Inserts data at a specific 1-based position.
     * @param data The data to insert.
     * @param position The 1-based position to insert at.
     */
    public void insertAtPosition(int data, int position){
        if(position < 1 || position > size + 1){
            System.out.println("Error: Position out of range. Valid positions are 1 to " + (size + 1) + ".");
            return;
        }
        if(position == 1){
            insertAtBeginning(data);
            return;
        }
        if(position == size + 1){
            insertAtEnd(data);
            return;
        }
        // Traverse to the node *before* the desired insertion point
        Node current = head;
        for(int i = 1; i < position - 1; i++){
            current = current.next;
        }
        Node newNode = new Node(data);
        newNode.next = current.next;
        newNode.prev = current;
        current.next.prev = newNode;
        current.next = newNode;
        size++;
    }

    /**
     * Deletes the element from the beginning of the list. O(1) operation.
     */
    public void deleteFromBeginning(){
        if(isEmpty()){
            System.out.println("Error: List is empty. Cannot delete.");
            return;
        }
        if(size == 1){
            head = null;
        } else {
            Node tail = head.prev;
            head = head.next;
            head.prev = tail;
            tail.next = head;
        }
        size--;
    }
    /**
     * Deletes the element from the end of the list. O(1) operation.
     */
    public void deleteFromEnd(){
        if(isEmpty()){
            System.out.println("Error: List is empty. Cannot delete.");
            return;
        }
        if(size == 1){
            head = null;
        } else {
            Node tail = head.prev;// Get the current tail
            tail.prev.next = head;// Point the new tail's next to head
            head.prev = tail.prev;// Point head's prev to new tail
        }
        size--;
    }
    /**
     * Deletes data from a specific 1-based position.
     * @param position The 1-based position to delete from.
     */
    public void deleteFromPosition(int position){
        if(position < 1 || position > size){
            System.out.println("Error: Position out of range. Valid positions are 1 to " + size + ".");
            return;
        }
        if(position == 1){
            deleteFromBeginning();
            return;
        }
        if(position == size){
            deleteFromEnd();
            return;
        }
        
        // Traverse to the node *at* the desired deletion point
        Node current = head;
        for(int i = 1; i < position; i++){
            current = current.next;
        }
        
        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
    }

    /**
     * Displays the contents of the list.
     */
    public void display(){
        if(isEmpty()){
            System.out.println("List is empty.");
            return;
        }
        Node temp = head;
        System.out.print("List (size=" + size + "): head -> ");
        do{
            System.out.print(temp.data + " <-> ");
            temp = temp.next;
        } while(temp != head);
        System.out.println("(back to head " + head.data + ")");
    }
    public static void main(String[] args) {
        CircularDoublyLinkedList cdll = new CircularDoublyLinkedList();
        System.out.println("--- Inserting elements ---");
        cdll.insertAtEnd(20);
        cdll.insertAtEnd(30);
        cdll.insertAtBeginning(10);
        cdll.insertAtEnd(50);
        cdll.insertAtPosition(40, 4); // Insert 40 at position 4
        cdll.insertAtPosition(5, 1);   // Insert 5 at the beginning
        cdll.insertAtPosition(60, 7);  // Insert 60 at the end
        cdll.display();
        System.out.println("Current size: " + cdll.size());

        System.out.println("\n--- Deleting elements ---");
        cdll.deleteFromBeginning();
        System.out.println("After deleting from beginning:");
        cdll.display();

        cdll.deleteFromEnd();
        System.out.println("After deleting from end:");
        cdll.display();

        cdll.deleteFromPosition(3); // Delete '30' from position 3
        System.out.println("After deleting from position 3:");
        cdll.display();
        System.out.println("Current size: " + cdll.size());
    }
}
