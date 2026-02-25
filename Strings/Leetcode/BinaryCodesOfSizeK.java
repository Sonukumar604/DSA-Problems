import java.util.HashSet;
import java.util.Set;

public class BinaryCodesOfSizeK {
    /**
     * Checks if a binary string contains all binary codes of size k.
     * 
     * Approach: Sliding Window with HashSet
     * 1. The total number of unique binary codes of length k is 2^k.
     * 2. We iterate through the string 's' using a sliding window of size 'k'.
     * 3. We extract every substring of length 'k' and add it to a HashSet.
     * 4. The HashSet automatically handles duplicates.
     * 5. Finally, we check if the size of the HashSet equals 2^k.
     * 
     * Time Complexity: O(N * K) - We iterate through the string, and substring/hashing takes O(K).
     * Space Complexity: O(2^K * K) - To store up to 2^K binary strings of length K.
     * 
     * Dry Run:
     * Input: s = "00110110", k = 2
     * Target Count (2^k): 4 ("00", "01", "10", "11")
     * 
     * i=0: sub="00", Set={"00"}
     * i=1: sub="01", Set={"00", "01"}
     * i=2: sub="11", Set={"00", "01", "11"}
     * i=3: sub="10", Set={"00", "01", "11", "10"} -> Size is 4. Return true.
     */
    public boolean hasAllCodes(String s, int k) {
        if (s.length() < k) return false;

        Set<String> set = new HashSet<>();
        int totalCodes = 1 << k; // 2^k

        for (int i = 0; i <= s.length() - k; i++) {
            set.add(s.substring(i, i + k));
            // Optimization: If we found all codes early, return true
            if (set.size() == totalCodes) return true;
        }

        return set.size() == totalCodes;
    }

    public static void main(String[] args) {
        BinaryCodesOfSizeK solution = new BinaryCodesOfSizeK();

        String s1 = "00110110";
        int k1 = 2;
        System.out.println("Has all codes (s=\"" + s1 + "\", k=" + k1 + "): " + solution.hasAllCodes(s1, k1)); // Expected: true

        String s2 = "0110";
        int k2 = 2;
        System.out.println("Has all codes (s=\"" + s2 + "\", k=" + k2 + "): " + solution.hasAllCodes(s2, k2)); // Expected: false
    }
}
