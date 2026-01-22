public class AggressiveCows {
    private boolean canPlaceCows(int[] stalls, int cows, int minDist){
        int count = 1;
        int lastPos = stalls[0];
        for(int i = 1; i < stalls.length; i++){
            if(stalls[i] - lastPos >= minDist){
                count++;
                lastPos = stalls[i];
            }
            if(count >= cows) return true;
        }
        return false;
    }
    /**
     * Finds the largest minimum distance possible between any two cows.
     *
     * Time Complexity: O(N log N + N * log(maxDist))
     *   - O(N log N) for sorting the stalls.
     *   - O(N * log(maxDist)) for the binary search on the answer, where maxDist is the range of positions.
     * Space Complexity: O(1) (ignoring sort stack space).
     */
    public int aggressiveCows(int[] stalls, int cows){
        java.util.Arrays.sort(stalls);
        int low = 1;
        int high = stalls[stalls.length - 1] - stalls[0];
        int ans = 0;
        while(low <= high){
            int mid = low + (high - low) /2 ;
            if(canPlaceCows(stalls, cows, mid)){
                ans = mid;
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        AggressiveCows obj = new AggressiveCows();
        int[] stalls = {0, 3, 4, 7, 10, 9};
        int cows = 4;
        int result = obj.aggressiveCows(stalls, cows);
        System.out.println("Largest minimum distance: " + result);

    }
}
