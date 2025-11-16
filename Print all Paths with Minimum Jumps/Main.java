import java.util.*;

public class Main {
  static class Pair {
    int idx;
    String psf;
    int jumps;

    Pair(int idx, String psf, int jumps) {
      this.idx = idx;
      this.psf = psf;
      this.jumps = jumps;
    }
  }

  public static void main(String[] args) {
    int[] arr = { 3, 2, 4, 2, 0, 2, 3, 1, 2, 2 };
    printAllMinJumpPaths(arr);
  }

  static void printAllMinJumpPaths(int[] arr) {
    int n = arr.length;
    int[] dp = new int[n];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[n - 1] = 0;
    for (int i = n - 2; i >= 0; i--) {
      int step = arr[i];
      int min = Integer.MAX_VALUE;
      for (int j = 1; j <= step && i + j < n; j++) {
        min = Math.min(min, dp[i + j]);
      }
      if (min != Integer.MAX_VALUE)
        dp[i] = min + 1;
    }
    System.out.println("Minimum jumps: " + dp[0]);
    System.out.println("All Paths:");

    ArrayDeque<Pair> q = new ArrayDeque<>();
    q.add(new Pair(0, "0", dp[0]));
    while (!q.isEmpty()) {
      Pair rem = q.remove();
      if (rem.idx == n - 1) {
        System.out.println(rem.psf);
        continue;
      }
      int step = arr[rem.idx];
      for (int j = 1; j <= step && rem.idx + j < n; j++) {
        int next = rem.idx + j;
        if (dp[next] == rem.jumps - 1) {
          q.add(new Pair(next, rem.psf + " -> " + next, rem.jumps - 1));
        }
      }
    }
  }
}
