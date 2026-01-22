public class FirstAndLastOccurrence {
    /**
     * Finds the first and last occurrence of a target in a sorted array.
     * Uses two separate binary searches.
     *
     * Time Complexity: O(log N) - Two binary searches are performed.
     * Space Complexity: O(1)
     */
    public static int[] firstAndLastOccurrence(int[] arr, int target){
        int[] result = {-1, -1};
        // Find first occurrence
        int low = 0;
        int high = arr.length - 1;
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(arr[mid] == target){
                result[0] = mid;
                high = mid - 1;
            }
            else if(arr[mid] < target){
                low = mid + 1;
            }else{
                high = mid - 1;
            }

        }
        // Find last occurrence
        low = 0;
        high = arr.length - 1;
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(arr[mid] == target){
                result[1] = mid;
                low = mid + 1;
            }
            else if(arr[mid] < target){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return result;
    }
    /**
     * Counts the number of occurrences of a target in a sorted array.
     *
     * Time Complexity: O(log N)
     * Space Complexity: O(1)
     */
    public static int countOccurrence(int[] arr, int target) {
        int[] occurrences = firstAndLastOccurrence(arr, target);
        if (occurrences[0] == -1) {
            return 0; // target not found
        }
        return occurrences[1] - occurrences[0] + 1;
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 2, 3, 4, 5};
        int target = 2;
        int count = countOccurrence(arr, target);
        System.out.println("Occurrence Count: " + count);

        int[] occurrences = firstAndLastOccurrence(arr, target);
        System.out.println("First Occurrence: " + occurrences[0]);
        System.out.println("Last Occurrence: " + occurrences[1]);
    }
}
