class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int ans = 0;

        for (int num : arr1) {
            int idx = Arrays.binarySearch(arr2, num - d);

            if (idx < 0)
                idx = -idx - 1;
            else {
                while (idx > 0 && arr2[idx - 1] == num - d)
                    idx--;
            }

            if (idx < arr2.length && arr2[idx] <= num + d)
                continue; // Invalid
            else
                ans++;
        }

        return ans;
    }
}