class Solution {

    public int ops = 0;
    public int ops2 = 0;

    int maxProfitRec(int k, int[] p, int i, int j, int t, int[][] dp) {

        int n = p.length;

        int profit = 0;

        ops++;

        // Out of array boundaries or transaction limit exceeded
        if(j > n - 1 || t > k) {
            return 0;
        }

        // Get data from memo table if available
        if(dp[i][t] != 0) {
            return dp[i][t];
        }

        // Compute max profit with at most k transactions
        if(j <= n - 1 && t < k) {
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

    int maxProfitRec2(int k, int[] p, int j, int t, int have, int[][][] dp) {

        int n = p.length;

        int profit = 0;

        ops2++;

        // Out of array boundaries or transaction limit exceeded
        if(j > n - 1 || t > k) {
            return 0;
        }

        // Get data from memo table if available
        if(dp[j][t][have] != 0) {
            return dp[j][t][have];
        }

        // Compute max profit with at most k transactions
        if(j <= n - 1 && t < k) {
            // We have no stocks
            if(have == 0) {
                // Buy stock on day i and sell at a later day
                int prof1 = -p[j] + maxProfitRec2(k, p, j + 1, t, 1, dp);
                // Do not buy stock on day i and instead buy stock at a later day
                int prof2 = maxProfitRec2(k, p, j + 1, t, 0, dp);

                profit = Math.max(profit, prof1);
                profit = Math.max(profit, prof2);
            }

            // We have a stock
            if(have == 1) {
                // Sell stock
                int prof3 = p[j] + maxProfitRec2(k, p, j, t + 1, 0, dp);
                // Sell stock later
                int prof4 = maxProfitRec2(k, p, j + 1, t, 1, dp);

                profit = Math.max(profit, prof3);
                profit = Math.max(profit, prof4);
            }
        }

        // Store data in memo table
        dp[j][t][have] = profit;

        return profit;
    }

    public int maxProfit2(int k, int[] prices) {

        int n = prices.length;

        // Declare and initialize memo table
        int[][][] dp = new int[n + 1][k + 1][2];

        // Compute max profit
        return maxProfitRec2(k, prices, 0, 0, 0, dp);
    }
}