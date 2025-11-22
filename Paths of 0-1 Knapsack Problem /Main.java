import java.util.ArrayDeque;

public class Main {

    public static class Pair {
        int i;
        int j;
        String psf;

        public Pair(int i, int j, String psf) {
            this.i = i;
            this.j = j;
            this.psf = psf;
        }
    }

    public static int knapsack(int W, int val[], int wt[]) {

        int n = val.length;
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {
                dp[i][w] = dp[i - 1][w]; // exclude
                if (w >= wt[i - 1]) { // include
                    dp[i][w] = Math.max(dp[i][w],
                            dp[i - 1][w - wt[i - 1]] + val[i - 1]);
                }
            }
        }

        System.out.println("Max Value = " + dp[n][W]);
        System.out.println("Item combinations:");

        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.add(new Pair(n, W, ""));
        while (!q.isEmpty()) {
            Pair rem = q.removeFirst();
            if (rem.i == 0 || rem.j == 0) {
                System.out.println(rem.psf.trim());
                continue;
            }
            int exclude = dp[rem.i - 1][rem.j];
            if (rem.j >= wt[rem.i - 1]) {
                int include = dp[rem.i - 1][rem.j - wt[rem.i - 1]] + val[rem.i - 1];
                if (dp[rem.i][rem.j] == include) {
                    q.add(new Pair(rem.i - 1, rem.j - wt[rem.i - 1],
                            (rem.i - 1) + " " + rem.psf));
                }
            }
            if (dp[rem.i][rem.j] == exclude) {
                q.add(new Pair(rem.i - 1, rem.j, rem.psf));
            }
        }

        return dp[n][W];
    }

    public static void main(String[] args) {
        int W = 7;
        int val[] = {15, 14, 10, 45, 30};
        int wt[] = {2, 5, 1, 3, 4};

        knapsack(W, val, wt);
    }
}
