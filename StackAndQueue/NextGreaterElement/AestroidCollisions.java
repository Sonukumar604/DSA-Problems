import java.util.Stack;
public class AestroidCollisions {
    public static int[] aestroidCollisions(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int n = arr.length;
        for(int i = 0; i < n - 1;i++){
            if(arr[i] > 0){// If the asteroid is moving right
                stack.push(arr[i]);// Push the right-moving asteroid
            }
            else{// If the asteroid is moving left
                while(!stack.isEmpty() && stack.peek() > 0 && stack.peek() < Math.abs(arr[i])){// Check for collisions
                    stack.pop();// Destroy the smaller asteroid
                }// End of collision check
                if(!stack.isEmpty() && stack.peek() == Math.abs(arr[i])){// Check for equal size asteroids
                    stack.pop();// Destroy both asteroids
                }else{// If the asteroid is larger
                    if(stack.isEmpty() || stack.peek() < 0){// Push the asteroid onto the stack
                        stack.push(arr[i]);// Push the left-moving asteroid
                    }
                }
            }
        }// End of asteroid processing
        int[] result = new int[stack.size()];// Create the result array
        for(int i = result.length - 1; i >= 0; i--){// Fill the result array
            result[i] = stack.pop();// Pop the remaining asteroids
        }
        return result;// Return the final state of asteroids
    }
    public static void main(String[] args) {
        int[] asteroids = {5, 10, -5};
        int[] result = aestroidCollisions(asteroids);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}
