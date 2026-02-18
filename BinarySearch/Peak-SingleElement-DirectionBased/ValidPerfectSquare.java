public class ValidPerfectSquare {
    public boolean isPerfectSquare(int num) {
        if(num < 1) return false;
        int low = 1, high = num;
        while(low <= high){
            int mid = low + (high - low) / 2;
            long midSq = (long) mid * mid; // Use long to prevent overflow
            if(midSq == num){
                return true;
            }else if(midSq < num){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        ValidPerfectSquare vps = new ValidPerfectSquare();
        int number = 16;
        boolean result = vps.isPerfectSquare(number);
        System.out.println(number + " is a perfect square: " + result);
    }
}
