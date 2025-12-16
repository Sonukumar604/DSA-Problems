import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
 
public class TwoSumPairsWith0Sum {
    /**
     * Finds all unique pairs of integers in an array that sum to 0.
     *
     * This optimized method uses a single HashSet to achieve O(n) time complexity.
     * It avoids storing duplicate pairs (e.g., [2, -2] and [-2, 2]) without needing a
     * second HashSet for tracking added pairs.
     *
     * @param arr The input integer array.
     * @return A List of Lists, where each inner list is a pair of integers summing to 0.
     *         The pairs are sorted internally (e.g., [-2, 2]).
     */
    public static List<List<Integer>> getPairs(int[] arr){
        Set<Integer> seen = new HashSet<>();
        // Use a Set of Lists to automatically handle duplicate pairs.
        Set<List<Integer>> resultPairs = new HashSet<>();

        for(int num : arr){
            int complement = -num;
            // If we've seen the complement before, we have a pair.
            if(seen.contains(complement)){
                List<Integer> pair = new ArrayList<>();
                pair.add(Math.min(num, complement));
                pair.add(Math.max(num, complement));
                resultPairs.add(pair); // The set handles uniqueness.
            }
            seen.add(num);
        }

        List<List<Integer>> result = new ArrayList<>(resultPairs);
        // Sort the final list of pairs based on the first element of each pair.
        result.sort(Comparator.comparingInt(pair -> pair.get(0)));
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {2, -3, 3, 4, -2, -4, 0, 1, -1, 0}; // Added a second 0 to test that case
        List<List<Integer>> pairs = getPairs(arr);
        System.out.println("Pairs with sum 0: " + pairs);
    }
}
