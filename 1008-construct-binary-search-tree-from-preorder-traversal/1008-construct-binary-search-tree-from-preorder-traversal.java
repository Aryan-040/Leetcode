/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int index = 0;
    public TreeNode bstFromPreorder(int[] preorder) {
        return build(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
     private TreeNode build(int[] preorder, int low, int high) {

        if (index == preorder.length)
            return null;

        int val = preorder[index];

        if (val < low || val > high)
            return null;

        TreeNode root = new TreeNode(val);
        index++;

        root.left = build(preorder, low, val);
        root.right = build(preorder, val, high);

        return root;
    }
}