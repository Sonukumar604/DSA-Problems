public class MissingNumber {
    public int findKthPositive(int[] arr, int k){
        int low = 0, high = arr.length - 1;
        while(low <= high){
            int mid = low + (high - low) / 2;
            int missingCount = arr[mid] - (mid + 1);
            if(missingCount < k){
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low + k;
    }
    public static void main(String[] args) {
        MissingNumber obj = new MissingNumber();
        int[] arr = {2,3,4,7,11};
        int k = 5;
        int result = obj.findKthPositive(arr, k);
        System.out.println("The " + k + "th missing positive number is: " + result);
    }
}
