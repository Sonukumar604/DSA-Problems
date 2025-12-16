import java.util.*;
public class SecondLargestElement {
    //Brute-Force: Time Complexity : o(NlogN + N), Space Complexity : O(1)
    public static int findSecondlargest(int[] arr){
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
