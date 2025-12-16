import java.util.Stack;
public class StockSpanner {
    /**
     * The StockSpanner class calculates the span of stock prices.
     * The span of a stock's price today is defined as the maximum number of consecutive days
     * before today for which the price of the stock was less than or equal to today's price.
     * 
     * Time Complexity: O(1) for each next() call on average.
     * Space Complexity: O(n) for storing the stack.
     */
    private Stack<int[]> stack;
    public StockSpanner(){
        stack = new Stack<>();
    }
    public int next(int value){
        int index = 1;
        while(!stack.isEmpty() && stack.peek()[0] <= value){
            index += stack.pop()[1];
        }
        stack.push(new int[]{value, index});
        return index;
    }
    public static void main(String[] args){
        StockSpanner spanner = new StockSpanner();
        System.out.println(spanner.next(100));
        System.out.println(spanner.next(80));
        System.out.println(spanner.next(60));
        System.out.println(spanner.next(70));
        System.out.println(spanner.next(60));
        System.out.println(spanner.next(75));
        System.out.println(spanner.next(85));
    }
}
