import java.util.Stack;
import java.util.Scanner;
import java.lang.StringBuilder;
import java.lang.Character;
public class postfixToPrefix {
    public static String findPostfixToPrefix(String s) {
        Stack<String> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                stack.push(String.valueOf(ch));
            } else { // Operator
                String right = stack.pop();
                String left = stack.pop();
                String expr = ch + left + right ;// Concatenate in prefix order
                stack.push(expr);
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        // Scanner for user input
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter postfix expression: ");
        String postfix = sc.nextLine();
        String result = findPostfixToPrefix(postfix);
        System.out.println("Prefix: " + result);
        sc.close();
    }
}
