import java.util.ArrayDeque;

public class Main {
    static class Pair {
        int i;
        int j;
        String psf;

        public Pair(int i, int j, String psf) {
            this.i = i;
            this.j = j;
            this.psf = psf;
        }
    }

    public static void main(String[] args) {

        int arr[][] = {
    {10, 33, 13, 15, 20},
    {22, 21, 04, 1, 12},
    {5,  0,  2,  3,  8},
    {0,  6,  14, 2,  9},
    {1,  2,  3,  4,  5}
};


        int dp[][] = new int[arr.length][arr[0].length];

        for (int j = arr[0].length - 1; j >= 0; j--) {
            for (int i = 0; i < arr.length; i++) {
                if (j == arr[0].length - 1) {
                    dp[i][j] = arr[i][j];
                } else if (i == 0) {
                    dp[i][j] = arr[i][j] + Math.max(dp[i][j + 1], dp[i + 1][j + 1]);
                } else if (i == arr.length - 1) {
                    dp[i][j] = arr[i][j] + Math.max(dp[i][j + 1], dp[i - 1][j + 1]);
                } else {
                    dp[i][j] = arr[i][j] + Math.max(dp[i][j + 1],
                            Math.max(dp[i - 1][j + 1], dp[i + 1][j + 1]));
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i][0] > max) max = dp[i][0];
        }

        System.out.println(max);

        ArrayDeque<Pair> q = new ArrayDeque<>();

        for (int i = 0; i < dp.length; i++) {
            if (dp[i][0] == max) {
                q.add(new Pair(i, 0, i + ""));
            }
        }

        while (!q.isEmpty()) {
            Pair rem = q.removeFirst();

            if (rem.j == arr[0].length - 1) {
                System.out.println(rem.psf);
                continue;
            }

            if (rem.i == 0) {
                int g = Math.max(dp[rem.i][rem.j + 1], dp[rem.i + 1][rem.j + 1]);

                if (g == dp[rem.i][rem.j + 1]) {
                    q.add(new Pair(rem.i, rem.j + 1, rem.psf + " d2"));
                }
                if (g == dp[rem.i + 1][rem.j + 1]) {
                    q.add(new Pair(rem.i + 1, rem.j + 1, rem.psf + " d3"));
                }
            }

            else if (rem.i == arr.length - 1) {
                int g = Math.max(dp[rem.i][rem.j + 1], dp[rem.i - 1][rem.j + 1]);

                if (g == dp[rem.i][rem.j + 1]) {
                    q.add(new Pair(rem.i, rem.j + 1, rem.psf + " d2"));
                }
                if (g == dp[rem.i - 1][rem.j + 1]) {
                    q.add(new Pair(rem.i - 1, rem.j + 1, rem.psf + " d1"));
                }
            }

            else {
                int g = Math.max(dp[rem.i][rem.j + 1],
                        Math.max(dp[rem.i - 1][rem.j + 1], dp[rem.i + 1][rem.j + 1]));

                if (g == dp[rem.i][rem.j + 1]) {
                    q.add(new Pair(rem.i, rem.j + 1, rem.psf + " d2"));
                }
                if (g == dp[rem.i - 1][rem.j + 1]) {
                    q.add(new Pair(rem.i - 1, rem.j + 1, rem.psf + " d1"));
                }
                if (g == dp[rem.i + 1][rem.j + 1]) {
                    q.add(new Pair(rem.i + 1, rem.j + 1, rem.psf + " d3"));
                }
            }
        }

    }
}
