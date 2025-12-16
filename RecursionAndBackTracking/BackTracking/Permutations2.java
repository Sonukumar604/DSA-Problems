import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations2 {
    public static List<List<Integer>> uniquePermutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        Arrays.sort(nums); // Sort to handle duplicates
        boolean[] used = new boolean[nums.length];
        backtrack(nums, used, new ArrayList<>(), result);
        return result;
    }
    public static void backtrack(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(used[i])continue;
            if(i > 0 && nums[i] == nums[i-1] && !used[i-1]) continue;
            used[i] = true;
            current.add(nums[i]);
            backtrack(nums, used, current, result);
            used[i] = false;
            current.remove(current.size() - 1);
        }
    }
    public static void main(String[] args) {
        int[] nums = {1,1,2};
        List<List<Integer>> result = uniquePermutations(nums);
        System.out.println(result);
    }
}