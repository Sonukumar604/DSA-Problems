import java.util.Arrays;

/**
 * Given an integer n, this class calculates the maximum product that can be obtained by breaking it into the sum of k positive integers, where k >= 2.
 *
 * ### Explanation
 * This problem can be solved using dynamic programming. We want to find the maximum product for a given integer `n`. Let's define `solve(n)` as the function that returns this maximum product.
 *
 * To calculate `solve(n)`, we can try every possible first break. We can break `n` into `i + (n - i)`, where `i` ranges from `1` to `n-1`.
 * For each break, we have two choices for the second part, `(n - i)`:
 * 1.  We don't break `(n - i)` any further. In this case, the product is `i * (n - i)`.
 * 2.  We break `(n - i)` further to potentially get an even larger product. The maximum product for breaking `(n - i)` is given by `solve(n - i)`. In this case, the total product is `i * solve(n - i)`.
 *
 * So, for each `i`, the best we can do is `max(i * (n - i), i * solve(n - i))`.
 * We iterate through all possible values of `i` and take the maximum value among them. This gives us the recurrence relation.
 *
 * To avoid recomputing the same subproblems, we use memoization (a top-down DP approach). We store the result for `solve(n)` in a `memo` array.
 *
 * ### Base Cases
 * - `n = 1`: Cannot be broken into a sum of at least two integers. The function returns 0.
 * - `n = 2`: Can be broken into `1 + 1`. The product is `1 * 1 = 1`.
 * - `n = 3`: Can be broken into `1 + 2` (product 2) or `1 + 1 + 1` (product 1). The max is 2.
 *
 * ### Dry Run (n = 5)
 * `solve(5)` is called.
 * `max` is initialized to 0.
 *
 * 1.  **i = 1:**
 *     - `direct = 1 * (5 - 1) = 4`.
 *     - `recursive = 1 * solve(4)`.
 *       - `solve(4)` is called. It computes `max(max(1*3, 1*solve(3)), max(2*2, 2*solve(2)), max(3*1, 3*solve(1)))` which results in `4`. `memo[4] = 4`.
 *     - `recursive = 1 * 4 = 4`.
 *     - `max` becomes `max(0, max(4, 4)) = 4`.
 *
 * 2.  **i = 2:**
 *     - `direct = 2 * (5 - 2) = 6`.
 *     - `recursive = 2 * solve(3)`.
 *       - `solve(3)` is a base case, returns `2`.
 *     - `recursive = 2 * 2 = 4`.
 *     - `max` becomes `max(4, max(6, 4)) = 6`.
 *
 * 3.  **i = 3:**
 *     - `direct = 3 * (5 - 3) = 6`.
 *     - `recursive = 3 * solve(2)`.
 *       - `solve(2)` is a base case, returns `1`.
 *     - `recursive = 3 * 1 = 3`.
 *     - `max` becomes `max(6, max(6, 3)) = 6`.
 *
 * 4.  **i = 4:**
 *     - `direct = 4 * (5 - 4) = 4`.
 *     - `recursive = 4 * solve(1)`.
 *       - `solve(1)` is a base case, returns `0`.
 *     - `recursive = 4 * 0 = 0`.
 *     - `max` becomes `max(6, max(4, 0)) = 6`.
 *
 * The loop finishes. `memo[5]` is set to 6. `solve(5)` returns 6.
 *
 * ### Complexity
 * - **Time Complexity:** O(N^2). Due to memoization, each state `solve(i)` from 1 to N is computed once. For each state, we loop from 1 to `i-1`. This leads to a total complexity of `sum(i for i=1..N)`, which is O(N^2).
 * - **Space Complexity:** O(N). For the memoization array and the depth of the recursion stack.
 */
public class IntegerBreak {
    public int integerBreak(int n) {
        int[] memo = new int[n+1];
        Arrays.fill(memo, -1);
        return solve(n, memo);
    }
    private int solve(int n, int[] memo){
        if(n == 1) return 0; // Cannot break 1 into a sum of k>=2 integers.
        if(n == 2) return 1; // 1 + 1, product is 1
        if(n == 3) return 2; // 1 + 2, product is 2
        if(memo[n] != -1) return memo[n];
        int max = 0;
        for(int i = 1; i < n; i++) {
        int direct = i * (n - i);
        int recursive = i * solve(n - i, memo);
        max = Math.max(max, Math.max(direct, recursive));
    }
    memo[n] = max;
    return max;
}

    public static void main(String[] args) {
        IntegerBreak solution = new IntegerBreak();
        int n1 = 2;
        System.out.println("Max product for " + n1 + ": " + solution.integerBreak(n1)); // Expected: 1

        int n2 = 10;
        System.out.println("Max product for " + n2 + ": " + solution.integerBreak(n2)); // Expected: 36
    }
}
