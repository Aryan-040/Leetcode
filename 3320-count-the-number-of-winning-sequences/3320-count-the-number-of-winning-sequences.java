class Solution {
    private long[][][] memo;
    private final int MOD = 1_000_000_007;

    public int countWinningSequences(String s) {
        int n = s.length();
        // lastMove: 0=F, 1=W, 2=E, 3=None
        // scoreDiff: range -n to n, offset by n -> 0 to 2n
        memo = new long[n][4][2 * n + 1];
        for (long[][] row : memo) for (long[] col : row) java.util.Arrays.fill(col, -1);
        
        return (int) solve(0, 3, n, s, n);
    }

    private long solve(int i, int last, int n, String s, int diff) {
        if (i == n) return diff > n ? 1 : 0;
        if (memo[i][last][diff] != -1) return memo[i][last][diff];

        long count = 0;
        for (int move = 0; move < 3; move++) {
            if (move == last) continue;
            
            int score = getScore(move, s.charAt(i));
            count = (count + solve(i + 1, move, n, s, diff + score)) % MOD;
        }
        return memo[i][last][diff] = count;
    }

    private int getScore(int bob, char alice) {
        // F=0, W=1, E=2
        if (bob == 0 && alice == 'E') return 1; // F beats E
        if (bob == 1 && alice == 'F') return 1; // W beats F
        if (bob == 2 && alice == 'W') return 1; // E beats W
        if ((bob == 0 && alice == 'W') || (bob == 1 && alice == 'E') || (bob == 2 && alice == 'F')) return -1;
        return 0;
    }
}