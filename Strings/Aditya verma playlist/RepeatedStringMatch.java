public class RepeatedStringMatch {
    // Returns the minimum number of times A must be repeated so that B is a substring of the repeated string
    public static int repeatedStringMatch(String A, String B) {
        int count = 1;
        StringBuilder repeated = new StringBuilder(A);
        while (repeated.length() < B.length()) {
            repeated.append(A);
            count++;
        }
        if (kmpSearch(repeated.toString(), B)) {
            return count;
        }
        // Try one more repeat in case B overlaps the end and start of repeated A
        repeated.append(A);
        count++;
        if (kmpSearch(repeated.toString(), B)) {
            return count;
        }
        return -1;
    }

    // KMP search to check if pattern exists in text
    private static boolean kmpSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int[] lps = computeLPSArray(pattern);

        int i = 0, j = 0;
        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (j == m) return true;
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }
        return false;
    }

    // Compute LPS (Longest Prefix Suffix) array for KMP
    private static int[] computeLPSArray(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0;
        lps[0] = 0;
        int i = 1;
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else if (len > 0) {
                len = lps[len - 1];
            } else {
                lps[i] = 0;
                i++;
            }
        }
        return lps;
    }

    // Example usage
    public static void main(String[] args) {
        String A = "abcd";
        String B = "cdabcdab";
        int result = repeatedStringMatch(A, B);
        System.out.println("Minimum repeats: " + result); // Output: 3
    }
}