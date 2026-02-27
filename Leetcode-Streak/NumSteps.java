public class NumSteps {
    /**
     * Calculates the number of steps to reduce a binary number represented by a string to 1.
     * The allowed operations are:
     * - If the number is even, divide it by 2.
     * - If the number is odd, add 1 to it.
     *
     * ### Explanation
     * The algorithm simulates the process by iterating through the binary string from right to left
     * (from the least significant bit to the most significant bit), which is more efficient than
     * converting the string to a large number.
     *
     * We use a `carry` variable to handle the propagation of '1's when we add one to an odd number.
     *
     * - If `bit + carry == 1`: The current number is odd. We must add 1 (1 step) and then divide by 2 (1 step), totaling 2 steps. Adding 1 to a '1' bit results in a '0' at the current position and a carry-over of 1 to the next bit.
     * - If `bit + carry == 0` or `bit + carry == 2`: The current number is effectively even. We perform a division by 2 (a right shift), which takes 1 step. The carry for the next bit is correctly maintained (it's 0 if `bit+carry` was 0, and 1 if `bit+carry` was 2).
     *
     * The loop processes all bits except the most significant one (at index 0). The final `steps + carry` handles the last state. If `carry` is 1 after the loop, it means the number has become '10' (binary 2), which needs one final division step to become '1'.
     *
     * ### Time and Space Complexity
     * - **Time Complexity:** O(N), where N is the length of the string `s`. We iterate through the string once.
     * - **Space Complexity:** O(1), as we only use a few variables (`steps`, `carry`) for the computation, requiring constant extra space.
     *
     * ### Dry Run (s = "1101")
     * Initial state: `steps = 0`, `carry = 0`. Loop from `i = 3` down to `1`.
     *
     * 1. **i = 3 (bit '1'):**
     *    - `bit` is 1. `bit + carry` is `1 + 0 = 1`. This is the odd case.
     *    - `steps` becomes 2.
     *    - `carry` becomes 1.
     *
     * 2. **i = 2 (bit '0'):**
     *    - `bit` is 0. `bit + carry` is `0 + 1 = 1`. This is the odd case.
     *    - `steps` becomes 2 + 2 = 4.
     *    - `carry` remains 1.
     *
     * 3. **i = 1 (bit '1'):**
     *    - `bit` is 1. `bit + carry` is `1 + 1 = 2`. This is the even case.
     *    - `steps` becomes 4 + 1 = 5.
     *    - `carry` remains 1.
     *
     * 4. **End of Loop & Final Step:**
     *    - The loop finishes. We return `steps + carry` which is `5 + 1 = 6`.
     */
    /**
     * ... (previous content) ...
     *
     * ### Another Dry Run (s = "11101")
     * Manual check: "11101" (29) -> 30 (1 step) -> 15 (2) -> 16 (3) -> 8 (4) -> 4 (5) -> 2 (6) -> 1 (7). Total steps: 7.
     *
     * Algorithm Dry Run:
     * Initial state: `steps = 0`, `carry = 0`. Loop from `i = 4` down to `1`.
     *
     * 1. **i = 4 (bit '1'):**
     *    - `bit`=1, `carry`=0. `bit + carry` = 1 (odd). `steps` becomes 2. `carry` becomes 1.
     * 2. **i = 3 (bit '0'):**
     *    - `bit`=0, `carry`=1. `bit + carry` = 1 (odd). `steps` becomes `2 + 2 = 4`. `carry` becomes 1.
     * 3. **i = 2 (bit '1'):**
     *    - `bit`=1, `carry`=1. `bit + carry` = 2 (even). `steps` becomes `4 + 1 = 5`. `carry` remains 1.
     * 4. **i = 1 (bit '1'):**
     *    - `bit`=1, `carry`=1. `bit + carry` = 2 (even). `steps` becomes `5 + 1 = 6`. `carry` remains 1.
     * 5. **End of Loop & Final Step:** The loop finishes. We return `steps + carry` which is `6 + 1 = 7`.
     */
    public int numSteps(String s) {
        int steps = 0;
        int carry = 0;

        for (int i = s.length() - 1; i > 0; i--) {

            int bit = s.charAt(i) - '0';

            if (bit + carry == 1) {
                // odd case
                steps += 2;
                carry = 1;
            } else {
                // even case
                steps += 1;
            }
        }

        return steps + carry;
    }

    public static void main(String[] args) {
        NumSteps solution = new NumSteps();

        // Test Case 1 (from dry run)
        String s1 = "1101";
        System.out.println("Number of steps for \"" + s1 + "\": " + solution.numSteps(s1)); // Expected: 6

        // Test Case 2
        String s2 = "10";
        System.out.println("Number of steps for \"" + s2 + "\": " + solution.numSteps(s2)); // Expected: 1

        // Test Case 3
        String s3 = "1";
        System.out.println("Number of steps for \"" + s3 + "\": " + solution.numSteps(s3)); // Expected: 0

        // Test Case 4 (from another dry run)
        String s4 = "11101";
        System.out.println("Number of steps for \"" + s4 + "\": " + solution.numSteps(s4)); // Expected: 7
    }
}
