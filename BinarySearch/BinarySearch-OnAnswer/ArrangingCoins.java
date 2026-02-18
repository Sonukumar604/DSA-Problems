public class ArrangingCoins {
    public int arrangeCoins(int n) {

        long low = 0;
        long high = n;
        long ans = 0;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            long coinsNeeded = mid * (mid + 1) / 2;

            if (coinsNeeded <= n) {
                ans = mid;        
                low = mid + 1;    
            } else {
                high = mid - 1;   
            }
        }

        return (int) ans;
    }
    public static void main(String[] args) {
        ArrangingCoins ac = new ArrangingCoins();
        int n = 8;
        System.out.println("Rows: " + ac.arrangeCoins(n));
    }
}
