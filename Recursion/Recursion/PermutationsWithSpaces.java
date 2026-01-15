import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PermutationsWithSpaces {
    /**
     * The core recursive function that generates all permutations with optional spaces.
     * For each character in the input, it decides whether to prepend a space or not.
     *
     * @param input  The remaining characters of the string to be processed.
     * @param output The current permutation string being built.
     * @param result A list to store all the final generated permutations.
     */
    private static void solve(String input, String output, List<String> result){
        // Base Case: If there are no more characters to process, we have a complete
        // permutation. Add it to our results and stop this recursive path.
        if(input.length() == 0){
            result.add(output);
            return;
        }

        // Take the next character to be processed.
        char ch = input.charAt(0);
        // Get the rest of the string for the next recursive call.
        String rest = input.substring(1);

        // Recursive Call 1: The "add a space" choice.
        // We build the output string by adding a space and then the character.
        solve(rest, output + " " + ch, result);

        // Recursive Call 2: The "don't add a space" choice.
        // We build the output string by adding the character directly.
        solve(rest, output + ch, result);
    }
    public static void main(String[] args) {
        String input = "abc";
        List<String> result = new ArrayList<>();
        // The first character is used to start the output string.
        // The recursion then starts with the REST of the input string.
        solve(input.substring(1), "" + input.charAt(0), result);
        System.out.println("Permutations with spaces for '" + input + "': " + result);
    }
}