public class PeakElement {
    public static int findPeakElement(int[] arr){
        int n = arr.length;
        // If array has only one element, it is a peak
        if (n == 1) {
            return 0;
        }
        // Check if the first element is a peak
        if (arr[0] > arr[1]) {
            return 0;
        }
        // Check if the last element is a peak
        if (arr[n - 1] > arr[n - 2]) {
            return n - 1;
        }

        // Binary search on the remaining elements
        int low = 1, high = n - 2;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            // If arr[mid] is a peak
            if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                return mid;
            }
            // If we are on an increasing slope, the peak is to the right
            else if (arr[mid] < arr[mid + 1]) {
                low = mid + 1;
            }
            // If we are on a decreasing slope, the peak is to the left
            else {
                high = mid - 1;
            }
        }
        return -1; // Should not be reached if a peak always exists
    }
    public static void main(String[] args){
        int[] arr = {1, 2, 1, 3, 5, 6, 4}; // This array has peaks at index 1 and 5
        int result = findPeakElement(arr);
        System.out.println("A peak element is at index: " + result); // Output will be one of the peaks, e.g., 5
    }
}
