/**
 * A utility class to demonstrate generating string permutations with case changes using recursion.
 * This class cannot be instantiated.
 */
public final class PermutationWithCaseChange {

    // Private constructor to prevent instantiation of this utility class.
    private PermutationWithCaseChange() {}

    /**
     * Recursively generates all permutations of a string by changing the case of letters.
     * Digits and other characters are kept as is.
     *
     * @param input  The remaining characters to process.
     * @param output The string being built so far.
     */
    public static void solve(String input, String output) {
        if (input.isEmpty()) {
            System.out.println(output);
            return;
        }
        char ch = input.charAt(0);
        String rest = input.substring(1);

        // Corrected Logic: Branch only on letters.
        if (Character.isLetter(ch)) {
            // It's a letter, so create two branches: one for lowercase, one for uppercase.
            solve(rest, output + Character.toLowerCase(ch));
            solve(rest, output + Character.toUpperCase(ch));
        } else {
            // It's a digit or another symbol, so create only one branch.
            solve(rest, output + ch);
        }
    }
    public static void main(String[] args) {
        String input = "a1c";
        System.out.println("Permutations of " + input + ":");
        solve(input, "");
    }
}
