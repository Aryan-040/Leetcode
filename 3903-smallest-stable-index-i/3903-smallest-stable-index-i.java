class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];

        max[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max[i] = Math.max(max[i - 1], nums[i]);
        }
        min[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            min[i] = Math.min(min[i + 1], nums[i]);
        }

        for (int i = 0; i < nums.length; i++) {
            int res = max[i] - min[i];
            if (res <= k) {
                return i;
            }
        }
        return -1;
    }
}