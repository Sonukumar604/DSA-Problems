public class MedianSortedArray {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int x = nums1.length;
        int y = nums2.length;
        int low = 0;
        int high = x;
        while (low <= high) {
            int partitionX = (low + high) / 2;
            int partitionY = (x + y + 1) / 2 - partitionX;

            int maxX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minX = (partitionX == x) ? Integer.MAX_VALUE : nums1[partitionX];

            int maxY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minY = (partitionY == y) ? Integer.MAX_VALUE : nums2[partitionY];

            if (maxX <= minY && maxY <= minX) {
                if ((x + y) % 2 == 0) {
                    return ((double)Math.max(maxX, maxY) + Math.min(minX, minY)) / 2;
                } else {
                    return (double)Math.max(maxX, maxY);
                }
            } else if (maxX > minY) {
                high = partitionX - 1;
            } else {
                low = partitionX + 1;
            }
        }
        throw new IllegalArgumentException("Input arrays are not sorted.");
    }
    public static void main(String[] args) {
        MedianSortedArray medianFinder = new MedianSortedArray();
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        double median = medianFinder.findMedianSortedArrays(nums1, nums2);
        System.out.println("Median is: " + median); // Expected output: 2.0

        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};
        double median2 = medianFinder.findMedianSortedArrays(nums3, nums4);
        System.out.println("Median is: " + median2); // Expected output: 2.5
    }
}
