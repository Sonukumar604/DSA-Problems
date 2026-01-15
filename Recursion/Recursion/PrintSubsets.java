/**
 * This class demonstrates how to generate all subsets (the power set) of a string
 * using a recursive approach.
 */
public class PrintSubsets{
    /**
     * The core recursive function that generates and prints all subsets.
     * It follows a decision-tree model: for each character, it either includes it
     * in the subset or excludes it.
     *
     * @param input  The remaining part of the string to be processed.
     * @param output The current subset being built.
     */
    public static void solve(String input, String output){
        // Base Case: If the input string is empty, it means we have processed all
        // characters. The 'output' string now represents a complete subset, so we print it.
        if(input.length() == 0){
            System.out.println(output);
            return;
        }

        // Take the first character of the current input string.
        char ch = input.charAt(0);
        // Get the rest of the string, excluding the first character.
        String rest = input.substring(1);

        // Recursive Call 1: The "Exclude" choice.
        // We don't include the current character 'ch' in the output and move on to the rest of the string.
        solve(rest, output);
        // Recursive Call 2: The "Include" choice.
        // We append the current character 'ch' to the output and move on to the rest of the string.
        solve(rest, output + ch);
    }
    public static void main(String[] args) {
        String input = "abc";
        String output = "";
        System.out.println("Subsets of \"" + input + "\" are:");
        solve(input, output);
    }
}