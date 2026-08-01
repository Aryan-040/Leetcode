class Solution {
    Integer[][] memo;

    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        memo = new Integer[n][n];
        return calculateScore(nums, 0, n - 1) >= 0;
    }

    private int calculateScore(int[] nums, int left, int right) {
        if (left == right) return nums[left];

        if (memo[left][right] != null) return memo[left][right];

        int pickLeft = nums[left] - calculateScore(nums, left + 1, right);
        int pickRight = nums[right] - calculateScore(nums, left, right - 1);

        return memo[left][right] = Math.max(pickLeft, pickRight);
    }
}