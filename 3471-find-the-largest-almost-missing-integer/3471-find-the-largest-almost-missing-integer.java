class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : nums) freq.merge(x, 1, Integer::sum);

        if (k == 1) {
            int ans = -1;
            for (Map.Entry<Integer, Integer> e : freq.entrySet())
                if (e.getValue() == 1) ans = Math.max(ans, e.getKey());
            return ans;
        }

        if (k == n) {
            int ans = nums[0];
            for (int x : nums) ans = Math.max(ans, x);
            return ans;
        }

        int ans = -1;
        if (freq.get(nums[0]) == 1) ans = Math.max(ans, nums[0]);
        if (freq.get(nums[n - 1]) == 1) ans = Math.max(ans, nums[n - 1]);
        return ans;
    }
}