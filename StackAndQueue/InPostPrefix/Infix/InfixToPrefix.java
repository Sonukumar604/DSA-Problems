import java.util.Scanner;
import java.util.Stack;
import java.lang.StringBuilder;

public class InfixToPrefix {
    public static String infixToPrefix(String s){
        Stack<Character> stack = new Stack<>();
        StringBuilder prefix = new StringBuilder();
        StringBuilder infix = new StringBuilder(s);
        // Reverse the infix expression
        infix.reverse();
        // Replace '(' with ')' and vice versa
        for (int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);
            if (ch == '(') {
                infix.setCharAt(i, ')');
            } else if (ch == ')') {
                infix.setCharAt(i, '(');
            }
        }
        for (char ch : infix.toString().toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                prefix.append(ch);
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    prefix.append(stack.pop());
                }
                stack.pop(); // Pop the '('
            } else { // Operator
                while (!stack.isEmpty() && precedence(ch) < precedence(stack.peek())) {
                    prefix.append(stack.pop());
                }
                stack.push(ch);
            }
        }
        
        while (!stack.isEmpty()) {
            prefix.append(stack.pop());
        }
        
        // Reverse the result to get the final prefix expression
        return prefix.reverse().toString();
    }
    public static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1; // For '(' or any invalid character
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter infix expression: ");
        String infix = sc.nextLine();
        String result = infixToPrefix(infix);
        System.out.println("Prefix: " + result);
        sc.close();
    }

}