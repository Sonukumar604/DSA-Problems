
public class StringCompression {
    /**
     * Compresses a character array in-place.
     * 
     * Approach: Two Pointers (Read and Write)
     * 1. Use a 'read' pointer (i) to iterate through the array and identify groups of consecutive characters.
     * 2. Use a 'write' pointer to overwrite the array with the compressed version.
     * 3. For each group, write the character. If the group length > 1, write the count as individual characters.
     * 
     * Time Complexity: O(N) - We traverse the array once.
     * Space Complexity: O(1) - We modify the array in-place using constant extra space.
     */
    public int compress(char[] chars) {
        int write = 0;
        int i = 0;
        while(i < chars.length){
            char current = chars[i];
            int count = 0;
            while(i < chars.length && chars[i] == current){
                i++;
                count++;
            }
            chars[write] = current;
            write++;
            if(count > 1){
                String num = String.valueOf(count);
                for(char c : num.toCharArray()){
                    chars[write] = c;
                    write++;
                }
            }
        }
        return write;
    }

    public static void main(String[] args) {
        StringCompression solution = new StringCompression();

        // Test Case 1
        char[] chars1 = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        int len1 = solution.compress(chars1);
        System.out.println("Compressed length: " + len1);
        System.out.print("Compressed array: ");
        for (int i = 0; i < len1; i++) System.out.print(chars1[i]);
        System.out.println();

        // Test Case 2
        char[] chars2 = {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
        int len2 = solution.compress(chars2);
        System.out.println("Compressed length: " + len2);
        System.out.print("Compressed array: ");
        for (int i = 0; i < len2; i++) System.out.print(chars2[i]);
        System.out.println();
    }
}
