public class nextGreatestChar {
    public static char nextGreatestChar(char[] letters, char target) {
        int left = 0;
        int right = letters.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (letters[mid] <= target) {
                left = mid + 1; // Search in the right half
            } else {
                right = mid - 1; // Search in the left half
            }
        }
        // If left is equal to the length of the array, it means we need to wrap around
        return letters[left % letters.length];
    }
    public static void main(String[] args) {
        char[] letters = {'c', 'f', 'j'};
        char target = 'a';
        System.out.println(nextGreatestChar(letters, target)); // Output: 'c'

        target = 'c';
        System.out.println(nextGreatestChar(letters, target)); // Output: 'f'

        target = 'd';
        System.out.println(nextGreatestChar(letters, target)); // Output: 'f'

        target = 'g';
        System.out.println(nextGreatestChar(letters, target)); // Output: 'j'

        target = 'j';
        System.out.println(nextGreatestChar(letters, target)); // Output: 'c'

        target = 'k';
        System.out.println(nextGreatestChar(letters, target)); // Output: 'c'
    }
}
