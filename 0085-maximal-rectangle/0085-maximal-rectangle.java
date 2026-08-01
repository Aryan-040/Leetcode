class Solution {

    private int largestRectangleArea(int[] heights) {
        Stack<Integer> st = new Stack<>();
        int maxi = 0;

        for (int i = 0; i <= heights.length; i++) {

            int curr = (i == heights.length) ? 0 : heights[i];

            while (!st.isEmpty() && heights[st.peek()] > curr) {
                int height = heights[st.pop()];

                int right = i;
                int left = st.isEmpty() ? -1 : st.peek();

                int width = right - left - 1;

                maxi = Math.max(maxi, height * width);
            }

            st.push(i);
        }

        return maxi;
    }

    public int maximalRectangle(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int[] heights = new int[m];
        int maxi = 0;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1') {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }

            maxi = Math.max(maxi, largestRectangleArea(heights));
        }

        return maxi;
    }
}