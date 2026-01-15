import java.util.Arrays;

public class MatchSticksToSquare {
    public boolean makesquare(int[] matchsticks){
        if (matchsticks == null || matchsticks.length < 4) return false;
        int sum = 0;
        for (int v : matchsticks) sum += v;
        if (sum % 4 != 0) return false;
        int target = sum / 4;

        Arrays.sort(matchsticks);
        int n = matchsticks.length;
        int[] sticks = new int[n];
        for (int i = 0; i < n; i++) sticks[i] = matchsticks[n - 1 - i]; // descending
        if (sticks[0] > target) return false;

        int[] sides = new int[4];
        return backtrack(0, sticks, sides, target);
    }

    // Backtracking (DFS) helper
    private boolean backtrack(int index, int[] sticks, int[] sides, int target) {
        if (index == sticks.length) {
            return sides[0] == target && sides[1] == target && sides[2] == target; // 4th implied
        }
        int curr = sticks[index];
        for (int i = 0; i < 4; i++) {
            if (sides[i] + curr <= target) {
                sides[i] += curr;
                if (backtrack(index + 1, sticks, sides, target)) return true;
                sides[i] -= curr;
            }
            // symmetry pruning: if this side is still 0 after trying, other empty sides will be equivalent
            if (sides[i] == 0) break;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] matchsticks = new int[]{1,3,4,2,2,4};
        MatchSticksToSquare mss = new MatchSticksToSquare();
        System.out.println(mss.makesquare(matchsticks)); // expected: true
    }
}