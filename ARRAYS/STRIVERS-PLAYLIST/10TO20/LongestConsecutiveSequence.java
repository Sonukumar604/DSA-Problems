import java.util.Arrays;
import java.util.HashSet;

public class LongestConsecutiveSequence {
    //Brute-Force Approach
    //Time Complexity : O(N^3)
    // Helper function for linear search
    public static boolean ls(int[] arr, int num){
        for(int val : arr){
            if(val == num){
                return true;
            }
        }
        return false;
    }

    public static int longestConsecutiveSequence(int[] arr){
        int n = arr.length;
        if(n == 0){
            return 0;
        }
        int longest = 1;
        for(int i = 0; i < n; i++){
            int x = arr[i];
            int count = 1;
            while(ls(arr, x + 1 ) == true){
                x = x + 1;
                count = count + 1;
            }
            longest = Math.max(longest, count);
        }
        return longest;
    }

    // Better Approach using Sorting
    // Time Complexity: O(N log N) + O(N), Space Complexity: O(1)
    public static int longestConsecutivesBetter(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        Arrays.sort(arr);
        int n = arr.length;
        int longest = 1;
        int currentCount = 1;
        for(int i = 1; i < n; i++){
            if(arr[i] == arr[i-1] + 1){
                // It's a consecutive number
                currentCount++;
            } else if (arr[i] != arr[i-1]) {
                // It's not a consecutive number, and not a duplicate, so reset the count.
                currentCount = 1;
            }
            // If arr[i] == arr[i-1] (a duplicate), we do nothing.
            longest = Math.max(longest, currentCount);
        }
        return longest;
    }
    public static int longestConsecutivesOptimal(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        HashSet<Integer> numSet = new HashSet<>();
        for(int num : arr){
            numSet.add(num);
        }
        int longest = 0;
        for(int num : arr){
            if(!numSet.contains(num - 1)){
                int currentNum = num;
                int currentStreak = 1;

                while(numSet.contains(currentNum + 1)){
                    currentNum++;
                    currentStreak++;
                }
                longest = Math.max(longest, currentStreak);
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        int[] arr = {100, 4, 200, 1, 3, 2};
        System.out.println("Original Array: " + Arrays.toString(arr));
        int len1 = longestConsecutiveSequence(arr);
        System.out.println("Brute-Force: The longest consecutive sequence is " + len1);
        int len2 = longestConsecutivesBetter(arr);
        System.out.println("Better (Sorting): The longest consecutive sequence is " + len2);
        int len3 = longestConsecutivesOptimal(arr);
        System.out.println("Optimal (HashSet): The longest consecutive sequence is " + len3);
    }
}
