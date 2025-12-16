import java.util.Stack;
import java.util.List;

public class DeleteMidElementOfStack {
    /**
     * Recursively removes elements until the k-th element is found and popped.
     * Then, it pushes the elements back onto the stack.
     */
    public static void solve(Stack<Integer> s, int k) {
        // Base case: we've reached the element to delete.
        if(k == 1) {
            s.pop();
            return;
        }
        //Hypothesis
        int temp = s.peek();
        s.pop();
        solve(s, k - 1);
        // Induction: push the element back after the recursive call returns.
        s.push(temp);
    }

    // Public method to start the deletion of the middle element.
    public static void deleteMid(Stack<Integer> s) {
        // Base case: do nothing for an empty stack.
        if(s.isEmpty()){
            return;
        }
        // Calculate the position of the middle element (1-based from the top).
        int k = s.size() / 2 + 1;
        solve(s, k);
    }
    public static void main(String[] args){
        Stack<Integer> myStack = new Stack<>();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
        System.out.println("Original Stack: " + myStack);
        deleteMid(myStack);
        System.out.println("Stack after deleting mid: " + myStack);
    }
}
