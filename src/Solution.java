class Solution {

    public int ops;


    int maxProfitRec(int k, int[] p, int i, int j, int t, int[][] dp) {

        int n = p.length;

        int profit = 0;

        ops++;

        // Out of array boundaries or transaction limit exceeded
        if(j > n - 1 || t > k) {
            return 0;
        }

        // End of prices array
        if(j == n - 1 && t < k) {
            // Do not buy stock on day i or sell stock bought on day i on last day
            return Math.max(profit, p[j] - p[i]);
        }

        // Get data from memo table if available
        if(dp[i][t] != 0) {
            return dp[i][t];
        }

        // Compute max profit with at most k transactions
        if(j < n - 1 && t < k) {
            // Buy stock on day i and sell at a later day
            int prof1 = maxProfitRec(k, p, i, j + 1, t, dp);
            // Do not buy stock on day i and instead buy stock at a later day
            int prof2 = maxProfitRec(k, p, i + 1, i + 2, t, dp);
            // Consider selling stock bought on day i
            int prof3 = p[j] - p[i] + maxProfitRec(k, p, j, j + 1, t + 1, dp);;

            // Compute max profit
            profit = Math.max(profit, prof1);
            profit = Math.max(profit, prof2);
            profit = Math.max(profit, prof3);

        }

        // Store data in memo table
        dp[i][t] = profit;

        return profit;
    }

    public int maxProfit(int k, int[] prices) {

        int n = prices.length;

        // Declare and initialize memo table
        int[][] dp = new int[n + 1][k + 1];

        // Compute max profit
        return maxProfitRec(k, prices, 0, 1, 0, dp);
    }
}