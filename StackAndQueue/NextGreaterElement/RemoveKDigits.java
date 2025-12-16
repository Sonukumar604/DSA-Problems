import java.util.Stack;

public class RemoveKDigits {
    /**
     * Removes k digits from a number string to make the new number the smallest possible.
     * This is solved using a monotonic stack to greedily remove larger digits from more
     * significant positions.
     *
     * @param num The input number as a string.
     * @param k   The number of digits to remove.
     * @return The smallest possible number string after removing k digits.
     */
    public static String removeKDigits(String num, int k) {
        // 1. Handle edge cases
        if (num == null || num.length() == 0) {
            return "0";
        }
        if (k >= num.length()) {
            return "0"; // If we remove all or more digits, the result is 0.
        }
        if (k == 0) {
            return num; // If we remove no digits, return the original number.
        }

        Stack<Character> stack = new Stack<>();

        // 2. Iterate through each digit and build the monotonic stack
        for (char digit : num.toCharArray()) {
            // While stack has digits, we have removals left, and the top digit is larger
            // than the current one, pop the larger digit.
            while (!stack.isEmpty() && k > 0 && stack.peek() > digit) {
                stack.pop();
                k--;
            }
            stack.push(digit);
        }

        // 3. If k > 0, it means the digits were in increasing order (e.g., "12345").
        // Remove the remaining largest digits from the end of the number (top of the stack).
        while (k > 0) {
            stack.pop();
            k--;
        }

        // 4. Build the result string from the stack.
        StringBuilder sb = new StringBuilder();
        for (char digit : stack) {
            sb.append(digit);
        }

        // 5. Remove leading zeros from the result.
        // For example, if num="100" and k=1, the result in the stack is "00".
        // We need to trim this to "0".
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("num=1432219, k=3 -> " + removeKDigits("1432219", 3)); // Expected: 1219
        System.out.println("num=10200, k=1 -> " + removeKDigits("10200", 1));   // Expected: 200
        System.out.println("num=10, k=2 -> " + removeKDigits("10", 2));         // Expected: 0
        System.out.println("num=12345, k=2 -> " + removeKDigits("12345", 2));   // Expected: 123
    }
}
