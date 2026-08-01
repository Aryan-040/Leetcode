class Solution {
    public boolean[] transformStr(String s, String[] strs) {
        int n = s.length();

        // Number of zeros in each prefix of s
        int[] prefixZeros = new int[n];
        int totalZeros = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                totalZeros++;
            }
            prefixZeros[i] = totalZeros;
        }

        boolean[] ans = new boolean[strs.length];

        for (int k = 0; k < strs.length; k++) {
            String t = strs[k];

            int fixedZeros = 0;
            int questions = 0;

            for (char c : t.toCharArray()) {
                if (c == '0') {
                    fixedZeros++;
                } else if (c == '?') {
                    questions++;
                }
            }

            // Must be possible to end with exactly totalZeros zeros
            if (fixedZeros > totalZeros ||
                fixedZeros + questions < totalZeros) {
                ans[k] = false;
                continue;
            }

            // Number of '?' that must become 0
            int zerosNeeded = totalZeros - fixedZeros;

            int prefixFixedZeros = 0;
            int prefixQuestions = 0;

            boolean possible = true;

            for (int i = 0; i < n; i++) {
                char c = t.charAt(i);

                if (c == '0') {
                    prefixFixedZeros++;
                } else if (c == '?') {
                    prefixQuestions++;
                }

                // Maximum zeros we can put in this prefix
                int maxPrefixZeros =
                    prefixFixedZeros +
                    Math.min(prefixQuestions, zerosNeeded);

                if (maxPrefixZeros < prefixZeros[i]) {
                    possible = false;
                    break;
                }
            }

            ans[k] = possible;
        }

        return ans;
    }
}