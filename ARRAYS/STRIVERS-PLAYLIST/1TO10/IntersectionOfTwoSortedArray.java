import java.util.ArrayList;
import java.util.Arrays;

public class IntersectionOfTwoSortedArray {
    // Optimal Two-Pointer Approach
    // Time Complexity: O(n1 + n2), Space Complexity: O(1) (excluding space for the answer)
    public static int[] findIntersection(int[] arr1, int[] arr2){
        ArrayList<Integer> intersection = new ArrayList<>();
        int i = 0, j = 0;
        while(i < arr1.length && j < arr2.length){
            // If arr1's element is smaller, move its pointer forward.
            if(arr1[i] < arr2[j]){
                i++;
            // If arr2's element is smaller, move its pointer forward.
            } else if (arr2[j] < arr1[i]) {
                j++;
            // If elements are equal, we have a potential intersection.
            } else {
                // Add the element to our list if it's the first one or not a duplicate.
                if(intersection.isEmpty() || intersection.get(intersection.size() - 1) != arr1[i]){
                    intersection.add(arr1[i]);
                }
                // Move both pointers forward.
                i++;
                j++;
            }
        }
        // Convert the ArrayList to an int array to return.
        return intersection.stream()
        .mapToInt(Integer::intValue)
        .toArray();
    }
    // Alternative approach using a temporary array to store the intersection
    // Time Complexity: O(n1 + n2), Space Complexity: O(min(n1, n2))
    public static int[] findIntersection1(int[] arr1, int[] arr2){
        ArrayList<Integer> intersection = new ArrayList<>();
        // Max possible intersection size is the length of the smaller array
        int[] temp = new int[Math.min(arr1.length, arr2.length)];
        int k = 0; // Pointer for the temp array
        int i = 0, j = 0;
        while(i < arr1.length && j < arr2.length){
            // If arr1's element is smaller, move its pointer forward.
            if(arr1[i] < arr2[j]){
                i++;
            // If arr2's element is smaller, move its pointer forward.
            } else if (arr2[j] < arr1[i]) {
                j++;
            // If elements are equal, we have a potential intersection.
            } else {
                // Add the element to our list if it's the first one or not a duplicate.
              
                if(k == 0 || temp[k - 1] != arr1[i]){
                    temp[k++] = arr1[i];
                }
                // Move both pointers forward.
                i++;
                j++;
            }
        }
       
        // Return a copy of the array with the exact size of elements found
        return Arrays.copyOf(temp, k);
    }
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 2, 3, 3, 4, 5, 6};
        int[] arr2 = {2, 3, 3, 5, 6, 6, 7};
        int[] result = findIntersection(arr1, arr2);
        System.out.println("Intersection of the two arrays: " + Arrays.toString(result));
        int[] result1 = findIntersection1(arr1, arr2);
        System.out.println("Intersection of the two arrays (method 2): " + Arrays.toString(result1));
    }
}
