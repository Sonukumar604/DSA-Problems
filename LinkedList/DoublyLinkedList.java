public class DoublyLinkedList {
    // Node class is private and static for better encapsulation and memory efficiency.
    private static class Node{
        int data;
        Node next;
        Node prev;
        Node(int data){
            this.data = data;
            // 'next' and 'prev' are null by default
        }
    }
    private Node head;
    private Node tail; // Added tail for O(1) operations at the end
    private int size = 0; // Added size for O(1) size checking
    
    /**
     * Returns the number of elements in the list.
     * @return the size of the list.
     */
    public int size() {
        return size;
    }
    /**
     * Checks if the list is empty.
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }
    public void insertAtBeginning(int data){
        Node newNode = new Node(data);
        if(isEmpty()){
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }
    public void insertAtEnd(int data){
        Node newNode = new Node(data);
        if(isEmpty()){
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }
    
    /**
     * Inserts data at a specific position (1-based).
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
        if (newNode.next != null) {
            newNode.next.prev = newNode;
        }
        newNode.prev = current;
        current.next = newNode;
        size++;
    }
    public void deleteFromBeginning(){
        if(isEmpty()){
            System.out.println("Error: List is empty. Cannot delete.");
            return;
        }
        if(size == 1){
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
    }
    
    public void deleteFromEnd(){
        if(isEmpty()){
            System.out.println("Error: List is empty. Cannot delete.");
            return;
        }
        if(size == 1){
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
    }
    /**
     * Deletes data from a specific position (1-based).
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
        current = null;
        size--;
    }
    
    public void displayForward(){
        if(isEmpty()){
            System.out.println("List is empty.");
            return;
        }
        Node temp = head;
        System.out.print("Forward: null <- ");
        while(temp != null){
            System.out.print(temp.data + " <-> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
    public void displayBackward(){
        if(isEmpty()){
            System.out.println("List is empty.");
            return;
        }
        Node temp = tail;
        System.out.print("Backward: null <- ");
        while(temp != null ){
            System.out.print(temp.data + " <-> ");
            temp = temp.prev;
        }
        System.out.println("null");
    }
    
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        System.out.println("Is list empty? " + list.isEmpty());

        System.out.println("\n--- Inserting elements ---");
        list.insertAtBeginning(10);
        list.insertAtEnd(20);
        list.insertAtEnd(40);
        list.insertAtPosition(30, 3); // Insert 30 at position 3
        list.insertAtPosition(5, 1);   // Insert 5 at the beginning
        list.insertAtPosition(50, 6);  // Insert 50 at the end
        
        list.displayForward();
        list.displayBackward();
        System.out.println("Current size: " + list.size());

        System.out.println("\n--- Deleting elements ---");
        list.deleteFromBeginning();
        System.out.println("After deleting from beginning:");
        list.displayForward();

        list.deleteFromEnd();
        System.out.println("After deleting from end:");
        list.displayForward();

        list.deleteFromPosition(2); // Delete '30' from position 2
        System.out.println("After deleting from position 2:");
        list.displayForward();
        System.out.println("Current size: " + list.size());
    }
}