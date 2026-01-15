import java.util.*;
public class Recursive{
    //Sort the list recursively
    public static void sort(List<Integer> v){
        if(v.size() <= 1){
            return;
        }
        int temp = v.get(v.size() - 1);
        v.remove(v.size() - 1);
        sort(v);
        insert(v, temp);
    }
    //Insert an element into a sorted list, maintaining the sorted order
    public static void insert (List<Integer> v, int temp){
        //Base case: If the list is empty or the last element is <= temp,
        //we can just add temp
        if(v.isEmpty() || v.get(v.size() - 1) <= temp){
            v.add(temp);
            return;
        }
        int val = v.get(v.size() - 1);
        v.remove(v.size() - 1);
        insert(v, temp);
        v.add(val);
    }
    // Sort the stack recursively
    public static void sort(Stack<Integer> s) {
        // Base case: an empty stack is sorted.
        if (s.isEmpty()) {
            return;
        }
        // Hypothesis: pop an element, sort the rest of the stack,
        // then insert the popped element back in sorted order.
        int temp = s.pop();
        sort(s);
        insert(s, temp);
    }

    // Insert an element into a sorted stack, maintaining the sorted order.
    public static void insert(Stack<Integer> s, int temp) {
        // Base case: if the stack is empty or the top element is <= temp,
        // we can just push temp onto the stack.
        if (s.isEmpty() || s.peek() <= temp) {
            s.push(temp);
            return;
        }
        // Hypothesis: pop the top element, recursively insert temp into the
        // smaller sorted stack, and then push the popped element back.
        int val = s.pop();
        insert(s, temp);
        s.push(val);
    }

    public static void main(String[] args){
        ArrayList<Integer> v = new ArrayList<>(Arrays.asList(5, 2, 9, 1, 5, 6));
        System.out.println("Original List: " + v);
        sort(v);
        System.out.println("Sorted List: " + v);
        System.out.println("---");
        Stack<Integer> s = new Stack<>();
        s.addAll(Arrays.asList(5, 2, 9, 1, 5, 6));
        System.out.println("Original Stack: " + s);
        sort(s);
        System.out.println("Sorted Stack: " + s);
    }
}