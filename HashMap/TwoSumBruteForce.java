import java.util.Arrays;
public class TwoSumBruteForce {
    public static int[] findTwoSum(int[] nums, int target){
        if(nums == null || nums.length < 2){
            throw new IllegalArgumentException("Input array must contain at least two numbers.");
        }
        for(int i = 0; i < nums.length - 1; i++){
            for(int j = i + 1; j < nums.length; j++){
                if(nums[i] + nums[j] == target){
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution found for the given target.");
    }
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = findTwoSum(nums, target);
        System.out.println("Output: " + Arrays.toString(result));
    }
}
