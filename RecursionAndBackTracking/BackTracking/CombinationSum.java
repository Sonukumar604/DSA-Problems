import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    public static List<List<Integer>> combinationSum(int[] nums, int target){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        backtrack(nums, target, 0, current, result);
        return result;
    }
    public static void backtrack(int[] nums, int target, int start, List<Integer> current, List<List<Integer>> result){
        if(target < 0){
            return;
        }
        if(target == 0){
            // Add a copy of the current combination to the result list
            result.add(new ArrayList<>(current));
            return;
        }
        for(int i = start; i < nums.length; i++){
            current.add(nums[i]);
            // The same element can be used multiple times, so we pass 'i', not 'i + 1'
            backtrack(nums, target - nums[i], i, current, result);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {3, 4, 5}; // Using a more common example set
        int target = 16;
        List<List<Integer>> result = combinationSum(nums, target);
        System.out.println("Combinations that sum to " + target + ": " + result);
    }
}
