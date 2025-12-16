public class FirstAndLastOccurrence {
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
    public static int countOccurrence(int[] arr, int target) {
        int first = firstOccurrence(arr, target);
        if (first == -1) return 0;      // target not found
        int[] occurrences = firstAndLastOccurrence(arr, target);
        if (occurrences[0] == -1) {
            return 0; // target not found
        }

        int last = lastOccurrence(arr, target);
        return last - first + 1;
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
