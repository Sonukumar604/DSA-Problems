import java.util.Arrays;
import java.util.*;
public class LargestElement {
    //Brute-Froce : Time complexity : O(NlogN), O(1).
    public static int findlargest(int[] arr){
        Arrays.sort(arr);
        //dry run
        //arr = [3,5,7,2,8]
        //after sorting arr = [2,3,5,7,8]
        //return 8
        //Time Complexity : o(NlogN) because of Arrays.sort()
        //Space Complexity : O(1)
        return arr[arr.length - 1];
    }
    //Better and Optimal : Time complexity : O(n), O(1).
    public static int findlargestOptimal(int[] arr1){
        int largest = arr1[0];
        //dry run
        //arr1 = [1,2,3,4,5,6,7,8,9]
        //largest = 1
        //i = 0; i < arr1.length; i++
        //if(arr1[i] > largest)
        //i = 0; arr1[0] = 1; 1 > 1 false
        //i = 1; arr1[1] = 2; 2 > 1 true; largest = 2
        //i = 2; arr1[2] = 3; 3 > 2 true; largest = 3
        //i = 3; arr1[3] = 4; 4 > 3 true; largest = 4
        //i = 4; arr1[4] = 5; 5 > 4 true; largest = 5
        //i = 5; arr1[5] = 6; 6 > 5 true; largest = 6
        //i = 6; arr1[6] = 7; 7 > 6 true; largest = 7
        //i = 7; arr1[7] = 8; 8 > 7 true; largest = 8
        //i = 8; arr1[8] = 9; 9 > 8 true; largest = 9
        //return 9

        //Time Complexity : O(N)
        // explanation:
        //The time complexity of the provided code is O(N), where N is the number of elements in the input array arr1.
        //Here’s a breakdown:
        //Initialization:
        //int largest = arr1[0];: This operation takes constant time, O(1).
        //Loop through the array:
        //The for loop iterates through each element of the array arr1 once.
        //Inside the loop, there is a comparison if(arr1[i] > largest) and an assignment largest = arr1[i] that take constant time, O(1).
        //Therefore, the loop as a whole takes O(N) time because it performs a constant amount of work for each of the N elements in the array.
        //Return statement:
        //return largest;: This operation takes constant time, O(1).
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

//Space Complexity : O(1)
//explanation:The space complexity of the provided code is O(1), which means it uses a constant amount of space regardless of the size of the input array.
}
