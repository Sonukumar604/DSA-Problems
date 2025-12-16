import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RearrangeArray {
    // Brute-force/Better Approach
    // Time Complexity: O(N), Space Complexity: O(N)
    public static int[] rearrangeArrayBySign(int[] arr) {
        int n = arr.length;
        List<Integer> pos = new ArrayList<>();
        List<Integer> neg = new ArrayList<>();
        for (int num : arr) {
            if (num > 0) { // Standard problem assumes 0 is not present
                pos.add(num);
            } else {
                neg.add(num);
            }
        }
        // The loop should run for n/2 times as we are placing pairs.
        for (int i = 0; i < n / 2; i++) {
            arr[2 * i] = pos.get(i); // Even indices for positives
            arr[2 * i + 1] = neg.get(i); // Odd indices for negatives
        }
        return arr;
    }

    // Optimal Approach
    // Time Complexity: O(N), Space Complexity: O(1) (excluding answer array)
    public static int[] rearrangeArrayBySignOptimal(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        int posIndex = 0, negIndex = 1;
        for (int num : arr) {
            if (num > 0) {
                ans[posIndex] = num;
                posIndex += 2;
            } else {
                ans[negIndex] = num;
                negIndex += 2;
            }
        }
        return ans;
    }

    // Variety 2: Unequal number of positive and negative elements
    public static int[] alternateNumbers(int[] arr){
        List<Integer> pos = new ArrayList<>();
        List<Integer> neg = new ArrayList<>();
        for(int num : arr){
            if(num >= 0){
                pos.add(num);
            }else{
                neg.add(num);
            }
        }
        // Merge the positive and negative lists into the result array
        int[] result = new int[arr.length];
        int i = 0, j = 0, k = 0;
        while(i < pos.size() && j < neg.size()){
            result[k++] = pos.get(i++);
            result[k++] = neg.get(j++);
        }
        // If there are any remaining elements in either list, add them
        while(i < pos.size()){
            result[k++] = pos.get(i++);
        }
        while(j < neg.size()){
            result[k++] = neg.get(j++);
        }
        return result;
    }
    // Custom rearrange method similar to the brute-force approach
    void rearrange(ArrayList<Integer> arr){
        int n = arr.size();
        ArrayList<Integer> temp = new ArrayList<>(arr);
        int left = 0; // even index
        int right = 1; // odd index

        for(int i = 0; i < n; i++){
            if(temp.get(i) >= 0){
                arr.set(left, temp.get(i));
                left += 2;
            } else {
                arr.set(right, temp.get(i));
                right += 2;
            }
        }
    }
    public static void main(String[] args) {
        // --- Test Case 1: Equal number of positive and negative numbers ---
        System.out.println("--- Test Case 1: Equal Pos/Neg ---");
        int[] arr1 = {3, 1, -2, -5, 2, -4};
        System.out.println("Original Array: " + Arrays.toString(arr1));

        // Test Optimal method (returns a new array)
        int[] resultOptimal = rearrangeArrayBySignOptimal(arr1);
        System.out.println("Rearranged Array (Optimal): " + Arrays.toString(resultOptimal));

        // Test Brute-force/Better method (modifies array in-place)
        int[] arr1_copy = Arrays.copyOf(arr1, arr1.length);
        rearrangeArrayBySign(arr1_copy);
        System.out.println("Rearranged Array (Brute-force/Better): " + Arrays.toString(arr1_copy));
        System.out.println();

        // --- Test Case 2: Unequal number of positive and negative numbers ---
        System.out.println("--- Test Case 2: Unequal Pos/Neg ---");
        int[] arr2 = {-1, 2, 3, 4, -3, -2, 5};
        System.out.println("Original Array: " + Arrays.toString(arr2));
        int[] resultAlternate = alternateNumbers(arr2);
        System.out.println("Rearranged Array (Alternate): " + Arrays.toString(resultAlternate));

        // --- Test Case 3: Custom rearrange method ---
        System.out.println("\n--- Test Case 3: Custom rearrange method ---");
        // Using an input with equal positive/negative numbers to match the method's logic and avoid crashing.
        ArrayList<Integer> arr3 = new ArrayList<>(Arrays.asList(3, 1, -2, -5, 2, -4));
        System.out.println("Original ArrayList: " + arr3);
        
        // Create an instance to call the non-static method
        RearrangeArray rearranger = new RearrangeArray();
        rearranger.rearrange(arr3);
        System.out.println("Rearranged ArrayList: " + arr3);
    }
}
