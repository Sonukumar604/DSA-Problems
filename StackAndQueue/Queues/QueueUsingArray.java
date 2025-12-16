public class QueueUsingArray {
    private int[] queue;
    private int front, rear, size;
    public QueueUsingArray(int capacity){
        queue = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }
    public void enqueue(int value){
        if(size == queue.length){
            throw new IllegalStateException("Queue Overflow: ");
        }
        rear = (rear + 1) % queue.length;
        queue[rear] = value;
        size++;
    }
    public int dequeue(){
        if(size == 0){
            throw new IllegalStateException("Queue Underflow: ");
        }
        int removedValue = queue[front];
        front = (front + 1) % queue.length;
        size--;
        return removedValue;
    }
    public int front (){
        if(size == 0){
            throw new IllegalStateException("Queue is Empty: ");
        }
        return queue[front];
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public static void main(String[] args) {
        QueueUsingArray queue = new QueueUsingArray(5);
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.println("Front element: " + queue.front());
        System.out.println("Is queue empty? " + queue.isEmpty());
        int dequeuedElement = queue.dequeue();
        System.out.println("Dequeued element: " + dequeuedElement);
        System.out.println("Size: " + queue.size());
        System.out.println("Front element after dequeue: " + queue.front());
    }
}
