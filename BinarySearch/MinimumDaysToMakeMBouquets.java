public class MinimumDaysToMakeMBouquets {
    //Helper function
    public static boolean canMakeBouquets(int[] bloomDay, int days, int m , int k){
        int bouquets = 0;
        int flowers = 0;
        for(int day : bloomDay){
            if(day <= days){
                flowers++;
            }
            else{
                flowers = 0;
            }
            if(flowers == k){
                bouquets++;
                flowers = 0;
            }
            if(bouquets >= m){
                return true;
            }
        }
        return false;
    }
    public static int minDays(int[] bloomDay, int m, int k){
        if(bloomDay.length < m * k){
            return -1;
        }
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        for(int day : bloomDay){
            low = Math.min(low, day);
            high = Math.max(high, day);
        }
        int ans = -1;
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(canMakeBouquets(bloomDay, mid, m, k)){
                ans = mid;
                high = mid -1;

            }else{
                low = mid + 1;
            }

        }
        return ans;
    }

    public static void main(String[] args) {
        int[] bloomDay = {1, 10, 3, 10, 2};
        int m = 3;
        int k = 1;
        System.out.println("Minimum days to make " + m + " bouquets: " + minDays(bloomDay, m, k));
    }
}
