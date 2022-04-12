//给定二叉树的根节点 root ，返回所有左叶子之和。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入: root = [3,9,20,null,null,15,7] 
//输出: 24 
//解释: 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
// 
//
// 示例 2: 
//
// 
//输入: root = [1]
//输出: 0
// 
//
// 
//
// 提示: 
//
// 
// 节点数在 [1, 1000] 范围内 
// -1000 <= Node.val <= 1000 
// 
//
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 407 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        // 1 dfs allLeftLeaves
        allLeftLeaves(root, list);
        // 2 sumOfList
        int sum = 0;
        for (int i : list) {
            sum += i;
        }
        return sum;
    }

    private void allLeftLeaves(TreeNode root, List<Integer> list) {
        // over
        if (root.left != null && root.left.left == null && root.left.right == null) {
            list.add(root.left.val);
        }
        // 递归
        if (root.left != null) {
            allLeftLeaves(root.left, list);
        }
        if (root.right != null) {
            allLeftLeaves(root.right, list);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
