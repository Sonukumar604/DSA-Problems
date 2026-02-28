public class BinaryNumberWithAlternatingBits {
    /**
     * Checks if a positive integer has alternating bits (i.e., no two adjacent bits are the same).
     *
     * ### Explanation
     * We iterate through the bits of the number from the least significant bit (rightmost) to the most significant bit.
     * We maintain the value of the previously processed bit (`prevBit`).
     * In each iteration:
     * 1. Extract the current last bit using `n & 1`.
     * 2. Compare it with the `prevBit`. If they are the same, the bits are not alternating, so return `false`.
     * 3. Update `prevBit` to the current bit.
     * 4. Right shift `n` by 1 (`n >>= 1`) to process the next bit.
     * If the loop completes without finding adjacent matching bits, return `true`.
     *
     * ### Dry Run
     * Input: `n = 10` (Binary: `1010`)
     * Initialize `prevBit = -1`.
     *
     * 1. **Iteration 1:**
     *    - `n` is 10 (`...1010`).
     *    - `currentBit` = `10 & 1` = `0`.
     *    - `currentBit` (0) != `prevBit` (-1). Check passes.
     *    - `prevBit` becomes `0`.
     *    - `n` becomes `10 >> 1` = `5` (`...0101`).
     *
     * 2. **Iteration 2:**
     *    - `n` is 5 (`...0101`).
     *    - `currentBit` = `5 & 1` = `1`.
     *    - `currentBit` (1) != `prevBit` (0). Check passes.
     *    - `prevBit` becomes `1`.
     *    - `n` becomes `5 >> 1` = `2` (`...0010`).
     *
     * 3. **Iteration 3:**
     *    - `n` is 2 (`...0010`).
     *    - `currentBit` = `2 & 1` = `0`.
     *    - `currentBit` (0) != `prevBit` (1). Check passes.
     *    - `prevBit` becomes `0`.
     *    - `n` becomes `2 >> 1` = `1` (`...0001`).
     *
     * 4. **Iteration 4:**
     *    - `n` is 1 (`...0001`).
     *    - `currentBit` = `1 & 1` = `1`.
     *    - `currentBit` (1) != `prevBit` (0). Check passes.
     *    - `prevBit` becomes `1`.
     *    - `n` becomes `1 >> 1` = `0`.
     *
     * Loop terminates as `n` is 0. Return `true`.
     *
     * ### Complexity
     * - **Time Complexity:** O(1). Since the integer is a fixed size (32 bits in Java), the loop runs a maximum of 32 times.
     * - **Space Complexity:** O(1). We only use a few variables for storage.
     */
    public boolean hasAlternatingBits(int n) {
        int prevBit = -1; // Initialize to an invalid bit value
        while (n > 0) {
            int currentBit = n & 1; // Get the last bit
            if (currentBit == prevBit) {
                return false; // If the current bit is the same as the previous bit, return false
            }
            prevBit = currentBit; // Update previous bit
            n >>= 1; // Right shift to process the next bit
        }
        return true; // All bits are alternating
    }

    public boolean hasAlternatingBitsBitwise(int n) {
        String s = Integer.toBinaryString(n);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        BinaryNumberWithAlternatingBits solution = new BinaryNumberWithAlternatingBits();

        int n1 = 5; // Binary: 101
        System.out.println("Input: " + n1 + " (" + Integer.toBinaryString(n1) + ") -> " + solution.hasAlternatingBits(n1)); // Expected: true

        int n2 = 7; // Binary: 111
        System.out.println("Input: " + n2 + " (" + Integer.toBinaryString(n2) + ") -> " + solution.hasAlternatingBits(n2)); // Expected: false

        int n3 = 11; // Binary: 1011
        System.out.println("Input: " + n3 + " (" + Integer.toBinaryString(n3) + ") -> " + solution.hasAlternatingBits(n3)); // Expected: false

        int n4 = 10; // Binary: 1010
        System.out.println("Input: " + n4 + " (" + Integer.toBinaryString(n4) + ") -> " + solution.hasAlternatingBits(n4)); // Expected: true
    }
}
