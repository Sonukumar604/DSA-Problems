import java.util.Stack;
import java.util.Scanner;
import java.lang.StringBuilder;
import java.lang.Character;
import java.lang.String;
import java.lang.Exception;
import java.lang.System;
import java.lang.IndexOutOfBoundsException;
import java.lang.NullPointerException;
import java.lang.ArithmeticException;
public class PrefixToInfix {
    public static String findPrefixToInfix(String s){
        Stack<String> stack = new Stack<>();
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                stack.push(String.valueOf(ch));
            } else { // Operator
                String left = stack.pop();
                String right = stack.pop();
                String expr = "(" + left + ch + right + ")";
                stack.push(expr);
            }

        }
        return stack.pop();

    }
    public static void main(String[] args) {
        // Scanner for user input
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter prefix expression: ");
        String prefix = sc.nextLine();
        String result = findPrefixToInfix(prefix);
        System.out.println("Infix: " + result);
        sc.close();
    }
}