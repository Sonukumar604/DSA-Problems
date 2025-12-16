
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
    private Node tail; // For O(1) insertions at the end
    private int size = 0;

    public boolean isEmpty() {
        return size == 0;
    }

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
}
