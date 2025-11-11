import java.util.*;

public class Main {
  static class Pair {
    String psf;
    int i;
    int j;

    public Pair(String psf, int i, int j) {
      this.psf = psf;
      this.i = i;
      this.j = j;
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = 3;
    int m = 3;
    int grid[][] = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
    System.out.println(minPathSum(grid));
  }

  public static int minPathSum(int[][] grid) {
    int n = grid.length;
    int m = grid[0].length;

    int dp[][] = new int[n][m];
    for (int i = n - 1; i >= 0; i--) {
      for (int j = m - 1; j >= 0; j--) {
        if (i == n - 1 && j == m - 1) {
          dp[i][j] = grid[i][j];
        } else if (i == n - 1) {
          dp[i][j] = grid[i][j] + dp[i][j + 1];
        } else if (j == m - 1) {
          dp[i][j] = grid[i][j] + dp[i + 1][j];
        } else {
          dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
        }
      }
    }
  System.out.println("All minimum paths:");
    ArrayDeque<Pair> q = new ArrayDeque<>();
    q.add(new Pair("", 0, 0));
    while (q.size() > 0) {
      Pair rem = q.removeFirst();
      if (rem.i == n - 1 && rem.j == m - 1) {
      
        System.out.println(rem.psf);
      } else if (rem.i == n - 1 && rem.j + 1 < m) {
        q.add(new Pair(rem.psf + "H", rem.i, rem.j + 1));
      } else if (rem.j == m - 1 && rem.i + 1 < n)  {
        q.add(new Pair(rem.psf + "V", rem.i + 1, rem.j));
      } else {
        if (dp[rem.i][rem.j + 1] < dp[rem.i + 1][rem.j]) {
          q.add(new Pair(rem.psf + "H", rem.i, rem.j + 1));

        } else if (dp[rem.i][rem.j + 1] > dp[rem.i + 1][rem.j]) {
          q.add(new Pair(rem.psf + "V", rem.i + 1, rem.j));
        }
        else {
          q.add(new Pair(rem.psf + "H", rem.i, rem.j + 1));
          q.add(new Pair(rem.psf + "V", rem.i + 1, rem.j));
        }
      }
    }

    return dp[0][0];
  }
}