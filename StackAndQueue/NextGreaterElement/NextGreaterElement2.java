import java.util.Stack;
import java.util.Arrays;
public class NextGreaterElement2 {
    public static int[] nextGreaterElements(int[] nums){
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        Arrays.fill(result, -1);
        for(int i = 2 * n - 1; i >= 0; i--){
            int curr = nums[i % n];// Current element
            while(!stack.isEmpty() && nums[stack.peek()] <= curr){
                stack.pop();
            }
            if(i < n){
                result[i] = stack.isEmpty() ? -1 : nums[stack.peek()];
                stack.push(curr);
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 1};
        int[] result = nextGreaterElements(nums);
        System.out.println("Next Greater Circular Elements: ");
        for(int val : result){
            System.out.print(val + " ");
        }
    }
}
