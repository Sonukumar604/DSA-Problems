
public class StackUsingArray{
    private int[] stack;
    private int top;

    public StackUsingArray(int capacity){
        stack = new int[capacity];
        top = -1;
    }
    public void push(int value){
        if(top == stack.length - 1){
            throw new IllegalStateException("Stack Overflow");
        }
        stack[++top] = value;
    }
    public int pop(){
        if(isEmpty()){
            throw new IllegalStateException("Stack Underflow");
        }
        return stack[top--];
    }
    public int top(){
        if(isEmpty()){
            throw new IllegalStateException("Stack is empty");
        }
        return stack[top];
    }
    public int size(){
        return top + 1;
    }
    public boolean isEmpty() {
        return top == -1;
    }
    public static void main(String[] args) {
        StackUsingArray stack = new StackUsingArray(5);

        System.out.println("Is stack empty? " + stack.isEmpty());

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Top element: " + stack.top());
        System.out.println("Stack size: " + stack.size());
        System.out.println("Is stack empty? " + stack.isEmpty());

        int poppedElement = stack.pop();
        System.out.println("Popped element: " + poppedElement);
        System.out.println("Top element after pop: " + stack.top());
        System.out.println("Stack size after pop: " + stack.size());
    }
}
