import java.util.ArrayList;
import java.util.List;

public class PrintNBi {

    /**
     * Public-facing method to start the generation process.
     * It initializes an empty list and calls the recursive helper.
     *
     * @param n The number of bits in the binary numbers.
     * @return A list of valid N-bit binary strings.
     */
    public static List<String> generate(int n) {
        List<String> result = new ArrayList<>();
        solve(n, 0, 0, "", result);
        return result;
    }

    /**
     * The recursive helper function based on the Induction-Base-Hypothesis method.
     *
     * @param n      The number of digits remaining to be added.
     * @param ones   The count of 1s in the current output string.
     * @param zeros  The count of 0s in the current output string.
     * @param output The binary string built so far.
     * @param result The list to store the final valid strings.
     */
    private static void solve(int n, int ones, int zeros, String output, List<String> result) {
        // Base Case: If no more digits are needed, we have a valid number.
        if (n == 0) {
            result.add(output);
            return;
        }

        // Induction Step 1: Always safe to add a '1'.
        solve(n - 1, ones + 1, zeros, output + "1", result);

        // Induction Step 2: Only add a '0' if it doesn't violate the prefix rule.
        if (ones > zeros) {
            solve(n - 1, ones, zeros + 1, output + "0", result);
        }
    }

    public static void main(String[] args) {
        int n = 4;
        System.out.println("N-bit binary numbers with more 1s than 0s for any prefix (N=" + n + "):");
        List<String> validNumbers = generate(n);
        System.out.println(validNumbers);
        // Expected for N=4: [1111, 1110, 1101, 1100, 1011, 1010]
    }
}