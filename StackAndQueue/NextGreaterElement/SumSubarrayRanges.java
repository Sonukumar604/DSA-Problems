public class SumSubarrayRanges {
    public static int SumSubarrayRangesBruteForce(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        int totalRangeSum = 0;
        int n = arr.length;
        for(int i = 0; i < n; i++){
            int currentMin = arr[i];
            int currentMax = arr[i];
            for(int j = i; j < n; j++){
                currentMax = Math.max(currentMax, arr[j]);
                currentMin = Math.min(currentMin, arr[j]);
                totalRangeSum += (long)currentMax - currentMin;
            }
        }
        return totalRangeSum;
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        System.out.println("The sum of subarray ranges: " + SumSubarrayRangesBruteForce(arr));
        int[] arr2 = {4, -2, -3, 4, 1};
        System.out.println("The sum of subarray ranges: " + SumSubarrayRangesBruteForce(arr2));
    }
}
