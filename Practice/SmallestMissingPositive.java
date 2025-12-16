import java.util.HashSet;

public class SmallestMissingPositive {
    int missingNumber(int[] arr){
        HashSet<Integer> set = new HashSet<>();

        // Add all positive numbers from the array to the set.
        for (int num : arr) {
            if (num > 0) {
                set.add(num);
            }
        }

        // Find the first positive integer that is not in the set.
        for (int i = 1; i <= arr.length + 1; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return 1; // Should not be reached with the given logic.
    }
    public static void main(String[] args) {
        int[] arr= {2, -3, 4, 1, 1, 7};
        SmallestMissingPositive smp = new SmallestMissingPositive();
        int result = smp.missingNumber(arr);
        System.out.println("The Smallest Positive missing number is: " + result);
    }
}
