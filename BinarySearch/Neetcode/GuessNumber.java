package Neetcode;

public class GuessNumber {
    // The number to guess (simulating the hidden number)
    static int pick = 6;

    // Mock implementation of the guess API
    // returns -1 if num is higher than the picked number
    // returns 1 if num is lower than the picked number
    // returns 0 if num is equal to the picked number
    private static int guess(int num) {
        if (num > pick) return -1;
        if (num < pick) return 1;
        return 0;
    }

    /**
     * Guesses the number using Binary Search.
     *
     * Time Complexity: O(log N)
     * Space Complexity: O(1)
     */
    public static int guessNumber(int n){
        int low = 1;
        int high = n;
        while(low <= high){
            int mid = low + (high - low) / 2;
            int res = guess(mid);
            if(res == 0){
                return mid;
            }else if(res < 0){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return -1; // This line should never be reached if input is valid
    }
    public static void main(String[] args) {
        int n = 10; // Example input
        int result = guessNumber(n);
        System.out.println("Guessed number: " + result);
    }
}
