public class MaximumSubarraySum {
    public static int maximumSubarraySum(int[] arr, int k){
        int i = 0, j = 0, sum = 0;
        int max = Integer.MIN_VALUE;
        while(j < arr.length){
            sum += arr[j];
            if(j - i + 1 < k){
                j++;
            }
            else if(j - i + 1 == k){
                max = Math.max(max, sum);
                sum -= arr[j];
                i++;
                j++;
            }
        }
        return max;
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        int k = 3;
        int result = maximumSubarraySum(arr, k);
        System.out.println("The Maximum Subarray Sum of " + k + " is: " + result);
    }
}
