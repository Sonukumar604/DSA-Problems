import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RepeatingAndMissing {

    // The record is now a simple data carrier. I've also swapped the order
    // to a more conventional "repeating, missing".
    public record Result(int repeating, int missing) {
        @Override
        public String toString() {
            return "Repeating=" + repeating + ", Missing=" + missing;
        }
    }

    // This method is now correctly placed within the class, not the record.
    public static Result findRepeatingAndMissingMath(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Input array cannot be null or empty.");
        }
        long n = nums.length;
        long expectedSum = n * (n + 1) / 2;
        long expectedSumOfSquares = n * (n + 1) * (2 * n + 1) / 6;
        long actualSum = 0;
        long actualSumOfSquares = 0;

        for (int num : nums) {
            actualSum += num;
            actualSumOfSquares += (long) num * num;
        }
        long diffSum = actualSum - expectedSum;
        long diffSumOfSquares = actualSumOfSquares - expectedSumOfSquares;
        long sumXY = diffSumOfSquares / diffSum;
        long repeating = (sumXY + diffSum) / 2;
        long missing = (sumXY - diffSum) / 2;
        return new Result((int) repeating, (int) missing);
    }

    public static Result findRepeatingAndMissingUsingMap(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Input array cannot be null or empty.");
        }
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        int repeating = -1, missing = -1;
        for (int i = 1; i <= nums.length; i++) {
            if (frequencyMap.getOrDefault(i, 0) == 2) repeating = i;
            if (frequencyMap.getOrDefault(i, 0) == 0) missing = i; // Corrected logic
        }
        return new Result(repeating, missing);
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 6, 2, 1, 1};
        System.out.println("Input Array: " + Arrays.toString(nums));
        System.out.println("\n--- Using Mathematical Approach ---");
        System.out.println("Result: " + findRepeatingAndMissingMath(nums));
        System.out.println("\n--- Using HashMap Approach ---");
        System.out.println("Result: " + findRepeatingAndMissingUsingMap(nums));
    }
}
