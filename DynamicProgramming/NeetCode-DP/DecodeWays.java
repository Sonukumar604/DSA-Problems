import java.util.Arrays;

public class DecodeWays {
    public int numDecodings(String s){
        if(s == null || s.length() == 0){
            return 0;
        }
        int n = s.length();
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return decodeWays(s, 0, memo);
    }
    private int decodeWays(String s, int index, int[] memo){
        if(index == s.length()){
            return 1;
        }
        if(s.charAt(index) == '0'){
            return 0;
        }
        if(memo[index] != -1){
            return memo[index];
        }
        
        int count = decodeWays(s, index + 1, memo);
        
        if(index + 1 < s.length()){
            int twoDigit = Integer.parseInt(s.substring(index, index + 2));
            if(twoDigit >= 10 && twoDigit <= 26){
                count += decodeWays(s, index + 2, memo);
            }
        }
        return memo[index] = count;
    }
    public static void main(String[] args) {
        DecodeWays dw = new DecodeWays();
        System.out.println("Ways to decode '226': " + dw.numDecodings("226"));
    }
}
