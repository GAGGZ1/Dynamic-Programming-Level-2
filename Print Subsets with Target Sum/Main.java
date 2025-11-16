import java.util.ArrayDeque;

public class Main {
    public static class Pair {
        int i;
        int j;
        String psf;
        Pair(int i, int j, String psf) {
            this.i = i;
            this.j = j;
            this.psf = psf;
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 7, 1, 3};
        int target = 10;
        int n = arr.length;
        boolean dp[][] = new boolean[arr.length + 1][target + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (j == 0) {
                    dp[i][j] = true; 
                } else if (i == 0) {
                    dp[i][j] = false; 
                } else {
                    if (dp[i - 1][j] == true) {
                        dp[i][j] = true;
                    }
                    else if (j >= arr[i - 1]) {
                        if (dp[i - 1][j - arr[i - 1]] == true) {
                            dp[i][j] = true;
                        }
                    }
                }
            }
        }
        System.out.println("Target sum possible: " + dp[arr.length][target]);
        System.out.println("--- Subsets ---");
        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.add(new Pair(n, target, ""));
        while (q.size() > 0) {
            Pair rem = q.removeFirst();
            if (rem.j == 0) {
                System.out.println(rem.psf);
                continue;
            }
            if (rem.i == 0) {
                continue;
            }
            boolean exc = dp[rem.i - 1][rem.j];
            if (exc) {
                q.add(new Pair(rem.i - 1, rem.j, rem.psf));
            }
            if (rem.j >= arr[rem.i - 1]) {
                boolean inc = dp[rem.i - 1][rem.j - arr[rem.i - 1]];
                if (inc) {
                    q.add(new Pair(rem.i - 1, rem.j - arr[rem.i - 1], arr[rem.i - 1] + " " + rem.psf));
                }
            }
        }
    }
}