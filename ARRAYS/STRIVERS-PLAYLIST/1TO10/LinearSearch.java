public class LinearSearch {
    public static int linearSearch(int[] arr, int num){
        if(arr == null) return -1;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == num){
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5, 6, 7};

        // Test case 1: Number not found
        int numToFind1 = 4;
        int result1 = linearSearch(arr, numToFind1);
        System.out.println("Searching for " + numToFind1 + ". Result (index): " + result1); // Expected: -1

        // Test case 2: Number found
        int numToFind2 = 5;
        int result2 = linearSearch(arr, numToFind2);
        System.out.println("Searching for " + numToFind2 + ". Result (index): " + result2); // Expected: 3
    }
}
