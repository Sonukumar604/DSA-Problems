import java.util.HashMap;
import java.util.Map;

public class CountAnagramsUsingHashMap {
    public static int countAnagrams(String s, String p){
        if(s == null || s.length() == 0 || p == null || p.length() == 0 || s.length() < p.length()){
            throw new IllegalArgumentException("Invalid input: strings cannot be null, empty, or have s shorter than p.");
        }
        Map<Character, Integer> pMap = new HashMap<>();
        for(char c : p.toCharArray()){
            pMap.put(c , pMap.getOrDefault(c, 0) + 1);
        }
        Map<Character, Integer> windowMap = new HashMap<>();
        int k = p.length();
        int count = 0;
        for(int i = 0; i < k; i++){
            char c = s.charAt(i);
            windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);
        }
        if(windowMap.equals(pMap)){
            count++;
        }
        for(int i = k; i < s.length(); i++){
            char addChar = s.charAt(i);
            windowMap.put(addChar, windowMap.getOrDefault(addChar, 0) + 1);
            char removeChar = s.charAt(i - k);
            windowMap.put(removeChar, windowMap.get(removeChar) - 1);
            if(windowMap.get(removeChar) == 0){
                windowMap.remove(removeChar);
            }
            if(windowMap.equals(pMap)){
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        int result = countAnagrams(s, p);
        System.out.println("Number of anagrams: " + result);
    }
}
