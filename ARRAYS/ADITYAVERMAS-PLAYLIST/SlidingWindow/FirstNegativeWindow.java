import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List; // Added missing import for List

public class FirstNegativeWindow {
    public static List<Integer> firstNegativeInWindow(int[] arr, int k){
        
        int i = 0, j = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();
        while(j < arr.length){
            if(arr[j] < 0){
                dq.addLast(arr[j]);
            }
            if(j - i + 1 < k){
                j++;
            }
            else if(j - i + 1 == k){
                if(dq.isEmpty()){
                    result.add(0);
                }else{
                    result.add(dq.peekFirst());
                }
                if(!dq.isEmpty() && dq.peekFirst() == arr[i]){
                    dq.removeFirst();
                }
                i++;
                j++;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int[] arr = {1, -2, 3, -4, 5, -6, 7};
        int k = 3;
        List<Integer> result = firstNegativeInWindow(arr, k); // Corrected type from int to List<Integer>
        System.out.println("The first negative in each window of size " + k + " is: " + result);
    }
}
