import java.util.Stack;
public class QueueUsingStack2 {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();
    public void enqueue(int x){
        stack1.push(x);
    }
    public int dequeue(){
        if(stack2.isEmpty()){
            if(stack1.isEmpty()){
                System.out.println("Queue is empty");
                return -1;
            }
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
    public int peek(){
        if(stack2.isEmpty()){
            if(stack1.isEmpty()){
                System.out.println("Queue is Empty");
                return -1;
            }
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }
    public static void main(String[] args) {
        QueueUsingStack2 q = new QueueUsingStack2();
        q.enqueue(10);
        q.enqueue(20);
        System.out.println("Front: " + q.peek());
        System.out.println("Dequeued: " + q.dequeue());
        System.out.println("Front: " + q.peek());
    }
}
