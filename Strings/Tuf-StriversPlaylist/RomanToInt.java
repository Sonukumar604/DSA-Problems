import java.util.HashMap;
import java.util.Map;

public class RomanToInt {
    /**
     * Converts a Roman numeral string to an integer.
     *
     * ### Explanation
     * Roman numerals are usually written largest to smallest from left to right. However, if a smaller numeral
     * appears before a larger one, it is subtracted (e.g., IV = 5 - 1 = 4).
     *
     * Algorithm:
     * 1. Create a mapping of Roman characters to their integer values.
     * 2. Iterate through the string from left to right.
     * 3. For each character, compare its value with the value of the next character.
     *    - If the current value is less than the next value, subtract the current value from the total.
     *    - Otherwise, add the current value to the total.
     * 4. The last character is always added.
     *
     * ### Dry Run
     * Input: `s = "MCMXCIV"`
     * Map: {I:1, V:5, X:10, L:50, C:100, D:500, M:1000}
     * Initialize `total = 0`. Length = 7.
     *
     * 1. i=0 ('M'): val=1000. Next ('C')=100. 1000 >= 100. Add 1000. Total = 1000.
     * 2. i=1 ('C'): val=100. Next ('M')=1000. 100 < 1000. Subtract 100. Total = 900.
     * 3. i=2 ('M'): val=1000. Next ('X')=10. 1000 >= 10. Add 1000. Total = 1900.
     * 4. i=3 ('X'): val=10. Next ('C')=100. 10 < 100. Subtract 10. Total = 1890.
     * 5. i=4 ('C'): val=100. Next ('I')=1. 100 >= 1. Add 100. Total = 1990.
     * 6. i=5 ('I'): val=1. Next ('V')=5. 1 < 5. Subtract 1. Total = 1989.
     * 7. i=6 ('V'): val=5. No next char. Add 5. Total = 1994.
     *
     * Result: 1994
     *
     * ### Complexity
     * - **Time Complexity:** O(N), where N is the length of the string. We iterate through the string once.
     * - **Space Complexity:** O(1). The map has a constant number of entries (7).
     */
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int total = 0;

        for (int i = 0; i < s.length(); i++) {
            int current = map.get(s.charAt(i));

            if (i < s.length() - 1 && current < map.get(s.charAt(i + 1))) {
                total -= current;
            } else {
                total += current;
            }
        }

        return total;
    }

    public static void main(String[] args) {
        RomanToInt solution = new RomanToInt();
        
        String s1 = "III";
        System.out.println("Input: \"" + s1 + "\" -> Output: " + solution.romanToInt(s1)); // Expected: 3
        
        String s2 = "LVIII";
        System.out.println("Input: \"" + s2 + "\" -> Output: " + solution.romanToInt(s2)); // Expected: 58
        
        String s3 = "MCMXCIV";
        System.out.println("Input: \"" + s3 + "\" -> Output: " + solution.romanToInt(s3)); // Expected: 1994
    }
}
