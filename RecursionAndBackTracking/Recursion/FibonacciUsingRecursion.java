import java.util.Arrays;

public class FibonacciUsingRecursion {

    /**
     * Public-facing method to calculate the nth Fibonacci number using memoized recursion.
     * It sets up the cache and calls the memoized helper function.
     *
     * @param n The position in the sequence (non-negative integer).
     * @return The nth Fibonacci number.
     */
    public static long fib(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input n cannot be negative.");
        }
        // Create a cache array to store results. We use n+1 size for 0-based index.
        long[] memo = new long[n + 1];
        // Initialize cache with a value indicating "not yet computed".
        Arrays.fill(memo, -1);
        return fibMemoized(n, memo);
    }

    /**
     * The recursive helper function that uses a cache (memo) to store results.
     */
    private static long fibMemoized(int n, long[] memo) {
        // Base Cases
        if (n <= 1) {
            return n;
        }

        // If the value is already in our cache, return it directly.
        if (memo[n] != -1) {
            return memo[n];
        }

        // If not in cache, compute it, STORE it, and then return.
        memo[n] = fibMemoized(n - 1, memo) + fibMemoized(n - 2, memo);
        return memo[n];
    }

    /**
     * Calculates the nth Fibonacci number using a space-optimized iterative approach.
     *
     * @param n The position in the sequence (non-negative integer).
     * @return The nth Fibonacci number.
     */
    public static long fibIterative(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input n cannot be negative.");
        }
        // Base cases
        if (n <= 1) {
            return n;
        }

        long previous = 0; // Represents fib(i-2)
        long current = 1;  // Represents fib(i-1)

        for (int i = 2; i <= n; i++) {
            long next = previous + current;
            previous = current;
            current = next;
        }
        return current;
    }

    /**
     * Calculates the nth Fibonacci number using a simple, unoptimized recursive approach.
     * WARNING: This method has exponential time complexity O(2^n) and is very slow for n > 35.
     *
     * @param n The position in the sequence (non-negative integer).
     * @return The nth Fibonacci number.
     */
    public static long fibRecursiveUnoptimized(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input n cannot be negative.");
        }
        // Base Cases
        if (n <= 1) {
            return n;
        }
        // The classic, but inefficient, recursive step.
        return fibRecursiveUnoptimized(n - 1) + fibRecursiveUnoptimized(n - 2);
    }

    public static void main(String[] args) {
        int n = 45;
        System.out.println("--- Efficient Solutions for n = " + n + " ---");
        System.out.println("Memoized Recursive Result: " + fib(n));
        System.out.println("Iterative Result:          " + fibIterative(n));

        System.out.println("\n--- Visualizing the Recursion Tree for n = 4 ---");
        fibRecursiveUnoptimizedWithTrace(4, "");
    }

    /**
     * A recursive method that calculates Fibonacci and visualizes the inefficient recursion tree.
     * @param n The current number to compute the Fibonacci for.
     * @param indent The indentation string to show the recursion depth.
     * @return The nth Fibonacci number.
     */
    public static long fibRecursiveUnoptimizedWithTrace(int n, String indent) {
        System.out.println(indent + "-> Calling fib(" + n + ")");

        if (n <= 1) {
            System.out.println(indent + "<- Base case reached. Returning " + n);
            return n;
        }

        long result1 = fibRecursiveUnoptimizedWithTrace(n - 1, indent + "  ");
        long result2 = fibRecursiveUnoptimizedWithTrace(n - 2, indent + "  ");
        long finalResult = result1 + result2;
        System.out.println(indent + "<- Returning " + finalResult);
        return finalResult;
    }
}
