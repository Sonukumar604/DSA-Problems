public class BinaryAdd {
    /**
     * Adds two binary strings and returns their sum as a binary string.
     *
     * ### Explanation
     * The algorithm performs bitwise addition starting from the least significant bit (rightmost) to the most significant bit (leftmost).
     * It maintains a `carry` variable to handle overflows from one bit position to the next.
     * 1. Initialize two pointers, `i` and `j`, pointing to the end of strings `a` and `b` respectively.
     * 2. Initialize `carry` to 0.
     * 3. Loop while either pointer is valid (>= 0) or there is a non-zero carry.
     * 4. In each iteration:
     *    - Calculate `sum` starting with `carry`.
     *    - If `i` is valid, add the digit at `a[i]` to `sum` and decrement `i`.
     *    - If `j` is valid, add the digit at `b[j]` to `sum` and decrement `j`.
     *    - Append `sum % 2` (the result bit) to the `StringBuilder`.
     *    - Update `carry` to `sum / 2`.
     * 5. Finally, reverse the `StringBuilder` to get the correct order and convert it to a string.
     *
     * ### Dry Run
     * Input: `a = "1010"`, `b = "1011"`
     * Lengths: `a`=4, `b`=4. Pointers: `i`=3, `j`=3. `carry`=0.
     *
     * 1. **Iteration 1:**
     *    - `i`=3 ('0'), `j`=3 ('1'). `sum` = 0 + 0 + 1 = 1.
     *    - Append `1 % 2` = '1'. Result: "1". `carry` = 0.
     * 2. **Iteration 2:**
     *    - `i`=2 ('1'), `j`=2 ('1'). `sum` = 0 + 1 + 1 = 2.
     *    - Append `2 % 2` = '0'. Result: "10". `carry` = 1.
     * 3. **Iteration 3:**
     *    - `i`=1 ('0'), `j`=1 ('0'). `sum` = 1 + 0 + 0 = 1.
     *    - Append `1 % 2` = '1'. Result: "101". `carry` = 0.
     * 4. **Iteration 4:**
     *    - `i`=0 ('1'), `j`=0 ('1'). `sum` = 0 + 1 + 1 = 2.
     *    - Append `2 % 2` = '0'. Result: "1010". `carry` = 1.
     * 5. **Iteration 5:**
     *    - `i`=-1, `j`=-1. `carry`=1. `sum` = 1.
     *    - Append `1 % 2` = '1'. Result: "10101". `carry` = 0.
     *
     * End: Reverse "10101" -> "10101".
     *
     * ### Complexity
     * - **Time Complexity:** O(max(N, M)), where N and M are the lengths of strings `a` and `b`.
     * - **Space Complexity:** O(max(N, M)), to store the result in the `StringBuilder`.
     */
    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;
            if (i >= 0) {
                sum += a.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }
            result.append(sum % 2);
            carry = sum / 2;
        }

        return result.reverse().toString();
    }

    public static void main(String[] args) {
        BinaryAdd solution = new BinaryAdd();

        String a1 = "11";
        String b1 = "1";
        System.out.println("Input: a = \"" + a1 + "\", b = \"" + b1 + "\"");
        System.out.println("Output: " + solution.addBinary(a1, b1)); // Expected: "100"

        String a2 = "1010";
        String b2 = "1011";
        System.out.println("Input: a = \"" + a2 + "\", b = \"" + b2 + "\"");
        System.out.println("Output: " + solution.addBinary(a2, b2)); // Expected: "10101"
    }
}
