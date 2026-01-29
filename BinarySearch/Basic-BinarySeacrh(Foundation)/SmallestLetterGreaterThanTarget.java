public class SmallestLetterGreaterThanTarget {
    public char nextGreatestLetter(char[] letters, char target) {
        int low = 0;
        int high = letters.length - 1;
        char result = letters[0]; // Default to the first letter in case all are <= target

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (letters[mid] > target) {
                result = letters[mid]; // Potential answer found
                high = mid - 1; // Look for a smaller letter on the left side
            } else {
                low = mid + 1; // Move to the right side
            }
        }

        return result;
    }
    public static void main(String[] args) {
        SmallestLetterGreaterThanTarget solution = new SmallestLetterGreaterThanTarget();
        char[] letters = {'c', 'f', 'j'};
        char target = 'a';
        char result = solution.nextGreatestLetter(letters, target);
        System.out.println("Smallest letter greater than " + target + " is: " + result);
    }
}
