public class KokoEatingBanana {
    public int hoursNeeded(int[] piles, int k){
        int hours = 0;
        for(int bananas: piles){
            hours += (bananas + k - 1) / k;
        }
        return hours;
    }
    /**
     * Finds the minimum eating speed k such that Koko can eat all bananas within h hours.
     *
     * Time Complexity: O(N * log(MaxPile))
     *   - Binary search range is from 1 to the maximum pile size.
     * Space Complexity: O(1)
     */
    public int minEatingSpeed(int[] piles, int h){
        int low = 1, high = 0;
        for(int bananas: piles){
            high = Math.max(high, bananas);
        }
        int ans = high;
        while(low <= high){
            int mid = low + (high - low) / 2;
            int hours = hoursNeeded(piles, mid);
            if(hours <= h){
                ans = mid;
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] piles = {3, 6, 7, 11};
        int h = 8;
        KokoEatingBanana obj = new KokoEatingBanana();
        System.out.println(obj.minEatingSpeed(piles, h));
        
    }
}
