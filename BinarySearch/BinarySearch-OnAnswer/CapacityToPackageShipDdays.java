public class CapacityToPackageShipDdays {
    /**
     * Finds the minimum ship capacity required to ship all packages within 'days' days.
     *
     * Time Complexity: O(N * log(Sum - Max))
     *   - Binary search range is from max(weights) to sum(weights).
     * Space Complexity: O(1)
     */
    public int shipWithinDays(int[] weights, int days){
        int low = 0, high = 0;
        for(int w : weights){
            low = Math.max(low, w);
            high += w;
        }
        int ans = high;
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(canShip(weights, mid, days)){
                ans = mid;
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return ans;
    }
    private boolean canShip(int[] weights, int capacity, int days){
        int currentLoad = 0;
        int requiredDays = 1;
        for(int w : weights){
            if(currentLoad + w <= capacity){
                currentLoad += w;
            }else{
                requiredDays++;
                currentLoad = w;
                if(requiredDays > days){
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        CapacityToPackageShipDdays obj = new CapacityToPackageShipDdays();
        int[] weights = {1,2,3,4,5,6,7,8,9,10};
        int days = 5;
        int result = obj.shipWithinDays(weights, days);
        System.out.println("Minimum capacity to ship within " + days + " days: " + result);
    }
}
