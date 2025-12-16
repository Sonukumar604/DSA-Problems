import java.util.LinkedList;
import java.util.Queue;
public class StackUsingQueue {
    private Queue<Integer> queue;
    public StackUsingQueue(){
        queue = new LinkedList<>();
    }
    public void push(int value){
        queue.add(value);
        int sizeToRotate = queue.size() - 1;
        // Rotate the queue to make the new element the front
        while(sizeToRotate > 0){
            queue.add(queue.remove());
            sizeToRotate--;
        }
    }
    public int pop(){
        if(queue.isEmpty()){
            throw new IllegalStateException("Stack is empty");
        }
        return queue.remove();
    }
    public int top(){
        if(queue.isEmpty()){
            throw new IllegalStateException("Stack is empty");
        }
        return queue.peek();
    }
    public int size(){
        return queue.size();
    }
    public boolean isEmpty() {
        return queue.isEmpty();
    }
    public static void main(String[] args) {
        StackUsingQueue stack = new StackUsingQueue();
        stack.push(10);
        stack.push(20);
        System.out.println("Top element: " + stack.top());
        System.out.println("Stack size: " + stack.size());
        int poppedElement = stack.pop();
        System.out.println("Popped element: " + poppedElement);
        System.out.println("Top element after pop: " + stack.top());
        System.out.println("Is stack empty? " + stack.isEmpty());
    }
}
