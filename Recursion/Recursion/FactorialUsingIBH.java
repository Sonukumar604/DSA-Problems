/**
 * A utility class to demonstrate factorial calculation using recursion.
 * This class cannot be instantiated.
 */
public final class FactorialUsingIBH {

    // Private constructor to prevent instantiation of this utility class.
    private FactorialUsingIBH() {}

    /**
     * Calculates the factorial of a number using the Induction-Base-Hypothesis method.
     *
     * @param n The non-negative integer.
     * @return The factorial of n.
     */
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers.");
        }

        // Base Case (B): The simplest problem we can solve directly.
        // This stops the recursion.
        if (n == 0) {
            return 1;
        }

        // Induction (I) and Hypothesis (H) Step:
        // We assume factorial(n - 1) works (Hypothesis).
        // We use it to calculate factorial(n) (Induction).
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        int number = 6;
        System.out.printf("Calculating factorial of %d: %d%n", number, factorial(number));

        System.out.printf("%n--- Visualizing the Recursion Tree for n = 4 ---%n");
        // Call the trace method directly from main, providing the initial empty indentation.
        factorialWithTrace(4, "");
    }

    /**
     * A recursive method that calculates factorial and visualizes the recursion tree.
     * @param n The current number to compute the factorial for.
     * @param indent The indentation string to show the recursion depth.
     * @return The factorial of n.
     */
    public static long factorialWithTrace(int n, String indent) {
        System.out.println(indent + "-> Calling factorial(" + n + ")");

        // Base Case
        if (n == 0) {
            System.out.println(indent + "<- Base case reached. Returning 1.");
            return 1;
        }

        // Recursive Step
        long result = n * factorialWithTrace(n - 1, indent + "  ");

        System.out.println(indent + "<- Returning " + result + " (which is " + n + " * factorial(" + (n - 1) + "))");
        return result;
    }
}