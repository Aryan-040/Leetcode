class Solution {
    public int[] resultArray(int[] nums) {
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();

        arr1.add(nums[0]);
        arr2.add(nums[1]);

        for (int i = 2; i < nums.length; i++) {
            int last1 = arr1.get(arr1.size() - 1);
            int last2 = arr2.get(arr2.size() - 1);
            if (last1 > last2) {
                arr1.add(nums[i]);
            } else {
                arr2.add(nums[i]);
            }
        }
        
        // Concatenate arr1 and arr2
        int[] result = new int[arr1.size() + arr2.size()];
        int idx = 0;
        for (int num : arr1) result[idx++] = num;
        for (int num : arr2) result[idx++] = num;
        return result;
    }
}