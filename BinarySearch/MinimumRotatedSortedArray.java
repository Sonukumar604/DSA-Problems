public class MinimumRotatedSortedArray {
    public static int finMin(int[] arr){
        int low = 0; 
        int high = arr.length - 1;
        int min = Integer.MAX_VALUE;
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(arr[low] <= arr[high]){
                min = Math.min(min, arr[low]);
                break;
            }
            if(arr[low] <= arr[mid]){
                min = Math.min(min, arr[low]);
                low = mid + 1;
            }
            else{
                min = Math.min(min, arr[mid]);
                high = mid - 1;
            }
        }
        return min;
    }
    public static void main(String[] args){
        int[] arr = {4,5,6,7,0,1,2};
        System.out.println("The minimum element in the rotated sorted array is: " + finMin(arr));
    }
}
