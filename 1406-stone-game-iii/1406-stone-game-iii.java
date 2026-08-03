class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        Integer[] memo = new Integer[n];
        int result = dfs(stoneValue, 0, memo);
        if (result > 0) return "Alice";
        if (result < 0) return "Bob";
        return "Tie";
    }

    private int dfs(int[] stones, int i, Integer[] memo) {
        if (i >= stones.length) return 0;
        if (memo[i] != null) return memo[i];

        int maxDiff = Integer.MIN_VALUE;
        int currentSum = 0;

        for (int k = 0; k < 3 && i + k < stones.length; k++) {
            currentSum += stones[i + k];
            maxDiff = Math.max(maxDiff, currentSum - dfs(stones, i + k + 1, memo));
        }
        memo[i] = maxDiff;
        return maxDiff;
    }
}