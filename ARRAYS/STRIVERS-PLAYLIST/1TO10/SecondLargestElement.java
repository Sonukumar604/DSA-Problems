import java.util.*;
public class SecondLargestElement {
    //Brute-Force: Time Complexity : o(NlogN + N), Space Complexity : O(1)
    public static int findSecondlargest(int[] arr){
        //dry run
        //arr = [1,2,4,6,3,7,5]
        //after sorting arr = [1,2,3,4,5,6,7]
        //largest = 7
        //i = 5; i >= 0; i--
        //if(arr[i] != largest)
        //arr[5] = 6; 6 != 7 true; return 6;

        //Time Complexity : o(NlogN + N)
        //because of Arrays.sort()
        //Space Complexity : O(1)
        //because we are not using any extra space
        //just using variables
        if(arr == null || arr.length < 2){
            return Integer.MIN_VALUE;
        }
        Arrays.sort(arr);
        int largest = arr[arr.length - 1];
        for(int i = arr.length - 2; i >= 0; i--){
            if(arr[i] != largest){
                return arr[i];
            }
        }
        return Integer.MIN_VALUE;
    }
    //Better : Time Complexity : O(2N), Space Complexity : o(1)
    public static int findSecondlargestBetter(int[] arr){
        //dry run
        //arr = [1,2,4,7,7,5,3,2]
        //largest = Integer.MIN_VALUE
        //i = 0; i < arr.length; i++
        //if(arr[i] > largest)
        // largest = 7
        //secondlargest = Integer.MIN_VALUE
        //i = 0; i < arr.length; i++
        //if(arr[i] > secondlargest && arr[i] < largest)
        //secondlargest = 5;
        //return 5;

        //Time Complexity : O(2N)
        //Space Complexity : o(1)

        if(arr == null || arr.length < 2){
            return Integer.MIN_VALUE;
        }
        int largest = Integer.MIN_VALUE;
        for(int current : arr){
            if(current > largest){
                largest = current;
            }
        }
        int secondlargest = Integer.MIN_VALUE;
        for(int current : arr){
            if(current > secondlargest && current < largest){
                secondlargest = current;
            }
        }
        return secondlargest;
    }
    //Optimal : Time Complexity : O(N), Space Complexity : O(1)
    public static int findSecondlargestOptimal(int[] arr){
        //dry run
        //arr = [1,3,4,2,6,7,5]
        //largest = Integer.MIN_VALUE
        //secondlargest = Integer.MIN_VALUE
        //i = 0; i < arr.length; i++
        //if(arr[i] > largest)
        //arr[0] = 1; 1 > Integer.MIN_VALUE true; secondlargest = Integer.MIN_VALUE, largest = 1
        //arr[1] = 3; 3 > 1 true; secondlargest = 1, largest = 3
        //arr[2] = 4; 4 > 3 true; secondlargest = 3, largest = 4
        //arr[3] = 2; 2 > 4 false; else if(current > secondlargest && current != largest) 2 > 3 false
        //arr[4] = 6; 6 > 4 true; secondlargest = 4, largest = 6
        //arr[5] = 7; 7 > 6 true; secondlargest = 6, largest = 7
        //arr[6] = 5; 5 > 7 false; else if(current > secondlargest && current != largest) 5 > 6 false
        //return 6;

        //Time Complexity : O(N)
        //we are just traversing through the array
        //Space Complexity : O(1)
        //we are not using any extra space just variables
        if(arr == null || arr.length < 2){
            return Integer.MIN_VALUE;
        }
        int largest = Integer.MIN_VALUE;
        int secondlargest = Integer.MIN_VALUE;
        for(int current : arr){
            if(current > largest){
                secondlargest = largest;
                largest = current;
            }else if(current > secondlargest && current != largest){
                secondlargest = current;
            }
        }
        return secondlargest;
    }
    //Second smallest 
    //Optimal : Time Complextiy : O(N), Space Complexity : O(1)
    public static int findSecondsmallestOptimal(int[] arr){
        //dry run
        //arr = [1,3,5,4,6,4,7,8,5,3];
        //smallest = Integer.MAX_VALUE
        //secondsmallest = Integer.MAX_VALUE
        //i = 0; i < arr.length; i++
        //if(arr[i] < smallest)
        //arr[0] = 1; 1 < Integer.MAX_VALUE true; secondsmallest = Integer.MAX_VALUE, smallest = 1;
        //arr[1] = 3; 3 < 1 false; else if(current < secondsmallest && current != smallest) 3 < Integer.MAX_VALUE && 3 != 1 true; secondsmallest = 3;
        //arr[2] = 5; 5 < 1 false; else if(current < secondsmallest && current != smallest) 5 < 3 false;
        //return 3

        //Time Complextiy : O(N)
        //we are just traversing through the array
        //Space Complexity : O(1)
        //we are not using any extra space just variables

        if(arr == null || arr.length < 2){
            return Integer.MAX_VALUE;
        }
        int smallest = Integer.MAX_VALUE;
        int secondsmallest = Integer.MAX_VALUE;
        for(int current : arr){
            if(current < smallest){
                secondsmallest = smallest;
                smallest = current;
            }
            else if(current < secondsmallest && current != smallest){
                secondsmallest = current;
            }
        }
        return secondsmallest;
    }
    public static void main(String[] args) {
        int[] arr = {1,3,5,4,6,4,7,8,5,3};
        System.out.println(findSecondlargest(arr));
        int[] arr1 = {1,3,5,4,6,4,7,8,5,3};
        System.out.println(findSecondlargestBetter(arr1));
        int[] arr2 = {1,3,5,4,6,4,7,8,5,3};
        System.out.println(findSecondlargestOptimal(arr2));
        int[] arr3 = {1,3,5,4,6,4,7,8,5,3};
        System.out.println(findSecondsmallestOptimal(arr3));
    }
}
