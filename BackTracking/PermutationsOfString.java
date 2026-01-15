import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermutationsOfString {
    public static List<String> findPermutations(String s){
        List<String> result = new ArrayList<>();
        if(s == null || s.isEmpty()){
            return result;
        }
        permute(s.toCharArray(), 0, result);
        return result;
    }
    public static void permute(char[] arr, int index, List<String> result){
        if(index == arr.length - 1){
            result.add(new String(arr));
        }
        Set<Character> used = new HashSet<>();
        for(int i = index; i < arr.length; i++){
            if(used.contains(arr[i])){
                continue;
            }
            used.add(arr[i]);
            swap(arr, index, i);
            permute(arr, index + 1, result);
            swap(arr, index, i);
        }
    }
    public static void swap(char[] arr, int i, int j){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void main(String[] args) {
        String s = "AAB";
        System.out.println("Permutations of " + s + " : " + findPermutations(s));
    }
}