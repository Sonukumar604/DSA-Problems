public class MissingValue {
    //Brute Force Approach
    //Time Complexity: O(n^2), Space Complexity: O(1)
    public static int findMissingValue(int[] arr, int n){
        for(int i = 1; i <= n; i++){
            boolean isFound = false;
            for(int val : arr){
                if(val == i){
                    isFound = true;
                    break;
                }
            }
            if(!isFound){
                return i;
            }
        }
        return -1;
    }
    //Better Approach
    //Time Complextiy : O(N) + O(N), Space Complexity: O(N)
    public static int findMissingValueBetter(int[] arr, int n){
        int[] hash = new int[n + 1];
        for (int val : arr) {
            // The check `val <= n` handles cases where the array might contain numbers
            // larger than the expected range, preventing an ArrayIndexOutOfBoundsException.
            if (val <= n) {
                hash[val] = 1;
            }
        }
        for (int i = 1; i <= n; i++) {
            if (hash[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    // Optimal Approach 1: Summation
    // Time Complexity: O(N), Space Complexity: O(1)
    public static int findMissingValueOptimalSum(int[] arr, int n) {
        // Calculate the expected sum of the first 'n' natural numbers.
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;
        // Calculate the sum of elements in the given array.
        for (int val : arr) {
            actualSum += val;
        }
        // The difference is the missing number.
        return expectedSum - actualSum;
    }

    // Optimal Approach 2: XOR
    // Time Complexity: O(N), Space Complexity: O(1)
    public static int findMissingValueOptimalXOR(int[] arr, int n) {
        int xor1 = 0; // XOR of all numbers from 1 to n
        int xor2 = 0; // XOR of all numbers in the array
        for (int i = 1; i <= n; i++) {
            xor1 = xor1 ^ i;
        }
        for (int val : arr) {
            xor2 = xor2 ^ val;
        }
        // XORing the two results gives the missing number.
        return xor1 ^ xor2;
    }
    //Optimal Approach 3: XOR in single loop
    //Time Complexity : O(N), Space Complexity: O(1)
    public static int findMissingValueOptimalXOR2(int[] arr, int n){
        int xorSum = 0;
        // The array has n-1 elements. The loop will run from i=0 to n-2.
        // We XOR the array element with its corresponding expected number (i+1).
        for(int i = 0; i < n - 1; i++){
            xorSum = xorSum ^ arr[i];
            xorSum = xorSum ^ (i + 1);
        }
        // Finally, XOR with 'n' itself, which was not part of the loop.
        // The result is the missing number.
        return xorSum ^ n;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 6, 5};
        int n = 6;
        System.out.println("Brute Force: The missing value is: " + findMissingValue(arr, n));
        System.out.println("Better (Hashing): The missing value is: " + findMissingValueBetter(arr, n));
        System.out.println("Optimal (Summation): The missing value is: " + findMissingValueOptimalSum(arr, n));
        System.out.println("Optimal (XOR): The missing value is: " + findMissingValueOptimalXOR(arr, n));
        System.out.println("Optimal (XOR2): The missing value is: " + findMissingValueOptimalXOR2(arr, n));
    }
}
