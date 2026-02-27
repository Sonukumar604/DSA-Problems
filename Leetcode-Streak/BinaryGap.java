public class BinaryGap {
    /**
     * Finds the longest distance between two consecutive 1's in the binary representation of n.
     *
     * ### Explanation
     * We iterate through the bits of the integer `n` from the least significant bit (rightmost) to the most significant bit.
     * We maintain the position of the previously encountered '1' in the variable `lastIndex`.
     *
     * 1. Initialize `lastIndex` to -1 (indicating no '1' seen yet) and `maxGap` to 0.
     * 2. Loop while `n > 0`:
     *    - Check if the current bit is 1 (`(n & 1) == 1`).
     *    - If it is 1:
     *      - If `lastIndex` is not -1, calculate the distance (`index - lastIndex`) and update `maxGap`.
     *      - Update `lastIndex` to the current `index`.
     *    - Increment `index` to move to the next bit position.
     *    - Right shift `n` by 1 (`n >>= 1`) to process the next bit.
     * 3. Return `maxGap`.
     *
     * ### Dry Run
     * Input: `n = 22` (Binary: `10110`)
     * Initialize: `lastIndex = -1`, `maxGap = 0`, `index = 0`.
     *
     * 1. **Iteration 1:**
     *    - `n` = 22 (`...10110`). `n & 1` is 0.
     *    - `index` becomes 1. `n` becomes 11 (`...01011`).
     *
     * 2. **Iteration 2:**
     *    - `n` = 11 (`...01011`). `n & 1` is 1.
     *    - `lastIndex` is -1. No gap calculation.
     *    - `lastIndex` becomes 1.
     *    - `index` becomes 2. `n` becomes 5 (`...00101`).
     *
     * 3. **Iteration 3:**
     *    - `n` = 5 (`...00101`). `n & 1` is 1.
     *    - `lastIndex` is 1. Gap = `2 - 1 = 1`. `maxGap` = max(0, 1) = 1.
     *    - `lastIndex` becomes 2.
     *    - `index` becomes 3. `n` becomes 2 (`...00010`).
     *
     * 4. **Iteration 4:**
     *    - `n` = 2 (`...00010`). `n & 1` is 0.
     *    - `index` becomes 4. `n` becomes 1 (`...00001`).
     *
     * 5. **Iteration 5:**
     *    - `n` = 1 (`...00001`). `n & 1` is 1.
     *    - `lastIndex` is 2. Gap = `4 - 2 = 2`. `maxGap` = max(1, 2) = 2.
     *    - `lastIndex` becomes 4.
     *    - `index` becomes 5. `n` becomes 0.
     *
     * Loop terminates. Return `maxGap` = 2.
     *
     * ### Complexity
     * - **Time Complexity:** O(log N). The number of iterations is equal to the number of bits in `n`. For a 32-bit integer, this is effectively O(1).
     * - **Space Complexity:** O(1). We use a constant amount of extra space.
     */
    public int binaryGap(int n) {
        int lastIndex = -1;
        int maxGap = 0;
        int index = 0;
        while (n > 0) {
            if ((n & 1) == 1) {
                if (lastIndex != -1) {
                    maxGap = Math.max(maxGap, index - lastIndex);
                }
                lastIndex = index;
            }
            index++;
            n = n >> 1;
        }
        return maxGap;
    }

    public static void main(String[] args) {
        BinaryGap solution = new BinaryGap();

        int n1 = 22; // Binary: 10110
        System.out.println("Input: " + n1 + " (Binary: " + Integer.toBinaryString(n1) + ") -> Max Gap: " + solution.binaryGap(n1)); // Expected: 2

        int n2 = 8; // Binary: 1000
        System.out.println("Input: " + n2 + " (Binary: " + Integer.toBinaryString(n2) + ") -> Max Gap: " + solution.binaryGap(n2)); // Expected: 0

        int n3 = 5; // Binary: 101
        System.out.println("Input: " + n3 + " (Binary: " + Integer.toBinaryString(n3) + ") -> Max Gap: " + solution.binaryGap(n3)); // Expected: 2
    }
}
