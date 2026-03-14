import java.util.HashSet;
import java.util.LinkedHashSet;

public class RemoveDuplicates {
    // Brute-Force using a Set: Time Complexity: O(N), Space Complexity: O(N)
    // Note: Using LinkedHashSet to preserve the original order of elements.
    public static int removeDuplicates(int[] arr){
        //dry run
        //arr = [1,1,2,2,3,4,5]
        //set = {}
        //i = 0; i < arr.length; i++
        //set.add(arr[i])
        //set = {1,2,3,4,5}
        //index = 0
        //num = 1,2,3,4,5
        //arr[index++] = num;
        //Time Complexity : O(N)
        //Space Complexity : O(N)
        //because we are using LinkedHashSet to store the unique elements
        //which can take up to O(N) space
        //where N is the number of elements in the array.
        //The time complexity is O(N) because
        //we iterate through the array once to add elements to the set,
        //and then iterate through the set once to copy the unique elements back to the array.
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
        //dry run
        //arr = [1,1,2,2,2,3,3,3]
        //i = 0;
        //j = 1; j < arr.length; j++
        //if(arr[j] != arr[i])
        //arr[1] != arr[0] = 1 != 1 false;
        //arr[2] != arr[0] = 2 != 1 true; i++, arr[i] = arr[j];
        //i = 1; arr[1] = 2;
        //arr = [1,2,2,2,2,3,3,3]
        //return i + 1;

        //Time Complexity : O(N)
        //Space Complexity : O(1)
        //because we are not using any extra space
        //explanation:
        //The time complexity of the provided code is O(N), where N is the length of the input array arr.
        //This is because the code iterates through the array once using a single for loop.
        //Inside the loop, there is a constant amount of work being done for each element in the array.
        //The space complexity is O(1) because the code uses a constant amount of extra space, regardless of the size of the input array.
        //The variables i and j are used to keep track of the current positions in the array, but they only take up a constant amount of space.
        //The code modifies the input array in-place, so it does not need to allocate any additional space.

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
}
