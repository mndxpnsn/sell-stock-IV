class Solution {

    int ops = 0;

    int maxProfitRec(int k, int[] p, int day, int t, int have, int[][][] dp) {

        int n = p.length;

        int profit = 0;

        ops++;

        // Out of array boundaries or transaction limit exceeded
        if(day > n - 1 || t > k) {
            return 0;
        }

        // Get data from memo table if available
        if(dp[day][t][have] != 0) {
            return dp[day][t][have];
        }

        // Compute max profit with at most k transactions
        if(day <= n - 1 && t < k) {
            // We have no stocks
            if(have == 0) {
                // Buy stock now
                int prof1 = -p[day] + maxProfitRec(k, p, day + 1, t, 1, dp);
                // Buy stock later
                int prof2 = maxProfitRec(k, p, day + 1, t, 0, dp);

                profit = Math.max(profit, prof1);
                profit = Math.max(profit, prof2);
            }

            // We have a stock
            if(have == 1) {
                // Sell stock
                int prof1 = p[day] + maxProfitRec(k, p, day, t + 1, 0, dp);
                // Sell stock later
                int prof2 = maxProfitRec(k, p, day + 1, t, 1, dp);

                profit = Math.max(profit, prof1);
                profit = Math.max(profit, prof2);
            }
        }

        // Store data in memo table
        dp[day][t][have] = profit;

        return profit;
    }

    public int maxProfit(int k, int[] prices) {

        int n = prices.length;

        // Declare and initialize memo table
        int[][][] dp = new int[n + 1][k + 1][2];

        // Compute max profit
        return maxProfitRec(k, prices, 0, 0, 0, dp);
    }
}