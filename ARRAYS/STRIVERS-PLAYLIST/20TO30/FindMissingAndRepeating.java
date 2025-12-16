import java.util.ArrayList;
import java.util.Arrays;

public class FindMissingAndRepeating {

    // Brute-Force Approach
    // Time Complexity: O(N^2), Space Complexity: O(1)
    public static int[] findMissingAndRepeatingBrute(int[] arr) {
        int n = arr.length;
        int repeating = -1, missing = -1;

        // Iterate from 1 to N to check each number
        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (arr[j] == i) {
                    count++;
                }
            }
            if (count == 2) {
                repeating = i;
            } else if (count == 0) {
                missing = i;
            }

            if (repeating != -1 && missing != -1) {
                break;
            }
        }
        return new int[]{repeating, missing};
    }

    // Better Approach using a Frequency Array (Hashing)
    // Time Complexity: O(N), Space Complexity: O(N)
    public static int[] findMissingAndRepeatingBetter(int[] arr) {
        int n = arr.length;
        int[] hash = new int[n + 1]; // hash array from 0 to n

        for (int i = 0; i < n; i++) {
            hash[arr[i]]++;
        }

        int repeating = -1, missing = -1;
        for (int i = 1; i <= n; i++) {
            if (hash[i] == 2) {
                repeating = i;
            } else if (hash[i] == 0) {
                missing = i;
            }
        }
        return new int[]{repeating, missing};
    }

    // Optimal Approach using Mathematics
    // Time Complexity: O(N), Space Complexity: O(1)
    public static int[] findMissingAndRepeatingOptimalMath(int[] arr) {
        long n = arr.length;
        // Sum of first N natural numbers
        long SN = (n * (n + 1)) / 2;
        // Sum of squares of first N natural numbers
        long S2N = (n * (n + 1) * (2 * n + 1)) / 6;

        long S = 0, S2 = 0;
        for (int num : arr) {
            S += num;
            S2 += (long)num * num;
        }

        // S - SN = repeating - missing
        long val1 = S - SN;
        // S2 - S2N = repeating^2 - missing^2 = (repeating - missing)(repeating + missing)
        long val2 = S2 - S2N;

        // (repeating + missing) = val2 / val1
        long val3 = val2 / val1;

        // We have: repeating + missing = val3 and repeating - missing = val1
        long repeating = (val1 + val3) / 2;
        long missing = val3 - repeating;

        return new int[]{(int)repeating, (int)missing};
    }
    ArrayList<Integer> findTwoElement(int arr[], int n) {
        ArrayList<Integer> result = new ArrayList<>();
        int[] freq = new int[n + 1];

        for (int num : arr) {
            freq[num]++;
        }

        for (int i = 1; i <= n; i++) {
            if (freq[i] == 2) {
                result.add(i); // Repeating number
            } else if (freq[i] == 0) {
                result.add(i); // Missing number
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 6, 2, 1, 1};
        System.out.println("Original Array: " + Arrays.toString(arr));

        int[] bruteResult = findMissingAndRepeatingBrute(arr);
        System.out.println("Brute-Force: Repeating is " + bruteResult[0] + ", Missing is " + bruteResult[1]);

        int[] betterResult = findMissingAndRepeatingBetter(arr);
        System.out.println("Better (Hashing): Repeating is " + betterResult[0] + ", Missing is " + betterResult[1]);

        int[] optimalResult = findMissingAndRepeatingOptimalMath(arr);
        System.out.println("Optimal (Math): Repeating is " + optimalResult[0] + ", Missing is " + optimalResult[1]);

        ArrayList<Integer> result = new FindMissingAndRepeating().findTwoElement(arr, arr.length);
        System.out.println("Using Frequency Array: Repeating is " + result.get(0) + ", Missing is " + result.get(1));
        System.out.println();
    }
}
