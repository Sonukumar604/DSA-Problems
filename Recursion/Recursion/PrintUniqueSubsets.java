import java.util.HashSet;

public class PrintUniqueSubsets {
    public static void solve(String input, String output, HashSet<String> set){
        if(input.length() == 0){
            // Base case: when the input is empty, we have a complete subset.
            // Add it to the set to ensure uniqueness.
            set.add(output);
            return;
        }
        char ch = input.charAt(0);
        String rest = input.substring(1);

        // Recursive call: Case where we DO NOT include the current character.
        solve(rest, output, set);
        // Recursive call: Case where we DO include the current character.
        solve(rest, output + ch, set);
    }
    public static void main(String[] args) {
        String input  = "aab"; // Using "aab" to demonstrate uniqueness
        String output = "";
        HashSet<String> set = new HashSet<>();
        solve(input, output, set);
        System.out.println("Unique subsets are: " + set);
    }
}
