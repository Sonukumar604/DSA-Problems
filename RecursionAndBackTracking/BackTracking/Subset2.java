import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subset2 { // Renamed class to match the file name "Subset2.java"
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }
    private void backtrack(int[] nums, int start, List<Integer> current, List<List<Integer>> result){
        // Add the current subset to the result list. A new list is created to avoid modification by reference.
        result.add(new ArrayList<>(current));

        for(int i = start; i < nums.length; i++){
            // This is the key to handling duplicates.
            // If the current element is the same as the previous one, and we are not at the start of the loop for this level,
            // we skip it to avoid generating a duplicate subset.
            if(i > start && nums[i] == nums[i - 1]) continue; // skip duplicates
            current.add(nums[i]);
            backtrack(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
    public static void main(String[] args) {
        Subset2 s = new Subset2(); // Corrected the class instantiation
        int[] nums = {1,2,1};
        System.out.println("Subsets of " + Arrays.toString(nums) + " : " + s.subsetsWithDup(nums));
    }
}
