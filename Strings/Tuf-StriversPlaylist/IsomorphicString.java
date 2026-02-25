import java.util.HashMap;
import java.util.Map;

public class IsomorphicString {
    /**
     * Checks if two strings are isomorphic.
     *
     * Approach: Two HashMaps (Bidirectional Mapping)
     * 1. If lengths differ, strings cannot be isomorphic.
     * 2. Use one map for s -> t and another map for t -> s.
     * 3. For every character pair (c1, c2):
     *    - If c1 was mapped earlier, it must map to c2.
     *    - If c2 was mapped earlier, it must map to c1.
     * 4. If all pairs are consistent, strings are isomorphic.
     *
     * Time Complexity: O(N)
     * Explanation: We traverse both strings once, and each map operation
     * (containsKey/get/put) is O(1) on average.
     *
     * Space Complexity: O(K)
     * Explanation: In the worst case, we store mappings for all unique
     * characters. K is the number of distinct characters (at most N).
     */
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Character> mapST = new HashMap<>();
        Map<Character, Character> mapTS = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if (mapST.containsKey(c1) && mapST.get(c1) != c2) return false;
            if (mapTS.containsKey(c2) && mapTS.get(c2) != c1) return false;

            mapST.put(c1, c2);
            mapTS.put(c2, c1);
        }

        return true;
    }

    public static void main(String[] args) {
        IsomorphicString solution = new IsomorphicString();

        String s1 = "egg", t1 = "add";
        System.out.println("Is '" + s1 + "' and '" + t1 + "' isomorphic? " + solution.isIsomorphic(s1, t1)); // Expected: true

        String s2 = "foo", t2 = "bar";
        System.out.println("Is '" + s2 + "' and '" + t2 + "' isomorphic? " + solution.isIsomorphic(s2, t2)); // Expected: false

        String s3 = "paper", t3 = "title";
        System.out.println("Is '" + s3 + "' and '" + t3 + "' isomorphic? " + solution.isIsomorphic(s3, t3)); // Expected: true
    }
}
