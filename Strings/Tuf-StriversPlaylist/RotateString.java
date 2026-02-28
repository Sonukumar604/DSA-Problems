
public class RotateString {
    /**
     * Checks if one string is a rotation of another.
     * 
     * Approach: Concatenation
     * 1. Check if lengths are equal. If not, they can't be rotations.
     * 2. Concatenate 's' with itself. This new string contains all possible rotations of 's'.
     * 3. Check if 'goal' is a substring of the concatenated string.
     * 
     * Time Complexity: O(N) - String concatenation takes O(N). 'contains' takes O(N) on average.
     * Space Complexity: O(N) - To store the concatenated string.
     */
    public boolean rotateString(String s, String goal) {
        int n = s.length();
        if(n != goal.length()) return false;
        String combined = s + s;
        return combined.contains(goal);
    }
    public static void main(String[] args) {
        RotateString solution = new RotateString();

        System.out.println("Can 'abcde' rotate to 'cdeab'? " + solution.rotateString("abcde", "cdeab")); // Expected: true
        System.out.println("Can 'abcde' rotate to 'abced'? " + solution.rotateString("abcde", "abced")); // Expected: false
    }
}
