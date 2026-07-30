class Solution {

    class TrieNode {
        TrieNode[] child = new TrieNode[2];
    }

    TrieNode root = new TrieNode();

    private void insert(int num) {

        TrieNode node = root;

        for (int i = 31; i >= 0; i--) {

            int bit = (num >> i) & 1;

            if (node.child[bit] == null)
                node.child[bit] = new TrieNode();

            node = node.child[bit];
        }
    }

    private int getMaxXor(int num) {

        TrieNode node = root;
        int ans = 0;

        for (int i = 31; i >= 0; i--) {

            int bit = (num >> i) & 1;

            int opposite = 1 - bit;

            if (node.child[opposite] != null) {

                ans |= (1 << i);
                node = node.child[opposite];

            } else {

                node = node.child[bit];
            }
        }

        return ans;
    }

    public int[] maximizeXor(int[] nums, int[][] queries) {

        Arrays.sort(nums);

        int q = queries.length;

        int[][] offline = new int[q][3];

        for (int i = 0; i < q; i++) {

            offline[i][0] = queries[i][0]; // xi
            offline[i][1] = queries[i][1]; // mi
            offline[i][2] = i;             // original index
        }

        Arrays.sort(offline, (a, b) -> Integer.compare(a[1], b[1]));

        int[] ans = new int[q];

        int idx = 0;

        for (int[] query : offline) {

            int xi = query[0];
            int mi = query[1];
            int originalIndex = query[2];

            while (idx < nums.length && nums[idx] <= mi) {

                insert(nums[idx]);
                idx++;
            }

            if (idx == 0) {

                ans[originalIndex] = -1;

            } else {

                ans[originalIndex] = getMaxXor(xi);
            }
        }

        return ans;
    }
}