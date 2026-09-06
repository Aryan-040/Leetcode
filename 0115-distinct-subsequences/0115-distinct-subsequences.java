class Solution {
    private int solve(int i, int j, String s, String t, int[][] dp) {
        if (j == t.length())
            return 1;
        if (i == s.length())
            return 0;
        if (dp[i][j] != -1)
            return dp[i][j];

        if (s.charAt(i) == t.charAt(j)) {
            int take = solve(i + 1, j + 1, s, t, dp);
            int notTake = solve(i + 1, j, s, t, dp);
            dp[i][j] = take + notTake;
        }else {
            dp[i][j] = solve(i + 1, j, s, t, dp);
        }
        return dp[i][j];
    }

    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length()][t.length()];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        return solve(0, 0, s, t, dp);
    }
}