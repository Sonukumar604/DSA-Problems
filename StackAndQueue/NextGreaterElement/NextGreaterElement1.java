import java.util.Stack;

public class NextGreaterElement1 {
    public static int[] FindNextGreaterElements(int[] nums){
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        for(int i = n - 1; i >= 0; i--){
            while(!stack.isEmpty() && nums[stack.peek()] <= nums[i]){
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : nums[stack.peek()];
            stack.push(i);
        }
        return result;
    }
    public static void main(String[] args) {
        int[] nums = {4, 5, 2, 10, 8};
        int[] nextGreaterElements = FindNextGreaterElements(nums);
        for (int element : nextGreaterElements) {
            System.out.print(element + " ");
        }
    }
}
