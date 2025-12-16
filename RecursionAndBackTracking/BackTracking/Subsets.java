import java.util.ArrayList;
import java.util.List;

public class Subsets {
    public List<List<Integer>> subsets(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        backtrack(nums, 0, current, result);
        return result;
    }

    private void backtrack(int[] nums, int start, List<Integer> current, List<List<Integer>> result){
        result.add(new ArrayList<>(current));

        for(int i = start; i < nums.length; i++){
            current.add(nums[i]);
            backtrack(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
    public static void main(String[] args) {
        Subsets subsets = new Subsets();
        int[] nums = {1,2,3};
        List<List<Integer>> result = subsets.subsets(nums);
        for(List<Integer> subset : result){
            System.out.println(subset);
        }
    }
}
