import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;


public class KthSmallestElement {
    /**
     * Finds the k-th smallest element in an array.
     * Time Complexity: O(N log N) due to sorting.
     * Space Complexity: O(1).
     *
     * @param arr The input array.
     * @param k The position of the smallest element to find (1-based).
     * @return The k-th smallest element, or Integer.MIN_VALUE if k is invalid.
     */
    // Brute Force Approach: Sort the array and return the (k-1)th element
    // Time Complexity: O(N log N)
    // Space Complexity: O(1)
    public int kthSmallestBrute(int[] arr, int k) {
        // Validate k to be within the bounds of the array.
        if (k < 1 || k > arr.length) {
            return Integer.MIN_VALUE; // Or throw an exception
        }
        Arrays.sort(arr);
        // The k-th smallest element is at index k-1 in a sorted array.
        return arr[k - 1];
    }

    // Better Approach: Use a Max Heap (Priority Queue)
    // Time Complexity: O(N log K)
    // Space Complexity: O(K)
    public int kthSmallestBetter(int[] arr, int k) {
        // Validate k to be within the bounds of the array.
        if (k < 1 || k > arr.length) {
            return Integer.MIN_VALUE; // Or throw an exception
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a); // Max heap

        for (int i = 0; i < arr.length; i++) {
            maxHeap.add(arr[i]);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // Remove the largest element
            }
        }

        return maxHeap.peek(); // The root of the max heap is the kth smallest element
    }

    // Optimal Approach: Quick Select Algorithm
    // Time Complexity: O(N) on average, O(N^2) in worst case
    // Space Complexity: O(1)
    public int kthSmallestOptimal(int[] arr, int k) {
        // Validate k to be within the bounds of the array.
        if (k < 1 || k > arr.length) {
            return Integer.MIN_VALUE; // Or throw an exception
        }

        int left = 0, right = arr.length - 1;
        Random random = new Random();

        while (left <= right) {
            int pivotIndex = left + random.nextInt(right - left + 1); // Random pivot
            int partitionIndex = partition(arr, left, right, pivotIndex);

            if (partitionIndex == k - 1) {
                return arr[partitionIndex];
            } else if (partitionIndex < k - 1) {
                left = partitionIndex + 1;
            } else {
                right = partitionIndex - 1;
            }
        }

        return Integer.MIN_VALUE; // Should not reach here if k is valid
    }

    private int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, i, storeIndex++);
            }
        }
        swap(arr, storeIndex, right);
        return arr[k - 1];
    }
    public static void main(String[] args) {
        KthSmallestElement obj = new KthSmallestElement();
        int[] arr = {7, 10, 4, 3, 20, 15};
        int k = 3;
        // Expected output: 7. The 3rd smallest element is 7.
        System.out.println("The " + k + "rd smallest element is: " + obj.kthSmallest(arr, k));
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
