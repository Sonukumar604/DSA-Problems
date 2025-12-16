public class SearchElementInSortedArray {
    public static int binarySearchUnique(int[] arr, int target){
        int low = 0;
        int high = arr.length - 1;
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(arr[mid] == target){
                return mid;
            }
            //Left Part is SOrted
            if(arr[low] <= arr[mid]){
                if(arr[low] <= target && target < arr[mid]){
                    high = mid - 1;
                }
                else{
                    low = mid + 1;
                }
            }
            //Right Part is Sorted
            else{
                if(arr[mid] < target && target <= arr[high]){
                    low = mid + 1;
                }else{
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
    public static boolean binarySearchDuplicate(int[] arr, int target){
        int low = 0;
        int high = arr.length - 1;
        while(low <= high){
            int mid = low + (high - low ) / 2;
            if(arr[mid] == target){
                return true;
            }
            if(arr[low] == arr[mid] && arr[mid] == arr[high]){
                low++;
                high--;
            }else if(arr[low] <= arr[mid]){
                if(arr[low] <= target && target < arr[mid]){
                    high = mid -1;
                }
                else{
                    low = mid + 1;
                }
            }
            else{
                if(arr[mid] < target && target <= arr[high]){
                    low = mid + 1;
                }
                else{
                    high = mid - 1;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 7, 1, 0, 2};
        int target = 0;
        int result = binarySearchUnique(arr, target);

        System.out.println("Index: " + result);
        int[] arr2 = {2, 5, 6, 0, 0, 1, 2};
        int target2 = 0;
        boolean result2 = binarySearchDuplicate(arr2, target2);
        System.out.println("Found: " + result2);
    }
}
