public class SortedCircularSinglyLinkedList {

    // Inner class for the node
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;

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

        // Case 1: List is empty
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            tail.next = head; // Points to itself
            return;
        }

        // Case 2: New node should be the new head
        if (data <= head.data) {
            newNode.next = head;
            head = newNode;
            tail.next = head; // The last node must point to the new head
            return;
        }

        // Case 3: New node should be the new tail
        if (data >= tail.data) {
            tail.next = newNode;
            tail = newNode;
            tail.next = head; // The new tail points back to the head
            return;
        }

        // Case 4: Insert in the middle
        Node current = head;
        // Traverse to find the node just before the insertion point
        while (current.next.data < data) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
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

        // Case 1: Deleting the only node in the list
        if (head == tail && head.data == data) {
            head = null;
            tail = null;
            return;
        }

        // Case 2: Deleting the head node
        if (head.data == data) {
            head = head.next;
            tail.next = head; // Tail points to the new head
            return;
        }

        // Case 3: Deleting a middle or tail node
        Node prev = head;
        Node current = head.next;

        // Traverse to find the node to delete, stopping before we loop completely
        while (current != head && current.data != data) {
            prev = current;
            current = current.next;
        }

        // If the node was found (i.e., the loop didn't finish because current == head)
        if (current.data == data) {
            // If we are deleting the tail, we must update the tail pointer
            if (current == tail) {
                tail = prev;
            }
            prev.next = current.next; // Unlink the node
        } else {
            System.out.println("Node with data " + data + " not found.");
        }
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
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        } while (temp != head);
        System.out.println("(back to head " + head.data + ")");
    }

    public static void main(String[] args) {
        SortedCircularSinglyLinkedList scsll = new SortedCircularSinglyLinkedList();
        System.out.println("Inserting elements (40, 20, 50, 10, 30)...");
        scsll.insert(40);
        scsll.insert(20);
        scsll.insert(50);
        scsll.insert(10);
        scsll.insert(30);
        scsll.display();

        System.out.println("\nDeleting head node (10)...");
        scsll.delete(10);
        scsll.display();

        System.out.println("\nDeleting tail node (50)...");
        scsll.delete(50);
        scsll.display();

        System.out.println("\nDeleting a middle node (30)...");
        scsll.delete(30);
        scsll.display();

        System.out.println("\nAttempting to delete a non-existent node (99)...");
        scsll.delete(99);
        scsll.display();

        System.out.println("\nDeleting remaining nodes...");
        scsll.delete(20);
        scsll.delete(40);
        scsll.display();

        // Re-populate for search test
        System.out.println("\nRe-populating list for search test...");
        scsll.insert(30);
        scsll.insert(10);
        scsll.insert(20);
        scsll.display();

        System.out.println("\nSearching for elements...");
        System.out.println("Search for 20: " + scsll.search(20)); // Expected: true
        System.out.println("Search for 15: " + scsll.search(15)); // Expected: false
        System.out.println("Search for 99: " + scsll.search(99)); // Expected: false
    }
}
