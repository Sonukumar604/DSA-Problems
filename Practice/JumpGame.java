import java.util.Arrays;

public class JumpGame {
    // min number of jumps to reach the end
    //Dynamic Programming Approach
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (j + nums[j] >= i && dp[j] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        // If dp[n-1] is still MAX_VALUE, it's unreachable. Return -1 for consistency.
        return dp[n - 1] == Integer.MAX_VALUE ? -1 : dp[n - 1];

    }
    // Greedy Approach
    public int minJumps(int[] arr) {
        
        // code here
        int n = arr.length;
        int totalJumps = 0;
        int dest = n - 1;
        int cov = 0, ljumpIdx = 0;
        if(n == 1) return 0;
        for(int i = 0; i < n; i++){
            if (i > cov) { // If we can't reach the current index 'i', we can't go further.
                return -1;
            }
            cov = Math.max(cov, i + arr[i]);
            if(i == ljumpIdx){
                ljumpIdx = cov;
                totalJumps++;
                if(cov >= dest){
                    return totalJumps;
                }
            }
        }
        return -1; // Should not be reached if the logic is correct and dest is reachable
    }

    public static void main(String[] args) {
        JumpGame jumpGame = new JumpGame();

        // Test case 1: Reachable end
        int[] arr1 = {2, 3, 1, 1, 4};
        System.out.println("Testing with array: " + Arrays.toString(arr1));
        System.out.println("DP Approach: " + jumpGame.jump(arr1.clone())); // Expected: 2
        System.out.println("Greedy Approach: " + jumpGame.minJumps(arr1.clone())); // Expected: 2
        System.out.println("--------------------");

        // Test case 2: Unreachable end
        int[] arr2 = {3, 2, 1, 0, 4};
        System.out.println("Testing with array: " + Arrays.toString(arr2));
        System.out.println("DP Approach: " + jumpGame.jump(arr2.clone())); // Expected: -1
        System.out.println("Greedy Approach: " + jumpGame.minJumps(arr2.clone())); // Expected: -1
        System.out.println("--------------------");

        // Test case 3: Single element
        int[] arr3 = {0};
        System.out.println("Testing with array: " + Arrays.toString(arr3));
        System.out.println("DP Approach: " + jumpGame.jump(arr3.clone())); // Expected: 0
        System.out.println("Greedy Approach: " + jumpGame.minJumps(arr3.clone())); // Expected: 0
        System.out.println("--------------------");
    }
}
