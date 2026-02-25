public class LongestHappyPrefix {
    public static String longestPrefixSuffix(String s){
        int n = s.length();
        int[] lps = new int[n];
        int len = 0;
        int i = 1;
        while(i < n){
            if(s.charAt(i) == s.charAt(len)){
                len++;
                lps[i] = len;
                i++;
            } else {
                if(len != 0){
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }

        }
        int length = lps[n - 1];
        return s.substring(0, length);
        
    }
    public static void main(String[] args){
        String s = "ababab";
        System.out.println(longestPrefixSuffix(s));
        String s1 = "level";
        System.out.println(longestPrefixSuffix(s1));
    }
}
