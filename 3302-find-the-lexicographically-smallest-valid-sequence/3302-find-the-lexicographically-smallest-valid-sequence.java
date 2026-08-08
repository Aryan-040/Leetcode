class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[] suf = new int[n + 1];
        Arrays.fill(suf, -1);

        int j = m - 1;
        suf[n] = j;

        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                j--;
            }
            suf[i] = j;
        }

        int[] ans = new int[m];
        int idx = 0;
        boolean used = false;

        for (int i = 0; i < n && idx < m; i++) {
            if (word1.charAt(i) == word2.charAt(idx)) {
                ans[idx++] = i;
            }
            else if (!used) {

                int remain = idx + 1;
                if (remain >= m || suf[i + 1] < remain) {
                    used = true;
                    ans[idx++] = i;
                }
            }
        }

        if (idx != m)
            return new int[0];

        return ans;
    }
}