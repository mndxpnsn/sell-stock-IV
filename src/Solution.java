class Solution {

    int max(int a, int b) {
        int res = 0;

        if(a > b) res = a;
        else res = b;

        return res;
    }

    int maxProfitRec(int k, int[] p, int i, int j, int t, int[][] dp) {

        int n = p.length;

        int profit = 0;
        int cut = -1;

        // Out of array boundaries
        if(j > n - 1 || t > k) {
            return 0;
        }

        // End of prices array
        if(j == n - 1 && t < k) {
            // Do not buy stock on day i or sell stock bought on day i on last day
            return max(profit, p[j] - p[i]);
        }

        // Get data from memo table if available
        if(dp[i][t] != -1) {
            return dp[i][t];
        }

        if(j < n - 1 && t < k) {
            // Sell stock bought on day i at a later day
            int prof_loc1 = maxProfitRec(k, p, i, j + 1, t, dp);
            // Do not buy stock on day i and instead buy stock at a later day
            int prof_loc2 = maxProfitRec(k, p, i + 1, i + 2, t, dp);

            // Sell stock bought on day i on day j and buy stock on day j + 1 or later
            // Also, do not consider selling stocks on days leading to negative profits
            int prof_loc3 = p[j] - p[i];
            if(prof_loc3 >= 0) {
                prof_loc3 = p[j] - p[i] + maxProfitRec(k, p, j, j + 1, t + 1, dp);
            }

            // Compute max profit
            profit = max(profit, prof_loc1);
            profit = max(profit, prof_loc2);
            profit = max(profit, prof_loc3);

        }

        // Store data in memo table
        dp[i][t] = profit;

        return profit;
    }

    void initDP(int[][] dp, int n, int k) {

        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < k; ++j) {
                dp[i][j] = -1;
            }
        }
    }

    public int maxProfit(int k, int[] prices) {

        int n = prices.length;

        // Declare and initialize memo table
        int[][] dp = new int[n + 1][k + 1];

        initDP(dp, n + 1, k + 1);

        // Compute max profit
        return maxProfitRec(k, prices, 0, 1, 0, dp);
    }
}