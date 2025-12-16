public class StringMatchingKMPAlgo {
    // LPS Array (Longest Prefix Suffix)
    public static void KMPSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int[] lps = new int[m];

        // Preprocess the pattern to get the LPS array
        computeLPSArray(pattern, m, lps);

        int i = 0; // index for text
        int j = 0; // index for pattern

        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }
            if (j == m) {
                System.out.println("Pattern found at index " + (i - j));
                j = lps[j - 1];
            } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
    }

    // Helper function to compute the LPS array
    private static void computeLPSArray(String pattern, int m, int[] lps) {
        int len = 0;
        lps[0] = 0;
        int i = 1;
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }
    public static void main(String[] args) {
        String text = "abababd";
        String pattern = "ababd";
        KMPSearch(text, pattern);
    }
}