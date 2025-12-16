public class StringMatching {
    public static int searchPattern(String text, String pattern){
        int n = text.length();
        int m = pattern.length();
        for(int i = 0; i < n; i++){
            if(text.charAt(i) == pattern.charAt(0)){
                int j = i, k = 0;
                while(j < n && k < m && text.charAt(j) == pattern.charAt(k)){
                    j++;
                    k++;
                }
                if(k == m){
                    return i;
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        String text = "ababcab";
        String pattern = "abc";
        int index = searchPattern(text, pattern);
        if (index != -1) {
            System.out.println("Pattern found at index: " + index);
        } else {
            System.out.println("Pattern not found");
        }
    }
}
