public class SortedCircularDoublyLinkedList {

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

    /**
     * Checks if the list is empty.
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Inserts a new node with the given data into the list while maintaining
     * the sorted (ascending) order.
     *
     * @param data The data for the new node.
     */
    public void insert(int data) {
        Node newNode = new Node(data);

        // Case 1: The list is empty.
        if (isEmpty()) {
            head = newNode;
            // The node points to itself in a single-element circular list.
            head.next = head;
            head.prev = head;
            return;
        }

        // Case 2: The new node's data is smaller than or equal to the head's data.
        // It becomes the new head.
        if (data <= head.data) {
            Node tail = head.prev; // The tail is the node just before the head.

            newNode.next = head;
            head.prev = newNode;
            newNode.prev = tail;
            tail.next = newNode; // The old tail must now point to the new head.
            head = newNode;      // Update the head reference.
            return;
        }

        // Case 3: Find the correct position to insert in the middle or at the end.
        Node current = head;
        // Traverse until we find a node whose data is greater than the new node's data,
        // or until we loop back to the head (meaning the new node is the largest).
        while (current.next != head && current.next.data < data) {
            current = current.next;
        }

        // Insert the new node after the 'current' node.
        newNode.next = current.next;
        current.next.prev = newNode;
        current.next = newNode;
        newNode.prev = current;
    }

    /**
     * Deletes the first occurrence of a node with the given data from the list.
     *
     * @param data The data of the node to be deleted.
     */
    public void delete(int data) {
        if (isEmpty()) {
            System.out.println("List is empty. Cannot delete.");
            return;
        }

        Node current = head;
        // Find the node to be deleted.
        do {
            if (current.data == data) {
                break; // Node found
            }
            current = current.next;
        } while (current != head);
        // If we looped through the entire list and didn't find the node
        if (current.data != data) {
            System.out.println("Node with data " + data + " not found.");
            return;
        }
        // If the node to be deleted is the only node in the list
        if (current.next == current) {
            head = null;
            return;
        }
        // If the node to be deleted is the head
        if (current == head) {
            head = head.next;
        }
        // Re-link the previous and next nodes to bypass the 'current' node.
        current.prev.next = current.next;
        current.next.prev = current.prev;
    }

    /**
     * Searches for a given key in the sorted circular list.
     * The search can stop early if an element greater than the key is found.
     *
     * @param key The value to search for.
     * @return true if the key is found, false otherwise.
     */
    public boolean search(int key) {
        if (isEmpty()) {
            return false;
        }
        Node temp = head;
        do {
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
        } while (temp != head);

        // If we complete the loop, the key was not found.
        return false;
    }

    /**
     * Displays the contents of the circular list.
     */
    public void display() {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        Node temp = head;
        System.out.print("List: head -> ");
        do {
            System.out.print(temp.data + " <-> ");
            temp = temp.next;
        } while (temp != head);
        System.out.println("(back to head " + head.data + ")");
    }

    public static void main(String[] args) {
        SortedCircularDoublyLinkedList scdll = new SortedCircularDoublyLinkedList();
        System.out.println("Inserting elements (40, 20, 50, 10, 30)...");
        scdll.insert(40);
        scdll.insert(20);
        scdll.insert(50);
        scdll.insert(10);
        scdll.insert(30);
        scdll.display();

        System.out.println("\nDeleting head node (10)...");
        scdll.delete(10);
        scdll.display();

        System.out.println("\nDeleting tail node (50)...");
        scdll.delete(50);
        scdll.display();

        System.out.println("\nDeleting a middle node (30)...");
        scdll.delete(30);
        scdll.display();

        System.out.println("\nSearching for elements...");
        System.out.println("Search for 20: " + scdll.search(20)); // Expected: true
        System.out.println("Search for 99: " + scdll.search(99)); // Expected: false
        System.out.println("Search for 30: " + scdll.search(30)); // Expected: false
    }
}
