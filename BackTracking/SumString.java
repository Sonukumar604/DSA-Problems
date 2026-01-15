public class SumString {
    public boolean isSumString(String s){
        return isSumString(s, 0, 0, 0, 0);
    }
    private boolean isSumString(String s, int index, long first, long second, int count){
        if(index == s.length()){
            return count >= 3;
        }
        long currentNumber = 0;
        for(int i = index; i < s.length(); i++){
            // Avoid numbers with leading zeros
            if(s.charAt(index) == '0' && i > index) break;

            currentNumber = currentNumber * 10 + (s.charAt(i) - '0');

            if(count < 2){
                if(isSumString(s, i + 1, first, second, count + 1)){
                    return true;
                }
            } else {
                long sum = first + second;
                if(currentNumber == sum){
                    if(isSumString(s, i + 1, second, currentNumber, count + 1)){
                        return true;
                    }
                } else if(currentNumber > sum){
                    break; // No need to continue if currentNumber exceeds the sum
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        SumString solution = new SumString();
        String s1 = "112358";
        System.out.println(solution.isSumString(s1));  // Output: true
    }
}