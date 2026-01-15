import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permuteHelper(nums, 0, result);
        return result;
    }

    private void swap(int[] num, int i, int j) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }

    /**
     * A helper method that generates permutations using backtracking and in-place swaps.
     * @param nums The array being permuted.
     * @param start The current index to start swapping from.
     * @param result The list to store all permutations.
     */
    private void permuteHelper(int[] nums, int start, List<List<Integer>> result) {
        // Base case: if we've reached the end of the array, we have a complete permutation.
        if (start == nums.length) {
            // Convert the array to a List and add it to the result.
            List<Integer> currentPermutation = new ArrayList<>();
            for (int num : nums) {
                currentPermutation.add(num);
            }
            result.add(currentPermutation);
            return;
        }
        for (int i = start; i < nums.length; i++) {
            swap(nums, start, i); // Swap the current element with the start element.
            permuteHelper(nums, start + 1, result); // Recurse for the rest of the array.
            swap(nums, start, i); // Backtrack: swap back to the original state.
        }
    }

    public static void main(String[] args) {
        Permutations p = new Permutations();
        int[] nums = {1, 2, 3};
        System.out.println("Permutations of " + Arrays.toString(nums) + ": " + p.permute(nums));
    }
}
