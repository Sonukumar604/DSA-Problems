/**
 * Implements the `myAtoi` function which converts a string to a 32-bit signed integer.
 *
 * ### Explanation
 * The algorithm follows these steps:
 * 1.  **Skip Leading Whitespace:** Read and ignore any leading whitespace characters.
 * 2.  **Check for Sign:** Check if the next character is a '-' or '+'. This determines if the final number is negative or positive. If neither is present, the number is assumed to be positive.
 * 3.  **Convert Digits:** Read in the next characters as long as they are digits. Convert this sequence of digits into an integer.
 * 4.  **Handle Overflow:** During the conversion, if the number goes outside the 32-bit signed integer range `[-2^31, 2^31 - 1]`, clamp it to the respective limit (`Integer.MIN_VALUE` or `Integer.MAX_VALUE`).
 * 5.  **Return Result:** Return the final integer multiplied by its sign.
 *
 * ### Dry Run
 * Input: `s = "   -4193 with words"`
 *
 * 1.  **Initial State:** `i = 0`, `n = 20`, `sign = 1`, `result = 0`.
 *
 * 2.  **Skip Whitespace:**
 *     - `i=0, s[0]==' '`. `i` becomes 1.
 *     - `i=1, s[1]==' '`. `i` becomes 2.
 *     - `i=2, s[2]==' '`. `i` becomes 3.
 *
 * 3.  **Check for Sign:**
 *     - `i=3, s[3]=='-'`. `sign` becomes -1. `i` becomes 4.
 *
 * 4.  **Convert Digits & Check Overflow:**
 *     - `i=4, s[4]=='4'`. `digit = 4`.
 *       - Overflow check: `0 <= (2147483647 - 4) / 10`. True.
 *       - `result = 0 * 10 + 4 = 4`. `i` becomes 5.
 *     - `i=5, s[5]=='1'`. `digit = 1`.
 *       - Overflow check: `4 <= (2147483647 - 1) / 10`. True.
 *       - `result = 4 * 10 + 1 = 41`. `i` becomes 6.
 *     - `i=6, s[6]=='9'`. `digit = 9`.
 *       - Overflow check: `41 <= (2147483647 - 9) / 10`. True.
 *       - `result = 41 * 10 + 9 = 419`. `i` becomes 7.
 *     - `i=7, s[7]=='3'`. `digit = 3`.
 *       - Overflow check: `419 <= (2147483647 - 3) / 10`. True.
 *       - `result = 419 * 10 + 3 = 4193`. `i` becomes 8.
 *     - `i=8, s[8]==' '`. Not a digit. The loop terminates.
 *
 * 5.  **Return Result:**
 *     - `result * sign` => `4193 * -1` => `-4193`.
 *
 * ### Complexity
 * - **Time Complexity:** O(N), where N is the length of the string. We perform a single pass over the string.
 * - **Space Complexity:** O(1). We only use a few variables to store state, regardless of the input string size.
 */
class StringToInt_Atoi {
    public int myAtoi(String s){
        int i = 0, n = s.length(), sign = 1, result = 0;
        // 1. Skip Leading Whitespaces
        while(i < n && s.charAt(i) == ' '){
            i++;
        }
        // 2. Check for Optional Sign ('+' or '-')
        if(i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')){
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }
        // 3. Convert Digits to Integer
        while(i < n && Character.isDigit(s.charAt(i))){
            int digit = s.charAt(i) - '0';
            // 4. Check for Overflow before appending the digit
            if(result > (Integer.MAX_VALUE - digit) / 10){
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + digit;
            i++;
        }
        // 5. Return the final result with the correct sign
        return result * sign;
    }

    public static void main(String[] args) {
        StringToInt_Atoi solution = new StringToInt_Atoi();
        String[] testCases = {"42", "   -42", "4193 with words", "words and 987", "-91283472332", "+-12", ""};

        for (String testCase : testCases) {
            System.out.println("Input: \"" + testCase + "\" -> Output: " + solution.myAtoi(testCase));
        }
    }
}
