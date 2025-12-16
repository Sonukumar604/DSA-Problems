import java.util.Stack;
public class SubarraySumMinimum2 {
    private static final int MOD = 1_000_000_007;
    public static int sumSubarrayMinsOptimal(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        int n = arr.length;
        //left[i] : number of elements to the left of i (including i)
        //Where arr[i] is the minimum.
        int[] left = new int[n];
        //right[i] = number of elements to the right of i (including i)
        //Where arr[i] is the minimum
        int[] right = new int[n];
        Stack<Integer> stack = new Stack<>();
        //Calculate left boundaries (distance to previous less Element)
        //To handle the duplicates correctly , we use '>' for left and '>=' for right.
        for(int i = 0 ; i < n; i++){
            while(!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                stack.pop();
            }
            left[i] = i - (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }
        stack.clear();
        //Calculate right boundaries (distance to Next Less or Equal Element)
        for(int i = n - 1; i >= 0; i--){
            while(!stack.isEmpty() && arr[stack.peek()] >= arr[i]){
                stack.pop();
            }
            right[i] = (stack.isEmpty() ? n : stack.peek()) - i;
            stack.push(i);
        }
        long totalSum = 0;
        for(int i = 0; i < n; i++){
            long contributition = (long) arr[i] * left[i] * right[i];
            totalSum = (totalSum + contributition) % MOD;
        }
        return (int) totalSum;
    }
    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 4};
        System.out.println("The sum of subarray minimums is : " + sumSubarrayMinsOptimal(arr));
    }
}
