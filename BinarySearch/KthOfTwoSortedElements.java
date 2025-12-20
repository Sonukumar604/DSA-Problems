public class KthOfTwoSortedElements {
    public static int findKthElement(int[] arr1, int[] arr2, int k) {
        int n1 = arr1.length;
        int n2 = arr2.length;

        // Ensure arr1 is the smaller array
        if (n1 > n2) {
            return findKthElement(arr2, arr1, k);
        }

        int low = Math.max(0, k - n2);
        int high = Math.min(k, n1);

        while (low <= high) {
            int cut1 = low + (high - low) / 2;
            int cut2 = k - cut1;

            int left1 = (cut1 == 0) ? Integer.MIN_VALUE : arr1[cut1 - 1];
            int left2 = (cut2 == 0) ? Integer.MIN_VALUE : arr2[cut2 - 1];
            int right1 = (cut1 == n1) ? Integer.MAX_VALUE : arr1[cut1];
            int right2 = (cut2 == n2) ? Integer.MAX_VALUE : arr2[cut2];

            if (left1 <= right2 && left2 <= right1) {
                return Math.max(left1, left2);
            } else if (left1 > right2) {
                high = cut1 - 1;
            } else {
                low = cut1 + 1;
            }
        }

        return -1; // If k is out of bounds
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 3, 6, 7, 9};
        int[] arr2 = {1, 4, 8, 10};
        int k = 5;
        int result = findKthElement(arr1, arr2, k);
        System.out.println("The " + k + "th smallest element is: " + result); // Output: The 5th smallest element is: 6
    }
}
