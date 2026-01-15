import java.util.ArrayList;
import java.util.List;

public class josephusProblem {
    // The original class name was `josephusProblem` (lowercase 'j').
    // I'm keeping it as is to match the file name, but `JosephusProblem`
    // would be the standard Java naming convention.


    // Private constructor to prevent instantiation.
    private JosephusProblem() {}

    /**
     * Recursively simulates the Josephus problem to find the last survivor.
     *
     * @param people The list of people remaining.
     * @param k      The counting step.
     * @param index  The starting index for the current round.
     * @return The number of the surviving person.
     */
    public static int solve(List<Integer> people, int k, int index){
        int n = people.size();
        // Base case: when only one person is left, they are the survivor.
        if(n == 1){
            return people.get(0);
        }
        index = (index + k - 1) % n;
        people.remove(index);
        return solve(people, k, index);
    }

    /**
     * The main method to demonstrate the Josephus problem solver.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args){
        int n = 7; // Total number of people
        int k = 3; // Count every 3rd person

        List<Integer> people = new ArrayList<>();
        for(int i = 1; i <= n; i++){
            people.add(i);
        }

        int survivor = solve(people, k, 0);
        System.out.printf("For n=%d and k=%d, the survivor is: %d%n", n, k, survivor); // Correct output for n=7, k=3 is 4
    }
}
