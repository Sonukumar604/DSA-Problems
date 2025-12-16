/**
 * This class finds the minimum number of cuts needed to partition a string
 * such that every substring of the partition is a palindrome.
 * This is a recursive solution that demonstrates the underlying logic,
 * but it has overlapping subproblems and is inefficient for larger strings.
 */
public class PalindromePartitioningRecursiveCall {
    public static boolean isPalindrome(String s, int i, int j){
        while(i <  j){
            if(s.charAt(i) != s.charAt(j))return false;
            i++;
            j--;
        }
        return true;
    }
    public static int minPartitions(String s, int i, int j){
        if(i >= j  || isPalindrome(s, i, j)){
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for(int k = i; k < j; k++){
            int left = minPartitions(s, i, k);
            int right = minPartitions(s, k + 1, j);
            int temp = 1 + left + right;
            min = Math.min(min, temp);
        }
        return min;
    }
    public static void main(String[] args) {
        String s = "ababbbabbababa";
        int n = s.length();
        int result = minPartitions(s, 0, n-1);
        System.out.println("Minimum cuts needed: " + result);
    }
}
