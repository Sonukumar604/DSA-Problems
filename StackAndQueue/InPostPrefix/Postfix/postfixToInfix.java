import java.util.*;
public class postfixToInfix {
    public String convert(String postfix) {
        if (postfix == null || postfix.isEmpty()) {
            return "";
        }
        java.util.Stack<String> stack = new java.util.Stack<>();

        for (char c : postfix.toCharArray()) {
            if (Character.isLetter(c)) {
                stack.push(String.valueOf(c));
            } else {
                String right = stack.pop();
                String left = stack.pop();
                String expr = "(" + left + c + right + ")";
                stack.push(expr);
            }
        }
        return stack.pop();
    }
    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Enter postfix expression: ");
        String postfix = sc.nextLine();
        postfixToInfix converter = new postfixToInfix();
        String result = converter.convert(postfix);
        System.out.println("Infix: " + result);
        sc.close();
    }
}
