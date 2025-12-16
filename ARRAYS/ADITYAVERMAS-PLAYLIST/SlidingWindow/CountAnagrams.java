import java.util.HashMap;
import java.util.Map;

public class CountAnagrams {
    // This is a more robust and efficient implementation using a HashMap and a counter.
    public static int countAnagrams(String s, String p){
        if(s == null || p == null || s.length() < p.length()){
            return 0;
        }

        Map<Character, Integer> pMap = new HashMap<>();
        for(char c : p.toCharArray()){
            pMap.put(c, pMap.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int countAnagrams = 0;
        int count = pMap.size(); // Number of unique characters to match

        while(right < s.length()){
            char rightChar = s.charAt(right);
            if(pMap.containsKey(rightChar)){
                pMap.put(rightChar, pMap.get(rightChar) - 1);
                if(pMap.get(rightChar) == 0) count--;
            }

            // Slide the window
            if(right - left + 1 == p.length()){
                if(count == 0) countAnagrams++;

                char leftChar = s.charAt(left);
                if(pMap.containsKey(leftChar)){
                    if(pMap.get(leftChar) == 0) count++;
                    pMap.put(leftChar, pMap.get(leftChar) + 1);
                }
                left++;
            }
            right++;
        }
        return countAnagrams;
    }

    public static void main(String[] args) {
        String s = "harharmahadevdevokdevmahadev";
        String p = "dev";
        int result = countAnagrams(s, p);
        System.out.println("The number of anagrams is: " + result);
    }
}
