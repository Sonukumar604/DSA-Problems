import java.util.Arrays;

public class ScrambledStringRecursive {
    public static boolean isScramble(String a, String b){
        // Base Case 1: If lengths are different, they can't be scrambled.
        if(a.length() != b.length()){
            return false;
        }
        // Base Case 2: If strings are identical, they are considered scrambled.
        if(a.equals(b)){
            return true;
        }

        // Optimization: If strings are not anagrams, they can't be scrambled.
        // This check significantly prunes the recursion tree.
        char[] arrA = a.toCharArray();
        char[] arrB = b.toCharArray();
        Arrays.sort(arrA);
        Arrays.sort(arrB);
        if(!Arrays.equals(arrA, arrB)){
            return false;
        }

        // Base Case 3: For strings of length > 1, if they are not equal but are anagrams,
        // we must check their children. If length is <= 1, and they are not equal, it's false.
        if(a.length() <= 1){
            return false; // Already handled by a.equals(b) but good for clarity.
        }

        int n = a.length();
        boolean flag = false;

        // Try every possible split point
        for(int i = 1; i < a.length(); i++){
            // Condition 1: No swap
            // Check if a[0...i-1] is a scramble of b[0...i-1] AND a[i...n-1] is a scramble of b[i...n-1]
            boolean noSwap = isScramble(a.substring(0, i), b.substring(0, i)) &&
                             isScramble(a.substring(i), b.substring(i));

            // Condition 2: Swap
            // Check if a[0...i-1] is a scramble of b[n-i...n-1] AND a[i...n-1] is a scramble of b[0...n-i-1]
            boolean swap = isScramble(a.substring(0, i), b.substring(n - i)) &&
                           isScramble(a.substring(i), b.substring(0, n - i));

            if(noSwap || swap){
                flag = true;
                break;
            }
        }
        return flag;
    }
    public static void main(String[] args) {
        String s1 = "great";
        String s2 = "rgeat";
        System.out.println("Is Scrambled: " + isScramble(s1, s2));
    }
}
