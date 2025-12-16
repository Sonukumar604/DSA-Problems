import java.util.*;
import java.util.Stack;
@SuppressWarnings("unused")
public class balancedParanthesis {
    public static boolean isBalanced(String str){
        Stack<Character> stack = new Stack<>();
        for(char ch : str.toCharArray()){
            if(ch == '(' || ch == '[' || ch == '{'){
                stack.push(ch);
            }
            else if(ch == ')' || ch == ']' || ch == '}'){
                if(stack.isEmpty())
                return false;
                char top = stack.pop();
                if((ch == ')' && top != '(') || (ch == ']' && top != '[') || (ch == '}' && top != '{')){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
    public static void main(String[] args) {
        String str1 = "{[()]}";
        String str2 = "(([]){}[])";
        System.out.println(str1 + " -> " + isBalanced(str1));
        System.out.println(str2 + " -> " + isBalanced(str2));
    }
}
