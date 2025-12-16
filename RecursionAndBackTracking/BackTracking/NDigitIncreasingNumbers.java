public class NDigitIncreasingNumbers {
    public static void solve(int start, int n , StringBuilder output){
        if(n == 0){
            // Add a space for better readability of the output
            System.out.print(output.toString() + " ");
            return;
        }
        for(int i = start; i <= 9; i++){
            output.append(i);
            // The next digit must start from 'i + 1' to be strictly increasing.
            solve(i + 1, n - 1, output);
            output.deleteCharAt(output.length() - 1);
        }
    }
    public static void main(String[] args) {
        int n = 2;
        System.out.println("All " + n + "-digit numbers with digits in increasing order:");
        // Start the process with an empty StringBuilder
        solve(1, n, new StringBuilder());
    }
}
