import java.util.HashMap;
import java.util.Map;
public class LongestSubarrayWithEqual0sAnd1s{
    public static int findMaxLength(int[] nums){
        if(nums == null || nums.length < 2){
            return 0;
        }
        Map<Integer, Integer> sumToIndex = new HashMap<>();
        sumToIndex.put(0, -1);
        int runningSum = 0;
        int maxLen = 0;
        for(int i = 0; i < nums.length; i++){
            runningSum += (nums[i] == 0) ? -1 : 1;
            if(sumToIndex.containsKey(runningSum)){
                maxLen = Math.max(maxLen, i - sumToIndex.get(runningSum));
            }else{
                sumToIndex.put(runningSum, i);
            }
        }
        return maxLen;
    }
    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 0,1, 1, 0};
        System.out.println("Length of the longest subarray with equals 0s and 1s: " +findMaxLength(nums));
    }
}