import java.util.ArrayList;
import java.util.Arrays;

public class TugOfWar { // The main class can be a simple entry point

    // Static variables to hold the state of the best solution found so far.
    private static int minDiff = Integer.MAX_VALUE;
    private static ArrayList<Integer> bestSubset1 = new ArrayList<>();
    private static ArrayList<Integer> bestSubset2 = new ArrayList<>();

    /**
     * A classic backtracking method to find the optimal partition.
     * It explores placing each element into one of two subsets and updates static
     * variables when a better partition is found.
     */
    public static void solve(int[] arr, int currentIndex, ArrayList<Integer> subset1, ArrayList<Integer> subset2, int sum1, int sum2) {
        int n = arr.length;

        // Base case: if we have processed all elements, check if this partition is the best so far.
        if (currentIndex == n) {
            int diff = Math.abs(sum1 - sum2);
            if (diff < minDiff) {
                minDiff = diff;
                // Store copies of the best subsets found.
                bestSubset1 = new ArrayList<>(subset1);
                bestSubset2 = new ArrayList<>(subset2);
            }
            return;
        }
        // Option 1: Add the current element to subset1, if it has space.
        if (subset1.size() < (n + 1) / 2) {
            subset1.add(arr[currentIndex]);
            solve(arr, currentIndex + 1, subset1, subset2, sum1 + arr[currentIndex], sum2);
            subset1.remove(subset1.size() - 1); // Backtrack: undo the choice.
        }

        // Option 2: Add the current element to subset2, if it has space.
        if (subset2.size() < (n + 1) / 2) {
            subset2.add(arr[currentIndex]);
            solve(arr, currentIndex + 1, subset1, subset2, sum1, sum2 + arr[currentIndex]);
            subset2.remove(subset2.size() - 1); // Backtrack: undo the choice.
        }
    }

    public static void main(String[] args) {
        int[] arr = {23, 45, -34, 12, 0, 98, -99, 4, 189, -1, 4};
        System.out.println("Input Array: " + Arrays.toString(arr));
        solve(arr, 0, new ArrayList<>(), new ArrayList<>(), 0, 0);
        System.out.println("The two subsets with the minimum sum difference are:");
        System.out.println("Subset 1: " + bestSubset1 + "\nSubset 2: " + bestSubset2);
        System.out.println("Minimum Difference: " + minDiff);

        // Because the state variables are static, we must reset them before the next run.
        minDiff = Integer.MAX_VALUE;
        bestSubset1 = new ArrayList<>();
        bestSubset2 = new ArrayList<>();

        int[] arr2 = {1, 2, 3, 4};
        System.out.println("\n----------------------------------\n");
        System.out.println("Input Array: " + Arrays.toString(arr2));
        solve(arr2, 0, new ArrayList<>(), new ArrayList<>(), 0, 0);
        System.out.println("The two subsets with the minimum sum difference are:");
        System.out.println("Subset 1: " + bestSubset1 + "\nSubset 2: " + bestSubset2);
        System.out.println("Minimum Difference: " + minDiff);
    }
}
