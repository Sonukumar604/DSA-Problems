import java.util.Stack;

public class ReverseAStackUsingRecursion {
    /**
     * The main recursive function to reverse a stack.
     * It pops the top element, recursively reverses the rest,
     * and then inserts the popped element at the bottom.
     */
    public static void reverse(Stack<Integer> s){
        //Base case
        if(s.isEmpty()){
            return;
        }
        //Hypothesis
        // 1. Remove the top element.
        int temp = s.pop();
        // 2. Reverse the remaining smaller stack.
        reverse(s);
        
        //Induction
        // 3. Insert the removed element at the bottom of the now-reversed stack.
        insertAtBottom(s, temp);
    }

    /**
     * A helper function to insert an element at the bottom of a stack.
     */
    public static void insertAtBottom(Stack<Integer> s, int ele){
        //Base case
        if(s.isEmpty()){
            s.push(ele);
            return;
        }
        //Hypothesis
        // 1. Pop the top element to get access to the rest of the stack.
        int temp = s.pop();
        // 2. Recursively call to insert the element at the bottom of the smaller stack.
        insertAtBottom(s, ele);
        
        //Induction
        // 3. Push the original top element back on.
        s.push(temp);
    }
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
        System.out.println("The original stack: " + s); // [1, 2, 3, 4, 5]
        reverse(s);
        System.out.println("Stack after the reverse: " + s); // [5, 4, 3, 2, 1]
    }
}
