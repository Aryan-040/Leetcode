class Solution {
    public boolean[] validSubarrays(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        int q = queries.length;
        int block = Math.max(1, (int) Math.sqrt(n));

        Query[] qs = new Query[q];
        for (int i = 0; i < q; i++) {
            qs[i] = new Query(queries[i][0], queries[i][1], i);
        }

        Arrays.sort(qs, (a, b) -> {
            int ba = a.l / block;
            int bb = b.l / block;

            if (ba != bb) return Integer.compare(ba, bb);

            return (ba & 1) == 0
                    ? Integer.compare(a.r, b.r)
                    : Integer.compare(b.r, a.r);
        });

        HashMap<Integer, Integer> map = new HashMap<>();
        int id = 0;

        int[] compressed = new int[n];
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], id++);
            }
            compressed[i] = map.get(nums[i]);
        }

        int[] freq = new int[id];
        boolean[] ans = new boolean[q];

        int left = 0;
        int right = -1;
        int distinct = 0;
        int xor = 0;

        for (Query query : qs) {
            while (left > query.l) {
                --left;
                int x = compressed[left];

                if (freq[x] == 0) distinct++;
                freq[x]++;
                xor ^= nums[left];
            }

            while (right < query.r) {
                ++right;
                int x = compressed[right];

                if (freq[x] == 0) distinct++;
                freq[x]++;
                xor ^= nums[right];
            }

            while (left < query.l) {
                int x = compressed[left];

                freq[x]--;
                if (freq[x] == 0) distinct--;
                xor ^= nums[left];

                left++;
            }

            while (right > query.r) {
                int x = compressed[right];

                freq[x]--;
                if (freq[x] == 0) distinct--;
                xor ^= nums[right];

                right--;
            }

            int len = query.r - query.l + 1;

            ans[query.idx] =
                len % 2 == 0 &&
                len >= 2 * k &&
                distinct == k &&
                xor == 0;
        }

        return ans;
    }

    static class Query {
        int l, r, idx;

        Query(int l, int r, int idx) {
            this.l = l;
            this.r = r;
            this.idx = idx;
        }
    }
}