import java.util.Arrays;

public class RightInterval {
    //brute force approach
    public int[] findRightInterval(int[][] intervals){
        int n = intervals.length;
        int[] result = new int[n];

        for(int i = 0; i < n; i++){
            int minStart = Integer.MAX_VALUE;
            int rightIndex = -1;

            for(int j = 0; j < n; j++){
                // Check if interval 'j' starts after or at the same time interval 'i' ends
                if(intervals[j][0] >= intervals[i][1]){
                    // If it's a valid "right" interval, check if it's the one with the smallest start time found so far
                    if(intervals[j][0] < minStart){
                        minStart = intervals[j][0];
                        rightIndex = j;
                    }
                }
            }
            // After checking all other intervals, the best rightIndex is found.
            // If no right interval was found, rightIndex will remain -1.
            result[i] = rightIndex;
        }
        return result;
    }
    //Binary Search approach can be implemented for better performance.
    public int[] findRightIntervalsBinarySearch(int[][] intervals){
        int n = intervals.length;
        int[] result = new int[n];//to store the result
        int[][] startWithIndex = new int[n][2];//to store the start time with original index
        for(int i = 0; i < n; i++){
            startWithIndex[i][0] = intervals[i][0];
            startWithIndex[i][1] = i;
        }
        //Sort based on start times
        Arrays.sort(startWithIndex, (a, b) -> Integer.compare(a[0], b[0]));
        for(int i = 0; i < n; i++){
            int target = intervals[i][1];
            int low = 0;
            int high = n - 1;
            int rightIndex = -1;

            //Binary search to find the smallest start time >= target
            while(low <= high){
                int mid = low + (high - low) / 2;
                if(startWithIndex[mid][0] >= target){
                    rightIndex = startWithIndex[mid][1];
                    high = mid - 1;
                }
                else{
                    low = mid + 1;
                }
            }
            result[i] = rightIndex;
        }
        return result;
    }
    public static void main(String[] args){
        RightInterval ri = new RightInterval();
        int[][] intervals = {{1,2},{2,3},{3,4}};
        int[] result = ri.findRightInterval(intervals);
        System.out.print("Right intervals indices: ");
        for(int index : result) {
            System.out.print(index + " ");
        }
        // Using Binary Search approach
        int[] resultBS = ri.findRightIntervalsBinarySearch(intervals);
        System.out.print("\nRight intervals indices (Binary Search): ");
        for(int index : resultBS) {
            System.out.print(index + " ");
        }
        // Expected Output: [1, 2, -1]
    }
}