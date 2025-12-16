public class MaxConsecutiveOnce {
    //Time Complextiy : O(n), Space Complexity: O(1)
    public static int findMaxConsecutivesOnes(int[] nums){
        int maxCount = 0;
        int currentCount = 0;
        for(int num : nums){
            if(num == 1){
                currentCount++;
            }else{
                currentCount = 0;
            }
            maxCount = Math.max(maxCount, currentCount);
        }
        return maxCount;
    }
    public static void main(String[] args) {
        int[] nums = {1, 1, 0, 1, 1, 1, 1};
        int result = findMaxConsecutivesOnes(nums);
        System.out.println("Maximum Consecutives ones: " + result);
    }
    
}
