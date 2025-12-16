public class PeakElement {
    /**
     * Finds a peak element in an array using a brute-force approach.
     * A peak element is an element that is not smaller than its neighbors.
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     *
     * @param arr The input array.
     * @return The index of a peak element.
     */
    public int peakElement(int[] arr){
        int n = arr.length;
        // A single element is always a peak.
        if(n == 1) return 0;
        // Check if the first element is a peak.
        if(arr[0] >= arr[1]) return 0;
        // Check if the last element is a peak.
        if(arr[n - 1] >= arr[n - 2]) return n - 1;

        // Check the middle elements.
        for(int i = 0; i < n; i++){
            if(arr[i] >= arr[i - 1] && arr[i] >= arr[i + 1]){
                return i;
            }
        }
        return -1; // Should not be reached if a peak always exists as per many problem definitions.
    }
    public static void main(String[] args) {
        PeakElement pe = new PeakElement();
        int[] arr = {1, 3, 20, 4, 1};
        int peakIndex = pe.peakElement(arr);
        if(peakIndex != -1) {
            System.out.println("Peak element found at index: " + peakIndex);
        } else {
            System.out.println("No peak element found.");
        }
    }
}