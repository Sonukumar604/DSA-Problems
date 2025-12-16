import java.util.HashMap;
import java.util.Map;
public class LongestkUniqueSubstring {
    public static int longestkUniqueSubstring(String s, int k){
        int left = 0;
        int maxLen = 0;
        Map<Character, Integer> windowCharCount = new HashMap<>();
        for(int right = 0; right < s.length(); right++){
            char c = s.charAt(right);
            windowCharCount.put(c, windowCharCount.getOrDefault(c, 0) + 1);
            if(windowCharCount.size() > k){
                char leftChar = s.charAt(left);
                windowCharCount.put(leftChar, windowCharCount.get(leftChar) - 1);
                if(windowCharCount.get(leftChar) == 0){
                    windowCharCount.remove(leftChar);
                }
                left++;
            }
            if(windowCharCount.size() == k){
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        String s = "aabacbebebe";
        int k = 3;
        int result = longestkUniqueSubstring(s, k);
        
        if (result != -1) {
            System.out.println("For s=\"" + s + "\", k=" + k + ": Length of the longest substring is: " + result);
        } else {
            System.out.println("For s=\"" + s + "\", k=" + k + ": No substring found with " + k + " unique characters.");
        }
    }
}
