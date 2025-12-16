public class SortedLinkedList2 {

    // Inner class to represent a node in the doubly linked list
    static class Node {
        int data;
        Node prev;
        Node next;

        Node(int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;

    /**
     * Inserts a new node with the given data into the list while maintaining
     * the sorted (ascending) order.
     *
     * @param data The data for the new node.
     */
    public void insert(int data) {
        Node newNode = new Node(data);

        // Case 1: The list is empty.
        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }

        // Case 2: The new node's data is smaller than or equal to the head's data.
        // Insert the new node at the beginning.
        if (head.data >= data) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            return;
        }

        // Case 3: The new node's data is greater than the tail's data.
        // Insert the new node at the end.
        if (tail.data <= data) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            return;
        }

        // Case 4: Find the correct position to insert in the middle.
        Node current = head;
        // Traverse until we find a node whose data is greater than the new node's data.
        while (current != null && current.data < data) {
            current = current.next;
        }

        // Insert the new node just before the 'current' node.
        newNode.next = current;
        newNode.prev = current.prev;
        current.prev.next = newNode;
        current.prev = newNode;
    }

    /**
     * Deletes the first occurrence of a node with the given data from the list.
     *
     * @param data The data of the node to be deleted.
     */
    public void delete(int data) {
        if (head == null) {
            System.out.println("List is empty. Cannot delete.");
            return;
        }

        Node current = head;
        // Find the node to be deleted.
        while (current != null && current.data != data) {
            current = current.next;
        }

        // If the node was not found
        if (current == null) {
            System.out.println("Node with data " + data + " not found.");
            return;
        }

        // If the node to be deleted is the head
        if (current == head) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else { // The list is now empty
                tail = null;
            }
        }
        // If the node to be deleted is the tail
        else if (current == tail) {
            tail = tail.prev;
            tail.next = null;
        }
        // If the node is in the middle
        else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
    }

    /**
     * Displays the contents of the list from head to tail.
     */
    public void displayForward() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        Node temp = head;
        System.out.print("Forward: null <- ");
        while (temp != null) {
            System.out.print(temp.data + " <-> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    /**
     * Searches for a given key in the sorted list.
     * The search can stop early if an element greater than the key is found.
     *
     * @param key The value to search for.
     * @return true if the key is found, false otherwise.
     */
    public boolean search(int key) {
        Node temp = head;
        while (temp != null) {
            // If we find the key, return true.
            if (temp.data == key) {
                return true;
            }
            // Since the list is sorted, if we encounter a number greater than the key,
            // the key cannot be in the rest of the list.
            if (temp.data > key) {
                return false;
            }
            temp = temp.next;
        }
        // Key was not found.
        return false;
    }

    public static void main(String[] args) {
        SortedLinkedList2 sdll = new SortedLinkedList2();

        System.out.println("Inserting elements (40, 20, 50, 10, 30)...");
        sdll.insert(40);
        sdll.insert(20);
        sdll.insert(50);
        sdll.insert(10);
        sdll.insert(30);

        System.out.print("List after insertions: ");
        sdll.displayForward(); // Expected: 10 <-> 20 <-> 30 <-> 40 <-> 50

        System.out.println("\nDeleting head node (10)...");
        sdll.delete(10);
        sdll.displayForward(); // Expected: 20 <-> 30 <-> 40 <-> 50

        System.out.println("\nDeleting tail node (50)...");
        sdll.delete(50);
        sdll.displayForward(); // Expected: 20 <-> 30 <-> 40

        System.out.println("\nDeleting a middle node (30)...");
        sdll.delete(30);
        sdll.displayForward(); // Expected: 20 <-> 40

        System.out.println("\nAttempting to delete a non-existent node (99)...");
        sdll.delete(99);
        sdll.displayForward(); // Expected: 20 <-> 40

        System.out.println("\nSearching for elements...");
        System.out.println("Search for 40: " + sdll.search(40)); // Expected: true
        System.out.println("Search for 99: " + sdll.search(99)); // Expected: false
    }
}
