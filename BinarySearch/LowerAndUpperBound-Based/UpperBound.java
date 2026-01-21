public class UpperBound {
    public static int upperBound(int[] arr, int target){
        int low = 0;
        int high = arr.length - 1;
        int ans = arr.length;
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(arr[mid] > target){
                ans = mid;
                high = mid - 1;
            }
            else{
                low = mid + 1;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 8, 10, 12};
        int target = 7;

        int ub = upperBound(arr, target);
        System.out.println("Upper Bound index: " + ub);
    }
}
