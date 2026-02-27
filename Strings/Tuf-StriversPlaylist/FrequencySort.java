import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Sorts the characters in a string by decreasing order of frequency.
 */
public class FrequencySort {

    /**
     * Approach 1: Frequency Array + Sorting
     *
     * ### Explanation
     * 1. **Frequency Counting:** We use an integer array `freq` of size 128 (covering standard ASCII) to store the count of each character in the string.
     * 2. **Collect Unique Characters:** We iterate through the frequency array and collect all characters that appeared in the string into a list.
     * 3. **Sort by Frequency:** We sort this list of characters based on their counts in the `freq` array in descending order.
     * 4. **Build Result:** We iterate through the sorted characters and append each character to the result string `count` times.
     *
     * ### Dry Run
     * Input: `s = "tree"`
     *
     * 1. **Frequency Count:**
     *    - 't': 1, 'r': 1, 'e': 2
     *    - `freq` array updated at indices 116 ('t'), 114 ('r'), 101 ('e').
     *
     * 2. **Collect Characters:**
     *    - Loop 0 to 127. Found 'e' (101), 'r' (114), 't' (116).
     *    - List `characters` = ['e', 'r', 't'] (order depends on ASCII value).
     *
     * 3. **Sort:**
     *    - Compare frequencies: freq['e'] (2) vs freq['r'] (1) -> 'e' comes first.
     *    - Compare freq['r'] (1) vs freq['t'] (1) -> order maintained or swapped.
     *    - Sorted List: ['e', 'r', 't'] (or ['e', 't', 'r']).
     *
     * 4. **Build String:**
     *    - Process 'e': freq is 2. Append "ee". Result: "ee".
     *    - Process 'r': freq is 1. Append "r". Result: "eer".
     *    - Process 't': freq is 1. Append "t". Result: "eert".
     *
     * Output: "eert"
     *
     * ### Complexity
     * - **Time Complexity:** O(N), where N is the length of the string.
     *   - Counting frequencies takes O(N).
     *   - Sorting takes O(K log K) where K is unique characters (constant <= 128), so O(1).
     *   - Building the string takes O(N).
     * - **Space Complexity:** O(N) for the output string. The frequency array is O(1).
     */
    public String frequencySort(String s) {
        int[] freq  = new int[128];
        for(char c : s.toCharArray()){
            freq[c]++;
        }
        List<Character> characters = new ArrayList<>();
        for(int i = 0; i < freq.length; i++){
            if(freq[i] > 0){
                characters.add((char) i);
            }
        }
        // Sort characters by frequency in descending order
        characters.sort((a, b) -> freq[b] - freq[a]);
        StringBuilder result = new StringBuilder();
        for(char c : characters){
            int count = freq[c];
            while(count-- > 0){
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * Approach 2: Bucket Sort
     *
     * ### Explanation
     * Instead of sorting, we use the frequency as an index in an array (buckets).
     * 1. Count character frequencies using a HashMap.
     * 2. Create an array of lists (buckets) where index `i` stores characters that appear `i` times.
     *    The maximum frequency cannot exceed the string length `n`.
     * 3. Iterate through the buckets from the highest frequency (`n`) down to 1.
     * 4. For each frequency `i`, append the characters in that bucket `i` times to the result.
     *
     * ### Dry Run
     * Input: `s = "tree"`
     * Length `n = 4`.
     *
     * 1. **Frequency Count (Map):**
     *    - 't': 1, 'r': 1, 'e': 2
     *
     * 2. **Buckets (Array of Lists size 5):**
     *    - bucket[1]: ['t', 'r'], bucket[2]: ['e'], bucket[3]: null, bucket[4]: null
     *
     * 3. **Build String (Iterate n down to 1):**
     *    - i = 2: bucket[2] has ['e']. Append 'e' 2 times. Result: "ee".
     *    - i = 1: bucket[1] has ['t', 'r']. Append 't' 1 time, 'r' 1 time. Result: "eetr".
     *
     * Output: "eetr"
     *
     * ### Complexity
     * - **Time Complexity:** O(N). We iterate the string once to count, and the buckets once to build.
     * - **Space Complexity:** O(N). For the map and the buckets.
     */
    public String frequencySortBucket(String s) {
        int n = s.length();
        
        // Step 1: Count frequency
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        
        // Step 2: Create buckets
        List<Character>[] bucket = new List[n + 1];
        
        for (char ch : map.keySet()) {
            int freq = map.get(ch);
            
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }
            
            bucket[freq].add(ch);
        }
        
        // Step 3: Build result from high frequency to low
        StringBuilder sb = new StringBuilder();
        
        for (int i = n; i >= 1; i--) {
            
            if (bucket[i] != null) {
                
                for (char ch : bucket[i]) {
                    
                    int count = i;
                    while (count-- > 0) {
                        sb.append(ch);
                    }
                }
            }
        }
        
        return sb.toString();
    }

    /**
     * Approach 3: HashMap + Priority Queue (Heap)
     *
     * ### Explanation
     * 1. Count character frequencies using a HashMap.
     * 2. Add all unique characters to a Max-Heap (PriorityQueue) sorted by frequency descending.
     * 3. Poll characters from the heap one by one. Since it's a max-heap, the most frequent characters come out first.
     * 4. Append the polled character to the result string `frequency` times.
     *
     * ### Dry Run
     * Input: `s = "cccaaa"`
     *
     * 1. **Frequency Count:** 'c': 3, 'a': 3
     * 2. **Priority Queue (Max Heap):** Contains: ['c', 'a'] (Order might vary if frequencies are equal).
     * 3. **Build String:**
     *    - Poll 'c' (freq 3). Append "ccc". Result: "ccc".
     *    - Poll 'a' (freq 3). Append "aaa". Result: "cccaaa".
     *
     * ### Complexity
     * - **Time Complexity:** O(N + K log K), where N is string length and K is number of unique characters.
     * - **Space Complexity:** O(N) for result and map/heap.
     */
    public String frequencySortHeap(String s) {
        
        // Step 1: Count frequency
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        
        // Step 2: Max Heap based on frequency
        PriorityQueue<Character> pq = 
            new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
        
        pq.addAll(map.keySet());
        
        // Step 3: Build result
        StringBuilder sb = new StringBuilder();
        
        while (!pq.isEmpty()) {
            char ch = pq.poll();
            int freq = map.get(ch);
            
            while (freq-- > 0) {
                sb.append(ch);
            }
        }
        
        return sb.toString();
    }

    public static void main(String[] args) {
        FrequencySort solution = new FrequencySort();

        String s1 = "tree";
        System.out.println("Approach 1 (Array Sort) - Input: \"" + s1 + "\" -> Output: \"" + solution.frequencySort(s1) + "\"");

        String s2 = "cccaaa";
        System.out.println("Approach 2 (Bucket Sort) - Input: \"" + s2 + "\" -> Output: \"" + solution.frequencySortBucket(s2) + "\"");

        String s3 = "Aabb";
        System.out.println("Approach 3 (Heap Sort)   - Input: \"" + s3 + "\" -> Output: \"" + solution.frequencySortHeap(s3) + "\"");
    }
}
