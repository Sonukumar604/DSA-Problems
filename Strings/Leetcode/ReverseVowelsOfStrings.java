
public class ReverseVowelsOfStrings {
    /**
     * Reverses the vowels in a given string using the Two Pointers technique.
     * 
     * Approach:
     * 1. Convert the string to a character array (since Strings are immutable).
     * 2. Use two pointers (left and right) to traverse from both ends.
     * 3. Swap characters when both pointers identify a vowel.
     * 
     * Time Complexity: O(N) - We traverse the string once.
     * Space Complexity: O(N) - For the character array.
     */
    public String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        int left = 0;
        int right = arr.length - 1;
        while(left < right){
            while(left < right && !isVowel(arr[left])){
                left++;
            }
            while(left < right && !isVowel(arr[right])){
                right--;
            }
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
        return new String(arr);
    }
    private boolean isVowel(char c){
        return "aeiouAEIOU".indexOf(c) != -1;
    }

    public static void main(String[] args) {
        ReverseVowelsOfStrings solution = new ReverseVowelsOfStrings();

        System.out.println("Reversed vowels of 'hello': " + solution.reverseVowels("hello")); // Output: holle
        System.out.println("Reversed vowels of 'leetcode': " + solution.reverseVowels("leetcode")); // Output: leotcede
        System.out.println("Reversed vowels of 'aA': " + solution.reverseVowels("aA")); // Output: Aa
    }
}
