import java.util.Random;

public class Main {

    static int[] initPrices(int n) {

        int[] prices = new int[n];

        Random r = new Random();

        for(int i = 0; i < n; ++i) {
            prices[i] = r.nextInt(1000);
        }

        return prices;
    }

    public static void main(String[] args) {

        // Input size prices array and transaction limit
        int k = 40;

        int n = 100;

        // Generate random input array for computation
        int[] prices = initPrices(n);

        // Compute max profit with at most k transactions
        Solution solObj = new Solution();

        int maxProfit = solObj.maxProfit(k, prices);

        // Print results
        System.out.println("max profit: " + maxProfit);
        System.out.println("number of operations: " + solObj.ops);
    }
}