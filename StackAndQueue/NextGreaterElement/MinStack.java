
import java.util.Stack;

public class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;
    public MinStack(){
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    public void push(int val){
        stack.push(val);
        if(minStack.isEmpty() && val <= stack.peek()){
            minStack.push(val);
        }
    }
    public void pop(){
        if(stack.isEmpty()){
            return;
        }
        int popped = stack.pop();
        if(popped == minStack.peek()){
            minStack.pop();
        }
    }
    public int top(){
        if(stack.isEmpty()){
            return -1;
        }
        return stack.peek();
    }
    public int getMin(){
        if(minStack.isEmpty()){
            return -1;
        }
        return minStack.peek();
    }
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println("Minimum element: " + minStack.getMin());
        minStack.pop();
        System.out.println("Top element: " + minStack.top());
        minStack.push(1);
        System.out.println("Minimum element: " + minStack.getMin());
    }
}
