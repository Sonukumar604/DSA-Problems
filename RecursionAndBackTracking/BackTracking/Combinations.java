import java.util.ArrayList;
import java.util.List;

public class Combinations {
    public List<List<Integer>> combine(int n, int k){
        List<List<Integer>> result = new ArrayList<>();
        // Start the backtracking process from the number 1.
        backtrack(1, n, k, new ArrayList<>(), result);
        return result;
    }

    /**
     * A helper method that uses backtracking to find all combinations.
     * @param start The starting number to consider for the current combination.
     * @param n The upper bound of the numbers to choose from (inclusive).
     * @param k The desired size of each combination.
     * @param current The current combination being built.
     * @param result The list to store all valid combinations.
     */
    private void backtrack(int start, int n, int k, List<Integer> current, List<List<Integer>> result){
        // Base case: if the current combination has the desired size 'k', add it to the result.
        if(current.size() == k){
            result.add(new ArrayList<>(current));
            return;
        }

        // Explore numbers from 'start' to 'n' to add to the current combination.
        for(int i = start; i <= n; i++){
            // Add the number 'i' to the current combination.
            current.add(i);
            // Recursively call to find the next numbers, starting from 'i + 1'.
            backtrack(i + 1, n, k, current, result);
            // Backtrack: remove the last added number to explore other possibilities.
            current.remove(current.size() - 1);
        }
    }
    public static void main(String[] args) {
        int n = 4, k = 2;
        Combinations c = new Combinations();
        List<List<Integer>> combinations = c.combine(n, k);
        System.out.println("Combinations of " + k + " numbers from 1 to " + n + ": " + combinations);
    }
}
