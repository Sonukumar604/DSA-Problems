import java.util.ArrayList;
import java.util.Arrays;

public class SumPairClosestToTarget {
    /**
     * Finds the pair of elements in an array whose sum is closest to a given target value.
     * This method uses a two-pointer approach on a sorted array.
     * Time Complexity: O(n log n) due to sorting. O(n) for the two-pointer scan.
     * Space Complexity: O(1) (or O(log n) to O(n) depending on sort implementation).
     *
     * @param arr The input integer array.
     * @param target The target sum.
     * @return An ArrayList containing the two numbers that form the sum closest to the target.
     */
    public ArrayList<Integer> sumClosest(int[] arr, int target){
        ArrayList<Integer> result = new ArrayList<>();
        int n = arr.length;
        if (n < 2) return result; // Not enough elements for a pair

        Arrays.sort(arr); // Sort the array to use the two-pointer technique

        int left = 0, right = n - 1;
        int minDiff = Integer.MAX_VALUE;
        int closestPairFirst = 0, closestPairSecond = 0;

        while (left < right) {
            int currentSum = arr[left] + arr[right];
            int currentDiff = Math.abs(currentSum - target);

            if (currentDiff < minDiff) {
                minDiff = currentDiff;
                closestPairFirst = arr[left];
                closestPairSecond = arr[right];
            }

            if (currentSum < target) left++;
            else right--;
        }
        result.add(closestPairFirst);
        result.add(closestPairSecond);
        return result;
    } 
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 5}; // Sorted: {5, 10, 20, 30}
        int target = 25;
        SumPairClosestToTarget spct = new SumPairClosestToTarget();
        ArrayList<Integer> res = spct.sumClosest(arr, target);
        // Expected pair is {5, 20} with sum 25, which is the closest (diff 0).
        System.out.println("The pair with sum closest to " + target + " is: " + res);
    }
}
