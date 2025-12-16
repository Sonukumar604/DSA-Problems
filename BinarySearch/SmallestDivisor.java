public class SmallestDivisor {
    private boolean canDivide(int[] nums, int divisor, int threshold){
        int sum = 0; // Use double for sum to avoid potential overflow with large inputs, though int is fine for this example.
        for(int num : nums){
            sum += (num + divisor - 1) / divisor; // Corrected from `nums` to `num`
            if(sum > threshold){
                return false;
            }
        }
        return sum <= threshold;
    }
    public int smallestDivisor(int[] nums, int threshold){
        int left = 1;
        int right = 0;
        for(int num : nums){
            right = Math.max(right, num);
        }
        int result = right;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if(canDivide(nums, mid, threshold)){
                result = mid;
                right = mid - 1;
            } else{
                left = mid + 1;
            }
        }
        return result;
    }
    public static void main(String[] args){
        SmallestDivisor sd = new SmallestDivisor();
        int[] nums = {1,2,5,9};
        int threshold = 6;
        int result = sd.smallestDivisor(nums, threshold);
        System.out.println("Smallest Divisor: " + result); // Expected output: 5
    }
}
