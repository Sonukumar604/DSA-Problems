public class TrappingRainWater{
    public static int TrappingRainWaterBruteForce(int[] height){
        int n = height.length;
        int trappedWater = 0;
        for(int i= 0; i < n; i++){
            int leftMax = 0;
            for(int j = 0; j <= i; j++){
                leftMax = Math.max(leftMax, height[j]);
            }
            int rightMax =0;
            for(int j = i; j <n; j++){
                rightMax = Math.max(rightMax, height[j]);
            }
            trappedWater += Math.min(leftMax, rightMax) - height[i];
        }
        return trappedWater;
    }
    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Trapped water: " +TrappingRainWaterBruteForce(height));
    }
}