public class LargestOddNumber {
    /**
     * Finds the largest odd number that is a non-empty substring of the input string.
     *
     * ### Explanation
     * A number is odd if its last digit is odd. Therefore, the largest odd number that is a substring
     * of the input `num` must be a prefix of `num` that ends with an odd digit.
     *
     * To find this, we can iterate through the string `num` from right to left. The first odd digit
     * we encounter will be the last digit of our result. The substring from the beginning of `num`
     * up to and including this odd digit is the longest possible odd number substring, and thus the largest.
     *
     * If we iterate through the entire string and find no odd digits, it means no such substring exists,
     * and we should return an empty string.
     *
     * ### Dry Run
     * `num = "35427"`
     * 1. Start from the end: `i = 4`. `char` is '7'.
     * 2. Check if '7' is odd: `(7 - '0') % 2 == 1`. It is.
     * 3. Return the substring from the start up to this index: `num.substring(0, 4 + 1)` -> `"35427"`.
     *
     * `num = "52"`
     * 1. Start from the end: `i = 1`. `char` is '2'. Not odd.
     * 2. Move to `i = 0`. `char` is '5'.
     * 3. Check if '5' is odd: `(5 - '0') % 2 == 1`. It is.
     * 4. Return `num.substring(0, 0 + 1)` -> `"5"`.
     *
     * ### Complexity
     * - **Time Complexity:** O(N), where N is the length of the string. In the worst case, we traverse the entire string once.
     * - **Space Complexity:** O(1), as we use constant extra space. The space for the returned substring is not counted.
     */
    public String largestOddNumber(String num) {
        for (int i = num.length() - 1; i >= 0; i--) {
            char ch = num.charAt(i);
            // Check if the numeric value of the character is odd.
            if ((ch - '0') % 2 == 1) {
                // If it's odd, we've found the last digit of the largest odd number.
                // The result is the substring from the beginning up to this character.
                return num.substring(0, i + 1);
            }
        }
        // If no odd digit is found, no odd number substring exists.
        return "";
    }

    public static void main(String[] args) {
        LargestOddNumber solution = new LargestOddNumber();
        String num1 = "52";
        System.out.println("Largest Odd Number in '" + num1 + "': " + solution.largestOddNumber(num1)); // Expected: "5"

        String num2 = "4206";
        System.out.println("Largest Odd Number in '" + num2 + "': " + solution.largestOddNumber(num2)); // Expected: ""

        String num3 = "35427";
        System.out.println("Largest Odd Number in '" + num3 + "': " + solution.largestOddNumber(num3)); // Expected: "35427"
    }
}