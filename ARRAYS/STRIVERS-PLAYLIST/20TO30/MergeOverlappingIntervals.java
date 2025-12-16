import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeOverlappingIntervals {

    // Brute-Force Approach
    // Time Complexity: O(N log N) + O(N^2), which simplifies to O(N^2).
    // Space Complexity: O(N) for the result list.
    public static int[][] mergeBrute(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        // 1. Sort the intervals based on their start time.
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> merged = new ArrayList<>();

        for (int i = 0; i < intervals.length; i++) {
            int[] currentInterval = {intervals[i][0], intervals[i][1]};

            // This flag is used to check if the current interval was merged into a previous one.
            boolean isMerged = false;
            for (int[] mergedInterval : merged) {
                // Check for overlap: mergedInterval.end >= currentInterval.start
                if (mergedInterval[1] >= currentInterval[0]) {
                    mergedInterval[1] = Math.max(mergedInterval[1], currentInterval[1]);
                    isMerged = true;
                    break; // Once merged, no need to check other merged intervals
                }
            }

            if (!isMerged) {
                merged.add(currentInterval);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    // Optimal Approach
    // Time Complexity: O(N log N) for sorting + O(N) for merging. Total: O(N log N).
    // Space Complexity: O(N) for the result list in the worst case.
    public static int[][] mergeOptimal(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        // 1. Sort the intervals based on their start time.
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> merged = new ArrayList<>();
        merged.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] lastInterval = merged.get(merged.size() - 1);
            int[] currentInterval = intervals[i];

            // 2. Check for overlap: lastInterval.end >= currentInterval.start
            if (lastInterval[1] >= currentInterval[0]) {
                // 3. Merge by updating the end of the last interval.
                lastInterval[1] = Math.max(lastInterval[1], currentInterval[1]);
            } else {
                // 4. No overlap, so add the current interval as a new one.
                merged.add(currentInterval);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.println("Original Intervals: " + Arrays.deepToString(intervals));

        int[][] mergedBrute = mergeBrute(Arrays.stream(intervals).map(int[]::clone).toArray(int[][]::new));
        System.out.println("Merged (Brute-Force): " + Arrays.deepToString(mergedBrute));

        int[][] mergedOptimal = mergeOptimal(Arrays.stream(intervals).map(int[]::clone).toArray(int[][]::new));
        System.out.println("Merged (Optimal): " + Arrays.deepToString(mergedOptimal));
    }
}
