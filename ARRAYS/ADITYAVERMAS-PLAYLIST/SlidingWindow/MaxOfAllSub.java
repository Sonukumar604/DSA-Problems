import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class MaxOfAllSub {
    /**
     * Finds the maximum element in each subarray of size k.
     * This implementation uses a Deque to efficiently track the maximums in a sliding window.
     *
     * @param arr The input array of integers.
     * @param k The size of the sliding window.
     * @return A list containing the maximum of each subarray.
     */
    public static List<Integer> maxOfAllSub(int[] arr, int k){
        int i = 0, j = 0;
        List<Integer> result = new ArrayList<>();
        // The Deque stores indices of elements that are potential maximums.
        // It's maintained in a way that the front always has the index of the largest element.
        Deque<Integer> dq = new ArrayDeque<>();

        while(j < arr.length){
            // Before adding the current element's index, remove any indices from the back
            // of the deque that correspond to elements smaller than the current element.
            // This maintains the decreasing order property of the deque.
            while(!dq.isEmpty() && arr[dq.peekLast()] < arr[j]){
                dq.pollLast();
            }
            dq.offerLast(j); // Add current element's index at the end.

            // If the window size is not yet reached, just expand the window.
            if(j - i + 1 < k){
                j++;
            }
            // When the window size is reached, we process it.
            else if(j - i + 1 == k){
                // The element at the front of the deque is the largest in the current window.
                result.add(arr[dq.peekFirst()]);

                // As we slide the window, if the maximum element is the one we are leaving behind,
                // remove its index from the front of the deque.
                if(dq.peekFirst() == i){
                    dq.pollFirst();
                }
                // Slide the window forward.
                i++;
                j++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {9, 7, 4, 8, 6, 3, 0, 1};
        int k = 3;
        // The method returns a List<Integer>, so the variable must be of the same type.
        List<Integer> maxValues = maxOfAllSub(arr, k);
        System.out.println("The maximums of all subarrays of size " + k + " are: " + maxValues);
    }
}
