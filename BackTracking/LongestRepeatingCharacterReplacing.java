public class LongestRepeatingCharacterReplacing {
    public int characterReplacement(String s, int k){
        int[] count = new int[26];
        int maxCount = 0;
        int left = 0;
        int result = 0;

        for(int right = 0; right < s.length(); right++){
            count[s.charAt(right) - 'A']++;   // Increment the count of the current character
            maxCount = Math.max(maxCount, count[s.charAt(right) - 'A']);// Update the maxCount

            while((right - left + 1) - maxCount > k){ // Check if we need to shrink the window
                count[s.charAt(left) - 'A']--; // Decrement the count of the leftmost character
                left++;
            }
            result = Math.max(result, right - left + 1);// Update the result with the maximum length found
        }
        return result;
    }
    public static void main(String[] args){
        LongestRepeatingCharacterReplacing lrc = new LongestRepeatingCharacterReplacing();
        String s = "AAABABB";
        int k = 1;
        System.out.println(lrc.characterReplacement(s, k)); // Expected output: 4
    }
}
