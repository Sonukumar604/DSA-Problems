import java.util.Stack;
public class QueueUsingStack1 {
    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();
    public void enqueue(int x){
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        stack1.push(x);
        while(!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
    }
    public int dequeue(){
        if(stack1.isEmpty()){
            System.out.println("Queue is Empty");
            return -1;
        }
        return stack1.pop();
    }
    public int peek(){
        if(stack1.isEmpty()){
            System.out.println("Queue is Empty");
            return -1;
        }
        return stack1.peek();
    }
    public boolean isEmpty(){
        return stack1.isEmpty();
    }
    public static void main(String[] args) {
        QueueUsingStack1 queue = new QueueUsingStack1();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println("Peek: " + queue.peek());
        System.out.println("Is Empty: " + queue.isEmpty());
    }
}
