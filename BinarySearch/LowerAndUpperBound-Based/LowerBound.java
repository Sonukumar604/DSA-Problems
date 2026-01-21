public class LowerBound {
    public static int lowerBound(int[] arr, int target){
        int low = 0;
        int high = arr.length - 1;
        int ans = arr.length;
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(arr[mid] >= target){
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

        int lb = lowerBound(arr, target);
        System.out.println("Lower Bound index: " + lb);
    }
}
