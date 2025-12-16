public class LargestNumberKSwaps{
    public static String findMaximumNum(String s, int k){
        StringBuilder result = new StringBuilder();
        setDigit(new StringBuilder(s), 0, result, k);
        return result.toString();
    }
    public static void match(String current, StringBuilder result){
        if(current.compareTo(result.toString()) > 0){
            result.replace(0, result.length(), current);
        }
    }
    public static void setDigit(StringBuilder s, int index, StringBuilder result, int k){
        if(k == 0 || index == s.length() - 1){
            match(s.toString(), result);
            return;
        }
        int maxDigit = 0;
        for(int i = index; i < s.length(); i++){
            maxDigit = Math.max(maxDigit, s.charAt(i) - '0');
        }
        if(s.charAt(index) - '0' == maxDigit){
            setDigit(s, index + 1, result, k);
            return;
        }
        for(int i = index + 1; i < s.length(); i++){
            if(s.charAt(i) - '0' == maxDigit){
                char temp = s.charAt(index);
                s.setCharAt(index, s.charAt(i));
                s.setCharAt(i, temp);
                setDigit(s, index + 1, result, k - 1);
                s.setCharAt(i, s.charAt(index));
                s.setCharAt(index, temp);
            }
        }
    }
    public static void main(String[] args){
        String s = "7599";
        int k = 5;
        System.out.println(findMaximumNum(s, k));

    }
}