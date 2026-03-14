public class SortedArray {
    public static boolean isSorted(int[] arr, int n){
        // Start the loop from the second element (index 1) to compare with the previous one.
        //dry run
        //arr = [1,2,3,4,5]
        //n = 5
        //i = 1; i < n; i++
        //if(arr[i] < arr[i - 1])
        //arr[1] < arr[0] = 2 < 1 false
        //arr[2] < arr[1] = 3 < 2 false
        //arr[3] < arr[2] = 4 < 3 false
        //arr[4] < arr[3] = 5 < 4 false
        //return true

        //arr = [5,4,3,2,1]
        //n = 5
        //i = 1; i < n; i++
        //if(arr[i] < arr[i - 1])
        //arr[1] < arr[0] = 4 < 5 true
        //return false

        //Time Complexity : o(N)
        //Space Complexity : o(1)
        //Because we are just using variables for comparison
        //so constant space
        for(int i = 1; i < n; i++){
            // If any element is less than its predecessor, the array is not sorted.
            // This also correctly handles arrays with duplicate values (e.g., {1, 2, 2, 3}).
            if(arr[i] < arr[i - 1]){
                return false;
            }
        }
        // If the loop completes without finding any out-of-order elements, the array is sorted.
        return true;
    }
    public static void main(String[] args) {
        int[] arr = {1,2,2,3,3,4,4};
        // Using a more conventional variable name.
        boolean isArraySorted = isSorted(arr, arr.length);
        System.out.println("Array is sorted: " + isArraySorted);
    }
}
