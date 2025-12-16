import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;

public class ScrambledStringMemoization {
    static Map<String , Boolean> dp = new HashMap<>();
    static boolean isScramble(String a, String b){
        if(a.length() != b.length())return false;
        int n = a.length();
        if(a.equals(b))return true;
        char[] arrA = a.toCharArray();
        char[] arrB = b.toCharArray();
        Arrays.sort(arrA);
        Arrays.sort(arrB);
        if(!Arrays.equals(arrA, arrB))return false; // FIX: Typo 'aarB' corrected to 'arrB'
        
        // FIX: This base case is redundant and potentially misleading.
        // If n=1 and a.equals(b) is true, it's handled.
        // If n=1 and a.equals(b) is false, the anagram check handles it.
        // if(n <= 1) return false; 

        String key = a + "|" + b;
        if(dp.containsKey(key)) return dp.get(key);
        boolean flag = false;
        for(int i = 1; i < n; i++){
            // FIX: Use substring() instead of length() and include both 'no swap' and 'swap' conditions
            if((isScramble(a.substring(0, i), b.substring(0, i)) && isScramble(a.substring(i), b.substring(i))) || // No swap
               (isScramble(a.substring(0, i), b.substring(n - i)) && isScramble(a.substring(i), b.substring(0, n - i)))) { // Swap
                flag = true; // If either condition is true, it's scrambled
                break; // No need to check further splits
            }
        }
        dp.put(key, flag);
        return flag;
    }
    public static void main(String[] args) {
        String s1 = "great";
        String s2 = "rgeat";
        System.out.println("is Scrambled? " + isScramble(s1, s2));

    }
}
