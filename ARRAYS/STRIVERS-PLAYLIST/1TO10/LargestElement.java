import java.util.Arrays;
import java.util.*;
public class LargestElement {
    //Brute-Froce : Time complexity : O(NlogN), O(1).
    public static int findlargest(int[] arr){
        Arrays.sort(arr);
        return arr[arr.length - 1];
    }
    //Better and Optimal : Time complexity : O(n), O(1).
    public static int findlargestOptimal(int[] arr1){
        int largest = arr1[0];
        for(int i = 0; i < arr1.length; i++){
            if(arr1[i] > largest){
                largest = arr1[i];
            }
        }
        return largest;
    }
    public static void main(String[] args) {
        int[] arr = {3,5,7,2,8};
        System.out.println(findlargest(arr));

        int[] arr1 = {1,2,3,4,5,6,7,8,9};
        System.out.println(findlargestOptimal(arr1));
    }
}
