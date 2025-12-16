import java.util.HashMap;
import java.util.Map;

/**
 * Implements a Least Recently Used (LRU) Cache.
 * This implementation uses a combination of a HashMap and a Doubly Linked List
 * to achieve O(1) time complexity for both `get` and `put` operations.
 *
 * - HashMap: Provides fast O(1) lookup of cache entries by key. The map stores
 *   the key and a reference to the corresponding Node in the linked list.
 * - Doubly Linked List: Maintains the order of usage. The most recently used
 *   item is at the head, and the least recently used item is at the tail.
 */
public class ImplementLRU {

    // Inner class for the nodes of the doubly linked list.
    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> cache;
    private final Node head; // Dummy head of the list
    private final Node tail; // Dummy tail of the list

    public ImplementLRU(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        // Initialize dummy head and tail to simplify list operations
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    /** Retrieves the value for a given key, and marks it as most recently used. */
    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1; // Key not found
        }
        // Move the accessed node to the head to mark it as most recently used
        moveToHead(node);
        return node.value;
    }
    /** Inserts or updates a key-value pair, marking it as most recently used. */
    public void put(int key, int value) {
        Node node = cache.get(key);

        if (node != null) {
            // Key already exists, update its value and move it to the head
            node.value = value;
            moveToHead(node);
        } else {
            // Key is new, create a new node
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addNode(newNode);

            // If capacity is exceeded, evict the least recently used item (from the tail)
            if (cache.size() > capacity) {
                Node tailNode = popTail();
                cache.remove(tailNode.key);
            }
        }
    }

    // Helper to add a node right after the dummy head
    private void addNode(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    // Helper to remove a node from its current position in the list
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Helper to move an existing node to the head
    private void moveToHead(Node node) {
        removeNode(node);
        addNode(node);
    }

    // Helper to remove and return the node just before the dummy tail (the LRU node)
    private Node popTail() {
        Node lruNode = tail.prev;
        removeNode(lruNode);
        return lruNode;
    }

    public static void main(String[] args) {
        System.out.println("--- LRU Cache with capacity 2 ---");
        ImplementLRU cache = new ImplementLRU(2);

        cache.put(1, 1);    // cache is {1=1}
        System.out.println("put(1, 1)");
        cache.put(2, 2);    // cache is {1=1, 2=2}
        System.out.println("put(2, 2)");
        System.out.println("get(1): " + cache.get(1));       // returns 1. cache is {2=2, 1=1} (1 is now most recent)

        cache.put(3, 3);    // LRU key 2 was evicted. cache is {1=1, 3=3}
        System.out.println("put(3, 3) -> Evicts key 2");
        System.out.println("get(2): " + cache.get(2));       // returns -1 (not found)

        cache.put(4, 4);    // LRU key 1 was evicted. cache is {3=3, 4=4}
        System.out.println("put(4, 4) -> Evicts key 1");
        System.out.println("get(1): " + cache.get(1));       // returns -1 (not found)
        System.out.println("get(3): " + cache.get(3));       // returns 3
        System.out.println("get(4): " + cache.get(4));       // returns 4
    }
}
