import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class UnionOfTwoSortedArray {
    //Brute-Force using HashSet: Time Complexity: O(n1 + n2), Space Complexity: O(n1 + n2)
    public static ArrayList<Integer> unionOfTwoSortedArray(int[] arr1, int[] arr2){
        HashSet<Integer>  set = new HashSet<>();
        for(int num : arr1){
            set.add(num);
        }
        for(int num : arr2){
            set.add(num);
        }
        // Note: HashSet does not preserve order. For a sorted union, this isn't ideal.
        // The optimal approach below is preferred.
        return new ArrayList<>(set);
    }
    //Optimal Two-Pointer Approach: Time Complexity: O(n1 + n2), Space Complexity: O(1) (for the answer list)
    public static ArrayList<Integer> union(int[] arr1, int[] arr2){
        int i = 0;
        int j = 0;
        ArrayList<Integer> union = new ArrayList<>();
        while(i < arr1.length && j < arr2.length){
            if(arr1[i] < arr2[j]){
                // Add the smaller element if the union is empty or the last element is different.
                if(union.isEmpty() || union.get(union.size() - 1) != arr1[i]){
                    union.add(arr1[i]);
                }
                i++;
            } else if (arr2[j] < arr1[i]) {
                if(union.isEmpty() || union.get(union.size() - 1) != arr2[j]){
                    union.add(arr2[j]);
                }
                j++;
            } else { // Both elements are equal
                if(union.isEmpty() || union.get(union.size() - 1) != arr1[i]){
                    union.add(arr1[i]);
                }
                i++;
                j++;
            }
        }
        // Add remaining elements from arr1, if any
        while(i < arr1.length){
            if(union.isEmpty() || union.get(union.size() - 1) != arr1[i]){
                union.add(arr1[i]);
            }
            i++;
        }
        // Add remaining elements from arr2, if any
        while(j < arr2.length){
            if(union.isEmpty() || union.get(union.size() - 1) != arr2[j]){
                union.add(arr2[j]);
            }
            j++;
        }
        return union;
    }
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5, 6};
        int[] arr2 = {2, 3, 5, 7};

        System.out.println("Array 1: " + Arrays.toString(arr1));
        System.out.println("Array 2: " + Arrays.toString(arr2));

        ArrayList<Integer> result = union(arr1, arr2);
        System.out.println("Union of the two arrays (Optimal): " + result);
    }
}
