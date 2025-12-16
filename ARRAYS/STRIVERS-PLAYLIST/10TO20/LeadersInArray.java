import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeadersInArray {
    //Brute Force
    //Time Complexity : O(N ^ 2)
    //Space Complexity : O(N) in the worst case (for the answer list)
    public static List<Integer> LeadersInArray(int[] arr){
        int n = arr.length;
        List<Integer> leaders = new ArrayList<>();
        for(int i = 0; i < n; i++){
            boolean isLeader = true;
            for(int j = i + 1; j < n; j++){
                if(arr[j] > arr[i]){
                    isLeader = false;
                    break;
                }
            }
            if(isLeader){
                leaders.add(arr[i]);
            }
        }
        return leaders;
    }
    //Optimal Approach
    //Time Complexity: O(N), Space Complexity: O(N) in the worst case
    public static List<Integer> LeadersInArrayOptimal(int[] arr){
        int n = arr.length;
        List<Integer> leaders = new ArrayList<>();
        // The rightmost element is always a leader.
        int max = Integer.MIN_VALUE;
        // Iterate from the right to the left.
        for(int i = n - 1; i >= 0; i--){
            // If the current element is greater than the max found so far, it's a leader.
            if (arr[i] > max) {
                leaders.add(arr[i]);
                max = arr[i]; // Update the max.
            }
        }
        // The leaders are found in reverse order, so we reverse the list to get the correct order.
        Collections.reverse(leaders);
        return leaders;
    }

    public static void main(String[] args) {
        int[] arr = {10, 22, 12, 3, 0, 6};
        System.out.println("Original Array: " + java.util.Arrays.toString(arr));
        System.out.println("Leaders (Brute Force): " + LeadersInArray(arr));
        System.out.println("Leaders (Optimal): " + LeadersInArrayOptimal(arr));
    }
}
