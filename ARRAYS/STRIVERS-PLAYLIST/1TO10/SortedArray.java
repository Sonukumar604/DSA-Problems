public class SortedArray {
    public static boolean isSorted(int[] arr, int n){
        // Start the loop from the second element (index 1) to compare with the previous one.
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
