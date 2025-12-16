public class QueueUsingLinkedList {
    private static class Node{
        int data;
        Node next;
        Node(int data){
            this.data = data;
            this.next = null;
        }
    }
    private Node front, rear;
    private int size;
    public QueueUsingLinkedList(){
        front = rear = null;
        size = 0;
    }
    public void enqueue(int value){
        Node newNode = new Node(value);
        if(rear == null){
            front = rear = newNode;
        }else{
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }
    public int dequeue(){
        if(front == null){
            throw new IllegalStateException("Queue Underflow: ");
        }
        int removedData = front.data;
        front = front.next;
        if(front == null){
            rear = null;
        }
        size--;
        return removedData;
    }
    public int front(){
        if(front == null){
            throw new IllegalStateException("Queue is Empty: ");
        }
        return front.data;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public static void main(String[] args) {
        QueueUsingLinkedList queue = new QueueUsingLinkedList();
        queue.enqueue(10);
        queue.enqueue(20);
        System.out.println("Front element: " + queue.front());
        System.out.println("Is queue empty? " + queue.isEmpty());
        int dequeuedElement = queue.dequeue();
        System.out.println("Dequeued element: " + dequeuedElement);
        System.out.println("Size: " + queue.size());
        System.out.println("Front element after dequeue: " + queue.front());
    }
}
