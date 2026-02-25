import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EncodedAndDecodedString {
    /**
     * Encodes a list of strings into a single string.
     * The format is [length]/[string][length]/[string]...
     * For example, ["neet", "code"] becomes "4/neet4/code".
     *
     * @param strs The list of strings to encode.
     * @return A single encoded string.
     */
    public String encode(List<String> strs){
        StringBuilder encodedString = new StringBuilder();
        for(String s : strs){
            encodedString.append(s.length()).append("/").append(s);
        }
        return encodedString.toString();
    }
    /**
     * Decodes a single string back into a list of strings.
     * @param s The encoded string.
     * @return The original list of strings.
     */
    public List<String> decode(String s){
        List<String> decodedStrings = new ArrayList<>();
        int i = 0;
        while(i < s.length()){
            int slashIndex = s.indexOf('/', i);
            int length = Integer.parseInt(s.substring(i, slashIndex));
            String str = s.substring(slashIndex + 1, slashIndex + 1 + length);
            decodedStrings.add(str);
            i = slashIndex + 1 + length;
        }
        return decodedStrings;
    }
    public static void main(String[] args) {
        List<String> original = Arrays.asList("neet","code","love","you");
        EncodedAndDecodedString codec = new EncodedAndDecodedString();
        String encoded = codec.encode(original);
        System.out.println("Encoded String: " + encoded);
        List<String> decoded = codec.decode(encoded);
        System.out.println("Decoded Strings: ");
        for (String s : decoded) {
            System.out.println(s);
        }
        

    }
}
