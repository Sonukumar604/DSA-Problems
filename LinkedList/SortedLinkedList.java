public class SortedLinkedList {

    // Inner class to represent a node in the linked list
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;

    /**
     * Inserts a new node with the given data into the list while maintaining
     * the sorted (ascending) order.
     *
     * @param data The data for the new node.
     */
    public void insert(int data) {
        Node newNode = new Node(data);

        // Case 1: The list is empty or the new node's data is smaller than the head's data.
        // In this case, the new node becomes the new head.
        if (head == null || head.data >= newNode.data) {
            newNode.next = head;
            head = newNode;
            return;
        }

        // Case 2: The new node's data is greater than the head's data.
        // We need to find the correct position to insert the new node.
        Node current = head;
        // Traverse the list to find the node just before the insertion point.
        // The loop stops when we find a node whose 'next' node has data greater than
        // the new node's data, or when we reach the end of the list.
        while (current.next != null && current.next.data < newNode.data) {
            current = current.next;
        }

        // Insert the new node after the 'current' node.
        newNode.next = current.next;
        current.next = newNode;
    }

    /**
     * Deletes the first occurrence of a node with the given data from the list.
     *
     * @param data The data of the node to be deleted.
     */
    public void delete(int data) {
        // Case 1: The list is empty.
        if (head == null) {
            System.out.println("List is empty. Cannot delete.");
            return;
        }

        // Case 2: The node to be deleted is the head.
        if (head.data == data) {
            head = head.next;
            return;
        }

        // Case 3: The node to be deleted is not the head.
        // We need to find the node just before the one to be deleted.
        Node current = head;
        while (current.next != null && current.next.data != data) {
            current = current.next;
        }

        // If the node was found (i.e., we didn't reach the end of the list)
        if (current.next != null) {
            // Unlink the node to be deleted
            current.next = current.next.next;
        } else {
            // If the loop finished, the node was not in the list.
            System.out.println("Node with data " + data + " not found in the list.");
        }
    }

    /**
     * Displays the contents of the linked list.
     */
    public void display() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
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
        SortedLinkedList sll = new SortedLinkedList();

        System.out.println("Inserting elements (40, 20, 50, 10, 30) into the sorted list...");
        sll.insert(40);
        sll.insert(20);
        sll.insert(50);
        sll.insert(10);
        sll.insert(30);
        System.out.print("List after insertions: ");
        sll.display();

        System.out.println("\nDeleting head node (10)...");
        sll.delete(10);
        System.out.print("List after deleting 10: ");
        sll.display();

        System.out.println("\nDeleting a middle node (40)...");
        sll.delete(40);
        System.out.print("List after deleting 40: ");
        sll.display();

        System.out.println("\nDeleting tail node (50)...");
        sll.delete(50);
        System.out.print("List after deleting 50: ");
        sll.display();

        System.out.println("\nAttempting to delete a non-existent node (99)...");
        sll.delete(99);
        System.out.print("List remains unchanged: ");
        sll.display();
    }
}
