public class SumOfAllSubsetXor {
    // A static variable to accumulate the sum of all subset XORs.
    static int totalSum = 0;

    public static void main(String[] args){
        int[] nums = {2,4};
        // Start the backtracking process.
        backtrack(nums, 0, 0);
        System.out.println("Sum of all subsets XOR total: " + totalSum);
    } // The main method was missing its closing brace.

    /**
     * Recursively explores all subsets, calculates their XOR sum, and adds it to the total.
     * @param nums The input array of numbers.
     * @param start The starting index for the current exploration.
     * @param currentXor The XOR sum of the subset built so far.
     */
    static void backtrack(int[] nums, int start, int currentXor){
        totalSum += currentXor;
        for(int i = start; i < nums.length; i++){
            // Include the current element and recurse.
            backtrack(nums, i + 1, currentXor ^ nums[i]);
            // Exclude the current element (handled by the loop continuing to the next 'i').
        }
    }
}
