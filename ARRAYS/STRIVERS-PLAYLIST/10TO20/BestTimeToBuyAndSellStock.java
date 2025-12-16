public class BestTimeToBuyAndSellStock {
    public static int maxProfit(int[] prices){
        int n = prices.length;
        int min = prices[0];
        int profit = 0;
        for(int i = 1; i < n; i++){
            if(prices[i] < min){
                min = prices[i];
            }
            else if(prices[i] == min){
                continue;
            }
            else{
                profit = Math.max(profit, prices[i] - min);
            }
        }
        return profit;
    }
    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println("Maximum profit: " + maxProfit(prices));
    }
}
