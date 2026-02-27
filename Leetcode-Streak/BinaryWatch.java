import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer `turnedOn` which represents the number of LEDs that are currently on,
 * this class returns all possible times the watch could represent.
 *
 * ### Explanation
 * The approach is a straightforward brute-force simulation. A binary watch has 10 LEDs in total:
 * 4 for the hour (0-11) and 6 for the minute (0-59). The number of turned-on LEDs (`turnedOn`)
 * is the sum of the number of set bits in the binary representation of the hour and the minute.
 *
 * The algorithm iterates through every possible time:
 * 1. Loop through every hour `h` from 0 to 11.
 * 2. Inside that loop, loop through every minute `m` from 0 to 59.
 * 3. For each combination of `h` and `m`, calculate the total number of set bits using `Integer.bitCount(h) + Integer.bitCount(m)`.
 * 4. If this sum equals the `turnedOn` value, it's a valid time.
 * 5. Format the valid time as a string (e.g., "h:mm") and add it to the result list.
 *
 * This method exhaustively checks all 12 * 60 = 720 possible times and filters them based on the bit count condition.
 *
 * ### Dry Run (turnedOn = 1)
 * The goal is to find all times where `Integer.bitCount(h) + Integer.bitCount(m) == 1`.
 * This means either the hour has 1 bit and the minute has 0, or vice-versa.
 *
 * - **Case 1: `bitCount(h) == 1` and `bitCount(m) == 0`**
 *   - `bitCount(m) == 0` is only true for `m = 0`.
 *   - `bitCount(h) == 1` is true for hours that are powers of 2: `h = 1, 2, 4, 8`.
 *   - This gives the times: "1:00", "2:00", "4:00", "8:00".
 *
 * - **Case 2: `bitCount(h) == 0` and `bitCount(m) == 1`**
 *   - `bitCount(h) == 0` is only true for `h = 0`.
 *   - `bitCount(m) == 1` is true for minutes that are powers of 2: `m = 1, 2, 4, 8, 16, 32`.
 *   - This gives the times: "0:01", "0:02", "0:04", "0:08", "0:16", "0:32".
 *
 * - **Result:** The final list will contain ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"].
 *
 * ### Complexity
 * - **Time Complexity:** O(1). The loops run a fixed number of times (12 hours * 60 minutes = 720 iterations). The operations inside the loop are constant time.
 * - **Space Complexity:** O(1). The space required for the result list is bounded by the total number of possible times, which is a constant.
 */
public class BinaryWatch {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> result = new ArrayList<>();
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if (Integer.bitCount(h) + Integer.bitCount(m) == turnedOn) {
                    result.add(String.format("%d:%02d", h, m));
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        BinaryWatch solution = new BinaryWatch();
        int turnedOn1 = 1;
        System.out.println("Possible times for " + turnedOn1 + " LED(s) on: " + solution.readBinaryWatch(turnedOn1));

        int turnedOn2 = 9;
        System.out.println("Possible times for " + turnedOn2 + " LED(s) on: " + solution.readBinaryWatch(turnedOn2));
    }
}
