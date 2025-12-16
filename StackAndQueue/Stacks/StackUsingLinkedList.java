
public class StackUsingLinkedList {

    // 1. The Node class is now a private, static inner class.
    // It only contains what a Node needs: data and a 'next' pointer.
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // 2. The stack's properties are moved here, to the main class.
    private Node top;
    private int size;

    // 3. The constructor for the StackUsingLinkedList.
    public StackUsingLinkedList() {
        top = null;
        size = 0;
    }

    // 4. The 'push' method, now part of the main class.
    public void push(int value) {
        Node newNode = new Node(value);
        newNode.next = top;
        top = newNode;
        size++;
    }

    // 5. The 'pop' method, corrected and moved.
    public int pop() {
        if (isEmpty()) {
            // Using a more consistent exception message.
            throw new IllegalStateException("Stack Underflow");
        }
        int poppedData = top.data;
        // This was the missing line to actually remove the element.
        top = top.next;
        size--;
        return poppedData;
    }

    // 6. The 'top' (or peek) method.
    public int top() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return top.data;
    }

    public int size() {
        return size;
    }

    // 7. The missing 'isEmpty' method is now implemented.
    public boolean isEmpty() {
        return top == null;
    }

    // 8. A more complete main method to test the functionality.
    public static void main(String[] args) {
        StackUsingLinkedList stl = new StackUsingLinkedList();
        stl.push(10);
        stl.push(20);
        System.out.println("Top element: " + stl.top());
        System.out.println("Stack size: " + stl.size());
        int popped = stl.pop();
        System.out.println("Popped element: " + popped);
        System.out.println("New top element: " + stl.top());
        System.out.println("Is stack empty? " + stl.isEmpty());
    }
}
