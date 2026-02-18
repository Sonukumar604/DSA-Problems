public class maxProduct {
    public int maxProduct(int[] nums){
        int max = 1;
        int min = 1;
        int result = Integer.MIN_VALUE;

        for(int num : nums){
            if(num < 0){
                int temp = max;
                max = Math.max(num, min * num);
                min = Math.min(num, temp * num);
            } else {
                max = Math.max(num, max * num);
                min = Math.min(num, min * num);
            }
            result = Math.max(result, max);
        }
        return result;
    }
    public static void main(String[] args) {
        maxProduct mp = new maxProduct();
        int[] nums = {2, 3, -2, 4};
        System.out.println("Max Product: " + mp.maxProduct(nums));
    }
}
