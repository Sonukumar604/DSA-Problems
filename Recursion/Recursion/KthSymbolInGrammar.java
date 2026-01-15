public class KthSymbolInGrammar {
    public static int KthGrammar(int n, int k){
        //Base case
        if(n == 1 && k == 1) return 0;
        //Hypothesis
        // The length of the string at row n is 2^(n-1).
        // The mid-point index is half of that length.
        int mid = (int)Math.pow(2, n - 1) / 2;
        //Induction
        if(k <= mid){
            // If k is in the first half, it's the same as the grammar in the previous row.
            return KthGrammar(n-1, k);
        }
        else{
            // If k is in the second half, it's the complement of the value from the previous row.
            // The complement of 0 is 1, and 1 is 0. This can be calculated as `1 - value`.
            return 1 - KthGrammar(n-1, k - mid);
        }
    }
    public static void main(String[] args){
        int n = 4, k = 6;
        int result = KthGrammar(n, k);
        System.out.println("The kth symbol in the grammar is: " +result );
    }
}
