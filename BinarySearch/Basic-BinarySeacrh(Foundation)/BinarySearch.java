public class BinarySearch{
    /**
     * Iterative implementation of Binary Search.
     *
     * Time Complexity: O(log N)
     * Space Complexity: O(1)
     */
    public static int BinarySearchIterative(int[] arr, int target){
        int low = 0, high = arr.length - 1;
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(arr[mid] == target) return mid;
            else if(target > arr[mid]) return low = mid + 1;
            else return high = mid - 1;
        }
        return -1;
    }
    /**
     * Recursive implementation of Binary Search.
     *
     * Time Complexity: O(log N)
     * Space Complexity: O(log N) due to the recursion stack.
     */
    public static int BinarySearchRecursive(int[] arr, int low, int high, int target){
        if(low > high){
            return -1;
        }
        int mid = low + (high - low) / 2;
        if(arr[mid] == target){
            return mid;
        }
        else if(target > arr[mid]) return BinarySearchRecursive(arr, mid + 1, high, target);
        else return BinarySearchRecursive(arr, low, mid - 1, target);
    }
    public static void main(String[] args) {
        int[] arr = {2, 5, 8, 12, 16, 23, 38, 56, 72};
        int target = 23;
        int index = BinarySearchIterative(arr, target);
        System.out.println("Index: " + index);
        
        int[] arr1 = {2, 5, 8, 12, 16, 23, 38, 56, 72};
        int target = 38;
        int recursiveIndex = BinarySearchRecursive(arr1, 0, arr1.length - 1, target);
        System.out.println("Index: " + recursiveIndex);
    }
}