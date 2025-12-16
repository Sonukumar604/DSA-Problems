public class CircularSinglyLinkedList {
    static class Node{
        int data;
        Node next;
        Node(int data){
            this.data = data;
            this.next = null;
        }
    }
    private Node head = null;
    private Node tail = null; // Crucial for O(1) end operations
    private int size = 0;     // Crucial for robust position checks
    
    /**
     * Checks if the list is empty.
     * @return true if the list has no elements, false otherwise.
     * Time Complexity: O(1)
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the list.
     * @return The size of the list.
     * Time Complexity: O(1)
     */
    public int size() {
        return size;
    }

    /**
     * Inserts an element at the beginning of the list.
     * This is an O(1) operation.
     * @param data The data to insert.
     */
    public void insertAtBeginning(int data){
        Node newNode = new Node(data);
        if(isEmpty()){
            head = newNode;
            tail = newNode;
            newNode.next = head; // Point to itself
        } else {
            newNode.next = head;
            head = newNode;
            tail.next = head; // The tail now points to the new head
        }
        size++;
    }

    /**
     * Inserts an element at the end of the list.
     * This is an O(1) operation.
     * @param data The data to insert.
     */
    public void insertAtEnd(int data){
        Node newNode = new Node(data);
        if(isEmpty()){
            // If empty, it's the same as inserting at the beginning
            head = newNode;
            tail = newNode;
            newNode.next = head;
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head; // The new tail points back to the head
        }
        size++;
    }

    /**
     * Inserts an element at a specific 1-based position.
     * Time Complexity: O(n) in the worst case (insertion in the middle).
     * @param data The data to insert.
     * @param position The 1-based position.
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
        current.next = newNode;
        size++;
    }

    /**
     * Deletes the element from the beginning of the list.
     * This is an O(1) operation.
     */
    public void deleteFromBeginning(){
        if(isEmpty()){
            System.out.println("Error: List is empty. Cannot delete.");
            return;
        }
        if(size == 1){
            head = null;
            tail = null;
        }else{
            head = head.next;
            tail.next = head;
        }
        size--;
    }

    /**
     * Deletes the element from the end of the list.
     * This is an O(n) operation as we must find the node before the tail.
     */
    public void deleteFromEnd(){
        if(isEmpty()){
            System.out.println("Error: List is empty. Cannot delete.");
            return;
        }
        if(size == 1){
            head = null;
            tail = null;
        }else{
            // Traverse until we find the node just before the tail
            Node current = head;
            while(current.next != tail){
                current = current.next;
            }
            current.next = head; // New last node points to head
            tail = current;      // Update tail
        }
        size--;
    }
    /**
     * Deletes an element from a specific 1-based position.
     * Time Complexity: O(n) in the worst case.
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
        
        // Find node *before* the one to be deleted
        Node prev = head;
        for(int i = 1; i < position - 1; i++){
            prev = prev.next;
        }
        prev.next = prev.next.next;
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
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }while(temp != head);
        System.out.println("(back to head " + head.data + ")");
    }

    public static void main(String[] args) {
        CircularSinglyLinkedList csll = new CircularSinglyLinkedList();
        System.out.println("--- Initial insertions ---");
        csll.insertAtBeginning(30);
        csll.insertAtBeginning(20);
        csll.insertAtBeginning(10);
        csll.display();

        System.out.println("\n--- Inserting at end ---");
        csll.insertAtEnd(40);
        csll.insertAtEnd(50);
        csll.display();

        System.out.println("\n--- Inserting by position ---");
        csll.insertAtPosition(25, 3);
        csll.insertAtPosition(5, 1);   // Test beginning
        csll.insertAtPosition(60, 8);  // Test end
        csll.display();

        System.out.println("\n--- Deletions ---");
        csll.deleteFromBeginning();
        System.out.print("After deleting from beginning: ");
        csll.display();

        csll.deleteFromEnd();
        System.out.print("After deleting from end: ");
        csll.display();

        csll.deleteFromPosition(3);
        System.out.print("After deleting from position 3: ");
        csll.display();
    }
}
