public class TransformedArray {
    public int[] constructTransformedArray(int[] nums){
        int n = nums.length;
        int[] transformedArray = new int[n];
        for(int i = 0; i < n; i++){
            int targetIndex = ((i + nums[i]) % n + n) % n;
            transformedArray[i] = nums[targetIndex];
        }
        return transformedArray;
    }

    public static void main(String[] args) {
        TransformedArray ta = new TransformedArray();
        int[] nums = {1, 2, 3, 4, 5};
        int[] transformed = ta.constructTransformedArray(nums);
        for(int num : transformed){
            System.out.print(num + " ");
        }
    }
}