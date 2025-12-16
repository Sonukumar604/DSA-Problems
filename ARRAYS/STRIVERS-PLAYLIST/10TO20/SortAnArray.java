import java.util.Arrays;

public class SortAnArray {
    // Better Approach: Counting Sort
    // Time Complexity: O(2N), Space Complexity: O(1)
    public static void SortAnArrayByCounting(int[] arr){
        int count0 = 0, count1 = 0, count2 = 0;
        for(int num : arr){
            if(num == 0)count0++;
            else if(num == 1)count1++;
            else count2++;
        }
        // Place all 0s
        for(int i = 0; i < count0; i++){
            arr[i] = 0;
        }
        // Place all 1s
        for(int i = count0; i < count0 + count1; i++){
            arr[i] = 1;
        }
        // Place all 2s
        for(int i = count0 + count1; i < arr.length; i++){
            arr[i] = 2;
        }
    }
    //Dutch National Flag Algorithm
    //Time Complexity: O(N), Space Complexity: O(1)
    public static void SortArrayDutch(int[] arr){
        int low = 0, mid = 0;
        int high = arr.length - 1;
        while(mid <= high){
            switch(arr[mid]){
                case 0:
                    swap(arr, low, mid);
                    low++;
                    mid++;
                    break;
                case 1: 
                    mid++;
                    break;
                case 2:
                    swap(arr, mid, high);
                    high--;
                    break;        
            }
        }
    }
    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void main(String[] args) {
        int[] arr1 = {0, 1, 2, 0, 1, 2, 1, 2, 0, 0, 0, 1};
        System.out.println("Original Array: " + Arrays.toString(arr1));
        SortAnArrayByCounting(arr1);
        System.out.println("Sorted by Counting: " + Arrays.toString(arr1));

        int[] arr2 = {2, 0, 2, 1, 1, 0};
        System.out.println("\nOriginal Array: " + Arrays.toString(arr2));
        SortArrayDutch(arr2);
        System.out.println("Sorted by Dutch Flag Algorithm: " + Arrays.toString(arr2));
    }
}
