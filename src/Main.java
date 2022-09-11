public class Main {
    public static void main(String[] args) {

        // Input prices and transaction limit
        int[] prices = {3,2,6,5,0,3};
        
        int k = 2;

        // Compute max profit with at most k transactions
        Solution solObj = new Solution();

        int maxProfit = solObj.maxProfit(k, prices);

        // Print results
        System.out.println("max profit: " + maxProfit);
    }
}