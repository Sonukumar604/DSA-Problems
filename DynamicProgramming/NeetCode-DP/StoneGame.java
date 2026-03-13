import java.util.Arrays;

public class StoneGame {
    /**
     * Default solution for the Stone Game (LeetCode 877).
     * For this specific problem's constraints (even number of piles, total sum is odd),
     * the first player (Alex) can always devise a strategy to win. This method provides that shortcut answer.
     */
    public boolean stoneGame(int[] piles) {
        // Alex can always win.
        return true;
    }

    /**
     * Solves the Stone Game problem using a pure recursive approach to demonstrate the underlying logic.
     *
     * ### Explanation
     * This is a game theory problem. We can define a function `solve(i, j)` that returns the maximum score difference
     * the current player can achieve over the opponent from the pile range `piles[i...j]`.
     *
     * The current player has two choices:
     * 1. Take `piles[i]`: Their score difference will be `piles[i]` minus the score difference the *next* player gets from the remaining piles `[i+1...j]`.
     *    So, the net difference is `piles[i] - solve(i+1, j)`.
     * 2. Take `piles[j]`: Their score difference will be `piles[j]` minus the score difference the *next* player gets from the remaining piles `[i...j-1]`.
     *    So, the net difference is `piles[j] - solve(i, j-1)`.
     *
     * The current player will choose the move that maximizes their score difference.
     * Therefore, `solve(i, j) = max(piles[i] - solve(i+1, j), piles[j] - solve(i, j-1))`.
     *
     * The first player (Alex) wins if their final score difference is positive.
     *
     * ### Base Case
     * When `i == j`, there's only one pile left. The current player takes it, so the score difference is `piles[i]`.
     *
     * ### Dry Run (piles = [5, 3, 4, 5])
     * `solve(0, 3)` = max(5 - solve(1, 3), 5 - solve(0, 2))
     *
     *   `solve(1, 3)` = max(3 - solve(2, 3), 5 - solve(1, 2))
     *     `solve(2, 3)` = max(4 - solve(3, 3), 5 - solve(2, 2)) = max(4 - 5, 5 - 4) = max(-1, 1) = 1
     *     `solve(1, 2)` = max(3 - solve(2, 2), 4 - solve(1, 1)) = max(3 - 4, 4 - 3) = max(-1, 1) = 1
     *   `solve(1, 3)` = max(3 - 1, 5 - 1) = max(2, 4) = 4
     *
     *   `solve(0, 2)` = max(5 - solve(1, 2), 4 - solve(0, 1))
     *     `solve(1, 2)` = 1 (calculated above)
     *     `solve(0, 1)` = max(5 - solve(1, 1), 3 - solve(0, 0)) = max(5 - 3, 3 - 5) = max(2, -2) = 2
     *   `solve(0, 2)` = max(5 - 1, 4 - 2) = max(4, 2) = 4
     *
     * `solve(0, 3)` = max(5 - 4, 5 - 4) = max(1, 1) = 1
     *
     * Since `solve(0, 3)` is 1 (which is > 0), Alex wins.
     *
     * ### Complexity
     * - Time Complexity: O(2^N), exponential due to overlapping subproblems.
     * - Space Complexity: O(N) for the recursion stack depth.
     */
    public boolean stoneGameRecursive(int[] piles) {
        return solve(piles, 0, piles.length - 1) > 0;
    }

    private int solve(int[] piles, int i, int j) {
        if (i == j) return piles[i];
        int takeLeft = piles[i] - solve(piles, i + 1, j);
        int takeRight = piles[j] - solve(piles, i, j - 1);
        return Math.max(takeLeft, takeRight);
    }

    /**
     * Solves the Stone Game problem using recursion with memoization (Top-Down DP).
     * This optimizes the recursive solution by storing the results of subproblems (i, j)
     * to avoid re-computation.
     *
     * ### Complexity
     * - Time Complexity: O(N^2), where N is the number of piles. Each subproblem `solve(i, j)` is computed only once.
     * - Space Complexity: O(N^2) for the memoization table + O(N) for the recursion stack.
     */
    public boolean stoneGameMemoization(int[] piles) {
        int n = piles.length;
        Integer[][] memo = new Integer[n][n];
        return solveMemo(piles, 0, n - 1, memo) > 0;
    }

    private int solveMemo(int[] piles, int i, int j, Integer[][] memo) {
        if (i == j) {
            return piles[i];
        }
        if (memo[i][j] != null) {
            return memo[i][j];
        }

        int takeLeft = piles[i] - solveMemo(piles, i + 1, j, memo);
        int takeRight = piles[j] - solveMemo(piles, i, j - 1, memo);

        memo[i][j] = Math.max(takeLeft, takeRight);
        return memo[i][j];
    }

    // --- Stone Game II (LeetCode 1140) ---

    /**
     * Solves the Stone Game II problem.
     * The goal is to find the maximum number of stones the first player (Alice) can get.
     * This is the main entry point, defaulting to the optimized memoization approach.
     */
    public int stoneGameII(int[] piles) {
        return stoneGameIIMemo(piles);
    }

