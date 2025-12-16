import java.util.Arrays; // Import for Arrays.toString()

public class MoveZerosToEnd {
    public static void moveZerosToEnd(int[] arr, int n){
        // Brute-force: Time Complexity: O(N), Space Complexity: O(N)
        int[] temp = new int[n]; // Corrected: temp should be an array
        int j = 0;
        for(int i = 0; i < n; i++){
            if(arr[i] != 0){
                temp[j] = arr[i];
                j++;
            }
        }
        // Copy non-zero elements back to the original array
        for(int i = 0; i < j; i++){ // Loop only up to 'j' (number of non-zero elements)
            arr[i] = temp[i];
        }
        // Fill the remaining positions with zeros
        for(int i = j; i < n; i++){
            arr[i] = 0;
        }
    }
    //Optimal Solution : Time Complexity : O(N), Space Complexity : O(1)
    public static void moveZerosToEndOptimal(int[] arr, int n){
        int j = 0;
        for(int i = 0; i < n; i++){
            if(arr[i] != 0){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                j++;
            }
        }
    }
    public static void main(String[] args) {
        int[] arr = {1,0,2,3,0,4,0,5,6,7,0,8,9,8,7,5,0}; // Added some zeros for better testing
        System.out.println("Original Array: " + Arrays.toString(arr));
        moveZerosToEnd(arr, arr.length); // Call the method to modify the array
        System.out.println("Array after moving zeros: " + Arrays.toString(arr)); 
        // Print the modified array
        int[] arr1 = {1,0,2,3,0,4,0,5,6,7,0,8,9,8,7,5,0}; // Added some zeros for better testing
        System.out.println("Original Array: " + Arrays.toString(arr1));
        moveZerosToEndOptimal(arr1, arr1.length); // Call the method to modify the array
        System.out.println("Array after moving zeros: " + Arrays.toString(arr1)); 
    }
}
