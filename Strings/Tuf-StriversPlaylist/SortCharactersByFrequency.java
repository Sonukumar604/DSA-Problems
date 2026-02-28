import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class SortCharactersByFrequency {
    /**
     * Approach 1: Frequency Array + Sorting
     *
     * ### Explanation
     * 1. **Frequency Counting:** Use an array `freq` of size 128 to store the count of each character.
     * 2. **Filter & Collect:** Create a list of characters that appear in the string.
     * 3. **Sort:** Sort the list based on the frequency values in descending order.
     * 4. **Reconstruct:** Append characters to the result string based on their frequency.
     *
     * ### Dry Run
     * Input: `s = "tree"`
     * 1. **Count:** 't':1, 'r':1, 'e':2.
     * 2. **List:** ['e', 'r', 't'] (Initial order depends on ASCII).
     * 3. **Sort:** Compare freqs. 'e'(2) > 'r'(1). 'e' comes first. 'r' and 't' have same freq.
     *    Sorted List: ['e', 'r', 't'] (or ['e', 't', 'r']).
     * 4. **Build:**
     *    - 'e' (2 times) -> "ee"
     *    - 'r' (1 time) -> "eer"
     *    - 't' (1 time) -> "eert"
     * Output: "eert"
     *
     * ### Complexity
     * - **Time Complexity:** O(N), where N is the length of the string. Sorting takes O(K log K) where K=128, which is constant.
     * - **Space Complexity:** O(1) (or O(K) where K=128) for frequency array.
     */
    public String frequencySort(String s) {
        int[] freq = new int[128];
        for(char ch : s.toCharArray()){
            freq[ch]++;
        }
        List<Character> list = new ArrayList<>();
        for(int i = 0; i < 128; i++){
            if(freq[i] > 0){
                list.add((char) i);
            }
        }
        Collections.sort(list, (a, b) -> freq[b] - freq[a]);
        StringBuilder sb = new StringBuilder();
        for(char ch : list){
            int count = freq[ch];
            while(count-- > 0){
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    /**
     * Approach 2: Bucket Sort
     *
     * ### Explanation
     * 1. **Frequency Counting:** Count character frequencies.
     * 2. **Buckets:** Create an array of lists (buckets) where index `i` stores characters that appear `i` times.
     * 3. **Fill Buckets:** Iterate through the frequency array and place characters into their corresponding buckets.
     * 4. **Reconstruct:** Iterate from the highest frequency bucket down to 1 and append characters.
     *
     * ### Dry Run
     * Input: `s = "cccaaa"`
     * 1. **Count:** 'c':3, 'a':3.
     * 2. **Buckets:** Size 7.
     *    - bucket[3] gets 'c' and 'a'.
     * 3. **Build:**
     *    - i=6..4: empty.
     *    - i=3: contains 'c', 'a'.
     *      - Append 'c' 3 times -> "ccc"
     *      - Append 'a' 3 times -> "cccaaa"
     * Output: "cccaaa"
     *
     * ### Complexity
     * - **Time Complexity:** O(N).
     * - **Space Complexity:** O(N) for buckets.
     */
        public String frequencySortBucket(String s) {
            int[] freq = new int[128];
            for(char ch : s.toCharArray()){
                freq[ch]++;
            }
            List<Character>[] buckets = new List[s.length() + 1];
            for(int i = 0; i < buckets.length; i++){
                buckets[i] = new ArrayList<>();
            }
            for(int i = 0; i < 128; i++){
                if(freq[i] > 0){
                    buckets[freq[i]].add((char) i);
                }
            }
            StringBuilder sb = new StringBuilder();
            for(int i = buckets.length - 1; i >= 0; i--){
                for(char ch : buckets[i]){
                    int count = freq[ch];
                    while(count-- > 0){
                        sb.append(ch);
                    }
                }
            }
            return sb.toString();
        }

    /**
     * Approach 3: HashMap + Priority Queue (Heap)
     *
     * ### Explanation
     * 1. **Frequency Map:** Count frequencies using a HashMap.
     * 2. **Max Heap:** Use a PriorityQueue to store characters, ordered by frequency descending.
     * 3. **Reconstruct:** Poll characters from the heap and append them to the result.
     *
     * ### Dry Run
     * Input: `s = "Aabb"`
     * 1. **Map:** {'A':1, 'a':1, 'b':2}
     * 2. **Heap:** ['b', 'A', 'a'] (b is at top).
     * 3. **Build:**
     *    - Poll 'b' (2) -> "bb"
     *    - Poll 'A' (1) -> "bbA"
     *    - Poll 'a' (1) -> "bbAa"
     * Output: "bbAa"
     *
     * ### Complexity
     * - **Time Complexity:** O(N + K log K), where K is unique characters.
     * - **Space Complexity:** O(N).
     */
    public String frequencySortPQ(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for(char ch : s.toCharArray()){
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        PriorityQueue<Character> pq = new PriorityQueue<>((a, b) -> freqMap.get(b) - freqMap.get(a));
        pq.addAll(freqMap.keySet());
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            char ch = pq.poll();
            int count = freqMap.get(ch);
            while(count-- > 0){
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SortCharactersByFrequency solution = new SortCharactersByFrequency();

        String s1 = "tree";
        System.out.println("Input: " + s1);
        System.out.println("Sort Approach:   " + solution.frequencySort(s1));
        System.out.println("Bucket Approach: " + solution.frequencySortBucket(s1));
        System.out.println("PQ Approach:     " + solution.frequencySortPQ(s1));
        System.out.println();

        String s2 = "cccaaa";
        System.out.println("Input: " + s2);
        System.out.println("Sort Approach:   " + solution.frequencySort(s2));
        System.out.println("Bucket Approach: " + solution.frequencySortBucket(s2));
        System.out.println("PQ Approach:     " + solution.frequencySortPQ(s2));
        System.out.println();

        String s3 = "Aabb";
        System.out.println("Input: " + s3);
        System.out.println("Sort Approach:   " + solution.frequencySort(s3));
        System.out.println("Bucket Approach: " + solution.frequencySortBucket(s3));
        System.out.println("PQ Approach:     " + solution.frequencySortPQ(s3));
    }
}