    /**
     * Solves Stone Game II using a pure recursive approach.
     *
     * ### Explanation
     * Let `solve(i, M)` be the maximum number of stones the current player can get from the piles `piles[i:]`
     * with the current value of `M`. The total stones available from `piles[i:]` is `suffixSum[i]`.
     * The current player can take `X` piles, where `1 <= X <= 2*M`.
     * If they take `X` piles, the next player starts at `i+X` with a new `M' = max(M, X)`.
     * The next player will get `solve(i+X, M')` stones.
     * The current player's total score for this move is `suffixSum[i] - solve(i+X, M')`.
     * The current player wants to maximize their score, which means they want to choose an `X` that
     * minimizes the next player's score.
     *
     * Recurrence: `solve(i, M) = suffixSum[i] - min_{1<=X<=2M} (solve(i+X, max(M,X)))`
     *
     * ### Complexity
     * - Time: Exponential, due to re-calculating the same (i, M) states.
     * - Space: O(N) for recursion stack and suffix sums.
     */
    public int stoneGameIIRecursive(int[] piles) {
        int n = piles.length;
        int[] suffixSum = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }
        return solveIIRecursive(piles, suffixSum, 0, 1);
    }
    private int solveIIRecursive(int[] piles, int[] suffixSum, int i, int M) {
        int n = piles.length;
        if (i >= n) return 0;
        if (i + 2 * M >= n) return suffixSum[i];
        int minOpponentScore = Integer.MAX_VALUE;
        for (int X = 1; X <= 2 * M; X++) {
            minOpponentScore = Math.min(minOpponentScore, solveIIRecursive(piles, suffixSum, i + X, Math.max(M, X)));
        }
        return suffixSum[i] - minOpponentScore;
    }
    /**
     * Solves Stone Game II using recursion with memoization.
     *
     * ### Complexity
     * - Time: O(N^2). The state is (i, M). `i` is N, `M` can go up to N. We compute each state once.
     * - Space: O(N^2) for memoization table.
     */
    public int stoneGameIIMemo(int[] piles) {
        int n = piles.length;
        int[] suffixSum = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }
        Integer[][] memo = new Integer[n][n + 1];
        return solveIIMemo(piles, suffixSum, 0, 1, memo);
    }

    private int solveIIMemo(int[] piles, int[] suffixSum, int i, int M, Integer[][] memo) {
        int n = piles.length;
        if (i >= n) return 0;
        if (i + 2 * M >= n) return suffixSum[i];
        if (memo[i][M] != null) return memo[i][M];

        int minOpponentScore = Integer.MAX_VALUE;
        for (int X = 1; X <= 2 * M; X++) {
            minOpponentScore = Math.min(minOpponentScore, solveIIMemo(piles, suffixSum, i + X, Math.max(M, X), memo));
        }
        return memo[i][M] = suffixSum[i] - minOpponentScore;
    }

    // --- Stone Game II - Alternative Memoization ---

    /**
     * Solves Stone Game II using an alternative top-down DP approach.
     * This method explicitly models the turns of both players (Alice and Bob).
     *
     * ### Explanation
     * Let `solveForAlice(person, i, M)` be the maximum number of stones Alice can get from `piles[i:]` with value `M`,
     * given that it is `person`'s turn to play.
     * - `person = 1` for Alice, `person = 0` for Bob.
     *
     * - **If it's Alice's turn (person = 1):**
     *   She wants to maximize her score. She can take `x` piles (where `1 <= x <= 2*M`).
     *   Her score for this move is `(stones she takes) + (what she can get from the rest of the game)`.
     *   The rest of the game is `solveForAlice(0, i + x, max(M, x))`.
     *   So, she maximizes: `stones_taken + solveForAlice(0, i + x, max(M, x))` over all valid `x`.
     *
     * - **If it's Bob's turn (person = 0):**
     *   He also plays optimally to maximize his own score. This means he will make a move that *minimizes*
     *   the score Alice can get from the rest of the game.
     *   He takes `x` piles, and the rest of the game for Alice is `solveForAlice(1, i + x, max(M, x))`.
     *   So, he will choose `x` to minimize `solveForAlice(1, i + x, max(M, x))`.
     *
     * The state is `(person, i, M)`, stored in a 3D memoization table.
     */
    int n;
    int[][][] t = new int[2][101][101];

    public int stoneGameII_Alternative(int[] piles) {

        n = piles.length;

        for(int p=0;p<2;p++)
            for(int i=0;i<101;i++)
                for(int j=0;j<101;j++)
                    t[p][i][j] = -1;

        return solveForAlice(piles, 1, 0, 1);
    }

    private int solveForAlice(int[] piles, int person, int i, int M) {

        if(i >= n)
            return 0;

        if(t[person][i][M] != -1)
            return t[person][i][M];

        int result = (person == 1) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int stones = 0;

        for(int x = 1; x <= Math.min(2*M, n-i); x++) {
            stones += piles[i + x - 1];
            if(person == 1) { // Alice (maximize)
                result = Math.max(result, stones + solveForAlice(piles, 0, i + x, Math.max(M, x)));
            } else { // Bob (minimize)
                result = Math.min(result, solveForAlice(piles, 1, i + x, Math.max(M, x)));
            }
        }
        return t[person][i][M] = result;
    }

    public static void main(String[] args) {
        StoneGame game = new StoneGame();
        int[] piles = {5, 3, 4, 5};
        System.out.println("--- Stone Game I ---");
        System.out.println("Piles: " + Arrays.toString(piles));
        System.out.println("Can Alex win (Recursive)? " + game.stoneGameRecursive(piles)); // Expected: true
        System.out.println("Can Alex win (Memoization)? " + game.stoneGameMemoization(piles)); // Expected: true
        System.out.println("Can Alex win (Default)? " + game.stoneGame(piles)); // Expected: true
        System.out.println();

        System.out.println("--- Stone Game II ---");
        int[] piles2 = {2, 7, 9, 4, 4};
        System.out.println("Piles: " + Arrays.toString(piles2));
        // System.out.println("Max stones for Alice (Recursive): " + game.stoneGameIIRecursive(piles2)); // Can be slow
        System.out.println("Max stones for Alice (Memoization): " + game.stoneGameIIMemo(piles2)); // Expected: 10
        System.out.println("Max stones for Alice (Alternative): " + game.stoneGameII_Alternative(piles2)); // Expected: 10
    }
}
