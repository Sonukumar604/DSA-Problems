import java.util.HashSet;
import java.util.LinkedHashSet;

public class RemoveDuplicates {
    // Brute-Force using a Set: Time Complexity: O(N), Space Complexity: O(N)
    // Note: Using LinkedHashSet to preserve the original order of elements.
    public static int removeDuplicates(int[] arr){
        // LinkedHashSet maintains insertion order.
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        for(int value : arr){
            set.add(value);
        }
        int index = 0;
        for(int num : set){
            arr[index++] = num;
        }
        return index;
    }

    // Optimal Two-Pointer Approach: Time Complexity: O(N), Space Complexity: O(1)
    public static int removeDuplicatesOptimal(int[] arr){
        if(arr.length == 0){
            return 0;
        }
        int i = 0; // Pointer for the last unique element's position
        // Start j from 1 to compare with the element at index 0.
        for(int j = 1; j < arr.length; j++){
            if(arr[j] != arr[i]){
                i++; // Move the unique pointer first
                arr[i] = arr[j]; // Place the new unique element at the new position 'i'
            }
        }
        return i + 1; // The new length is the last index + 1
    }
    public static void main(String[] args){
        int[] arr = {1,1,2,2,2,3,3,3};
        int k1 = removeDuplicates(arr);
        System.out.println("Brute-force: New length is " + k1);
        System.out.print("Array after removing duplicates: ");
        for(int i = 0; i < k1; i++) System.out.print(arr[i] + " ");
        System.out.println("\n");

        int[] arr1 = {1,1,2,2,2,3,3,3};
        int k2 = removeDuplicatesOptimal(arr1);
        System.out.println("Optimal: New length is " + k2);
        System.out.print("Array after removing duplicates: ");
        for(int i = 0; i < k2; i++) System.out.print(arr1[i] + " ");
        System.out.println();
    }
}
