public class SplitArray {
    private boolean canSplit(int[] nums, int k, int maxSum){
        int count = 1;
        int current = 0;
        for(int n : nums){
            if(n > maxSum) return false;
            if(current + n <= maxSum){
                current += n;
            }else{
                count++;
                current = n;
                if(count > k) return false;
            }
        }
        return true;
    }
    public int splitArray(int[] nums, int k){
        int low = 0;
        int high = 0;
        for(int n : nums){
            low = Math.max(low, n);
            high += n;
        }
        int ans = high;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canSplit(nums, k, mid)) {
                ans = mid;      // mid works, try smaller
                high = mid - 1;
            } else {
                low = mid + 1;  // need bigger segment sum
            }
        }

        return ans;
    }
    public static void main(String[] args) {
        SplitArray splitter = new SplitArray();
        int[] nums = {10, 20, 30, 40};
        int k = 2;
        int result = splitter.splitArray(nums, k);
        System.out.println("The minimum largest sum is: " + result);
    }
}
