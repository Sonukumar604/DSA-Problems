public class FindSQRT {
    public static int sqrt(int x){
        if (x == 0) return 0;
        int low = 1, high = x;
        int ans = -1;
        while(low <= high){
            int mid = low + (high - low) / 2;
            long midSq = (long) mid * mid; // Use long to prevent overflow
            if(midSq == x){
                return mid;
            }else if(midSq < x){
                ans = mid;
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int number = 16;
        int result = sqrt(number);
        System.out.println("The square root of " + number + " is: " + result);
    }
}
