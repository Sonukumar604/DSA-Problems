import java.util.Arrays;

public class EggDropMemoizationOptimization {
    public static int[][] dp;

    public static int eggDrop(int e, int f) {
        // Base cases
        if (f == 0 || f == 1) return f;
        if (e == 1) return f;

        // Check if we have already solved this subproblem
        if (dp[e][f] != -1) {
            return dp[e][f];
        }

        int min = Integer.MAX_VALUE;

        // Consider all droppings from floor 1 to f
        for (int k = 1; k <= f; k++) {
            int caseEggBreaks;
            int caseEggSurvives;

            // Case 1: Egg breaks. Check dp table before making a recursive call.
            if (dp[e - 1][k - 1] != -1) {
                caseEggBreaks = dp[e - 1][k - 1];
            } else {
                caseEggBreaks = eggDrop(e - 1, k - 1);
                // We can optionally store the result here, but the recursive call already does.
                // dp[e - 1][k - 1] = caseEggBreaks;
            }

            // Case 2: Egg survives. Check dp table before making a recursive call.
            if (dp[e][f - k] != -1) {
                caseEggSurvives = dp[e][f - k];
            } else {
                caseEggSurvives = eggDrop(e, f - k);
                // dp[e][f - k] = caseEggSurvives;
            }

            // We need the worst-case (max) trials between the two outcomes, plus this one trial.
            int temp = 1 + Math.max(caseEggBreaks, caseEggSurvives);

            // Find the minimum of all worst-cases.
            min = Math.min(min, temp);
        }

        // Store the result for the current state (e, f) and return it.
        return dp[e][f] = min;
    }

    public static void main(String[] args) {
        int eggs = 2, floors = 10;
        dp = new int[eggs + 1][floors + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        System.out.println("Minimum trials in worst case: " + eggDrop(eggs, floors));
    }
}
