public class SmallestLetterGreaterThanTarget {
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0;
        int right = letters.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (letters[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // wrap around
        return letters[left % letters.length];
    }

   
    public static void main(String[] args) {
        SmallestLetterGreaterThanTarget solution = new SmallestLetterGreaterThanTarget();
        char[] letters = {'c', 'f', 'j'};
        char target = 'a';
        char result = solution.nextGreatestLetter(letters, target);
        System.out.println("Smallest letter greater than " + target + " is: " + result);
    }
}
