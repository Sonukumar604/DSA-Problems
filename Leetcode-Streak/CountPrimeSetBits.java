public class CountPrimeSetBits {
    /**
     * Counts the number of integers in a given range [left, right] that have a prime number of set bits in their binary representation.
     *
     * ### Explanation
     * The algorithm iterates through each number from `left` to `right`. For each number, it performs two main steps:
     * 1.  **Count Set Bits:** It calculates the number of '1's in the binary representation of the current number. The `Integer.bitCount()` method is a highly optimized, built-in way to do this.
     * 2.  **Check for Primality:** It then checks if the resulting bit count is a prime number using a helper function `isPrime()`.
     *
     * The `isPrime()` helper function uses a standard trial division method. It returns `false` for numbers less than 2. Then, it checks for divisibility from 2 up to the square root of the number. If any divisor is found, the number is not prime.
     *
     * If the bit count is prime, a counter is incremented.
     *
     * ### Dry Run (left = 10, right = 15)
     * Initialize `count = 0`.
     *
     * - **i = 10** (Binary: `1010`):
     *   - `bits = Integer.bitCount(10)` -> 2.
     *   - `isPrime(2)` -> true.
     *   - `count` becomes 1.
     *
     * - **i = 11** (Binary: `1011`):
     *   - `bits = Integer.bitCount(11)` -> 3.
     *   - `isPrime(3)` -> true.
     *   - `count` becomes 2.
     *
     * - **i = 12** (Binary: `1100`):
     *   - `bits = Integer.bitCount(12)` -> 2.
     *   - `isPrime(2)` -> true.
     *   - `count` becomes 3.
     *
     * - **i = 13** (Binary: `1101`):
     *   - `bits = Integer.bitCount(13)` -> 3.
     *   - `isPrime(3)` -> true.
     *   - `count` becomes 4.
     *
     * - **i = 14** (Binary: `1110`):
     *   - `bits = Integer.bitCount(14)` -> 3.
     *   - `isPrime(3)` -> true.
     *   - `count` becomes 5.
     *
     * - **i = 15** (Binary: `1111`):
     *   - `bits = Integer.bitCount(15)` -> 4.
     *   - `isPrime(4)` -> false.
     *   - `count` remains 5.
     *
     * Loop ends. Return `count`, which is 5.
     *
     * ### Complexity
     * - **Time Complexity:** O(N * sqrt(K)), where N is the number of integers in the range (`right - left + 1`) and K is the maximum number of bits in an integer from the range. Since K is at most 32 (for a Java `int`), `sqrt(K)` is a small constant. Therefore, the complexity simplifies to O(N).
     * - **Space Complexity:** O(1). We use only a few variables, requiring constant extra space.
     */
    public int countPrimeSetBits(int left, int right) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            int bits = Integer.bitCount(i);
            if (isPrime(bits)) {
                count++;
            }
        }
        return count;
    }
    private boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        CountPrimeSetBits solution = new CountPrimeSetBits();

        int left1 = 6, right1 = 10;
        System.out.println("Count for [" + left1 + ", " + right1 + "]: " + solution.countPrimeSetBits(left1, right1)); // Expected: 4

        int left2 = 10, right2 = 15;
        System.out.println("Count for [" + left2 + ", " + right2 + "]: " + solution.countPrimeSetBits(left2, right2)); // Expected: 5
    }
}
