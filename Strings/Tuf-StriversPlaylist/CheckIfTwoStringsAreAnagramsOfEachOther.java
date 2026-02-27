
public class CheckIfTwoStringsAreAnagramsOfEachOther {
    /**
     * Checks if two strings are anagrams of each other.
     * 
     * Approach: Frequency Array
     * 1. If lengths differ, they cannot be anagrams.
     * 2. Use a fixed-size integer array (size 26) to track character counts.
     * 3. Increment counts for characters in 's' and decrement for characters in 't'.
     * 4. If the array contains only zeros, the strings are anagrams.
     * 
     * Dry Run:
     * Input: s = "anagram", t = "nagaram"
     * 
     * i=0: s='a', t='n' => count['a']=1, count['n']=-1
     * i=1: s='n', t='a' => count['n']=0, count['a']=0
     * i=2: s='a', t='g' => count['a']=1, count['g']=-1
     * i=3: s='g', t='a' => count['g']=0, count['a']=0
     * ... (remaining characters cancel out)
     * 
     * Final check: All counts are 0. Return true.
     * 
     * Time Complexity: O(N) - We iterate through the strings once.
     * Space Complexity: O(1) - We use a fixed-size array of 26 integers (constant space).
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        for (int num : count) {
            if (num != 0) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        CheckIfTwoStringsAreAnagramsOfEachOther solution = new CheckIfTwoStringsAreAnagramsOfEachOther();
        String s1 = "anagram";
        String t1 = "nagaram";
        System.out.println("Is '" + s1 + "' and '" + t1 + "' an anagram? " + solution.isAnagram(s1, t1)); // Expected: true
        String s2 = "rat";
        String t2 = "car";
        System.out.println("Is '" + s2 + "' and '" + t2 + "' an anagram? " + solution.isAnagram(s2, t2)); // Expected: false
    }
}
