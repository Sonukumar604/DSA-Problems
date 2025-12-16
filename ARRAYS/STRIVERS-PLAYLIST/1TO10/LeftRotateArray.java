import java.util.Arrays;

public class LeftRotateArray {
    // Method to left-rotate an array by one place
    // Time Complexity: O(N), Space Complexity: O(1)
    public void leftRotateByOne(int[] arr) {
        int n = arr.length;
        // No rotation needed for arrays with 0 or 1 element.
        if (n <= 1) return;

        // Store the first element in a temporary variable.
        int temp = arr[0];
        // Shift all elements one position to the left.
        for (int i = 1; i < n; i++) {
            arr[i - 1] = arr[i];
        }
        // Place the stored first element at the end of the array.
        arr[n - 1] = temp;
    }
    //Left Rotate by D places (Brute-force using a temporary array)
    //Time Complexity: O(N), Space Complexity: O(d)
    public void leftRotateByDPlaces(int[] arr, int d){
        int n = arr.length;
        if(n == 0) return; // No rotation needed for an empty array.
        // Handle cases where d is larger than n
        d = d % n;
        if(d == 0) return;

        // Store the first 'd' elements in a temporary array.
        int[] temp = new int[d];
        for(int i = 0; i < d; i++){
            temp[i] = arr[i];
        }
        // Shift the remaining elements to the left.
        for(int i = d; i < n; i++){
            arr[i - d] = arr[i];
        }
        // Place the elements from the temporary array at the end.
        for(int i = 0; i < d; i++){
            arr[n - d + i] = temp[i];
        }
    }
    //Optimal : Time Complexity : O(N), Space Complexity : O(1)
    public static void leftRotate(int[] arr, int d, int n){
        if(n <= 1) return;
        d = d % n;
        if(d == 0) return;
        reverse(arr, 0, d - 1);
        reverse(arr, d, n - 1);
        reverse(arr, 0, n - 1);
    }
    public static void reverse(int[] arr, int start, int end){
        while(start < end){
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        // --- Test 1: Left Rotate by One Place ---
        System.out.println("--- Testing Left Rotate by One Place ---");
        int[] arr1 = {1, 2, 3, 4, 5};
        LeftRotateArray rotator = new LeftRotateArray();
        System.out.println("Original Array: " + Arrays.toString(arr1));
        rotator.leftRotateByOne(arr1);
        System.out.println("After rotation: " + Arrays.toString(arr1));
        System.out.println();

        // --- Test 2: Left Rotate by D Places (Brute-force with temp array) ---
        System.out.println("--- Testing Left Rotate by D Places (Brute-force) ---");
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};
        int d2 = 3;
        System.out.println("Original Array: " + Arrays.toString(arr2));
        rotator.leftRotateByDPlaces(arr2, d2);
        System.out.println("After rotation by " + d2 + " places: " + Arrays.toString(arr2));
        System.out.println();

        // --- Test 3: Left Rotate by D Places (Optimal reversal algorithm) ---
        System.out.println("--- Testing Left Rotate by D Places (Optimal) ---");
        int[] arr3 = {10, 20, 30, 40, 50, 60};
        int d3 = 2;
        System.out.println("Original Array: " + Arrays.toString(arr3));
        leftRotate(arr3, d3, arr3.length); // This is a static method
        System.out.println("After rotation by " + d3 + " places: " + Arrays.toString(arr3));
    }
}
