import java.util.Stack;
import java.lang.StringBuilder;
import java.util.Scanner;
import java.lang.Character;

public class InfixToPostfix {
    public static String infixToPostfix(String s){
        Stack<Character> stack = new Stack<>();
        StringBuilder postfix = new StringBuilder();
        
        for (char ch : s.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                postfix.append(ch);
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());//
                }
                stack.pop(); // Pop the '('
            } else { // Operator
                while (!stack.isEmpty() && precedence(ch) <= precedence(stack.peek())) {
                    postfix.append(stack.pop());//
                }
                stack.push(ch);
            }
        }
        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }
        return postfix.toString();
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
        // Scanner for user input
        Scanner sc = new Scanner(System.in);
        System.out.println("Infix to Postfix Converter");
        System.out.print("Enter infix expression: ");
        String infix = sc.nextLine();
        String result = infixToPostfix(infix);
        System.out.println("Postfix: " + result);
    }
}
