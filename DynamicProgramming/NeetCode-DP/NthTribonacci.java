import java.util.Arrays;

public class NthTribonacci {
    // Iterative (Bottom-Up) Space-Optimized Approach
    public int tribonacci(int n){
        // Base cases
        if(n == 0) return 0;
        if(n == 1 || n == 2) return 1;

        // Space-optimized DP using three variables to track the last three numbers
        int t0 = 0; // Represents T(i-3)
        int t1 = 1; // Represents T(i-2)
        int t2 = 1; // Represents T(i-1)

        // Iterate from 3 up to n to calculate T(n)
        for(int i = 3; i <= n; i++){
            int current = t0 + t1 + t2; // T(i) = T(i-3) + T(i-2) + T(i-1)
            t0 = t1;
            t1 = t2;
            t2 = current;
        }
        return t2; // t2 now holds T(n)
    }

    // Recursive (Top-Down) with Memoization Approach
    public int tribonacciRecursive(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1); // Use -1 to indicate not yet computed
        return solve(n, memo);
    }

    private int solve(int n, int[] memo) {
        // Base cases
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;

        // If the value is already computed, return it from memo
        if (memo[n] != -1) {
            return memo[n];
        }

        // Recursive step: compute, store, and return
        memo[n] = solve(n - 1, memo) + solve(n - 2, memo) + solve(n - 3, memo);
        return memo[n];
    }

    public static void main(String[] args){
        NthTribonacci nt = new NthTribonacci();
        int n = 4;
        int result = nt.tribonacci(n);
        System.out.println("The " + n + "th Tribonacci number (Iterative): " + result);
        System.out.println("The " + n + "th Tribonacci number (Recursive): " + nt.tribonacciRecursive(n));
    }
}
