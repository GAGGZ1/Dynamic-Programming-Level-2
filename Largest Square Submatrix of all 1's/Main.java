class Main {
  public static int maximalSquare(char[][] m) {
    int dp[][] = new int[m.length][m[0].length];

    int ans = 0;
    for (int i = dp.length - 1; i >= 0; i--) {
      for (int j = dp[0].length - 1; j >= 0; j--) {
        if (i == dp.length - 1 && j == dp[0].length - 1) {
          if (m[i][j] == '1')
            dp[i][j] = 1;
          else
            dp[i][j] = 0;
        } else if (i == dp.length - 1) {
          if (m[i][j] == '1')
            dp[i][j] = 1;
          else
            dp[i][j] = 0;
        } else if (j == dp[0].length - 1) {
          if (m[i][j] == '1')
            dp[i][j] = 1;
          else
            dp[i][j] = 0;
        } else {
          if (m[i][j] == '1') {
            int min = Math.min(dp[i][j + 1], dp[i + 1][j]);
            min = Math.min(min, dp[i + 1][j + 1]);
            dp[i][j] = min + 1;

          } else {
            dp[i][j] = 0;
          }

        }
        ans = Math.max(ans, dp[i][j]);
      }
    }
    return ans * ans;
  }

   public static void main(String[] args) {
        char[][] mat = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        System.out.println(maximalSquare(mat));  
    }
}