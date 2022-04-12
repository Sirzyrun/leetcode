//给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。 
//
// 
//
// 示例1： 
//
// 
//
// 
//输入: root = [1,3,2,5,3,null,9]
//输出: [1,3,9]
// 
//
// 示例2： 
//
// 
//输入: root = [1,2,3]
//输出: [1,3]
// 
//
// 
//
// 提示： 
//
// 
// 二叉树的节点个数的范围是 [0,10⁴] 
// -2³¹ <= Node.val <= 2³¹ - 1 
// 
//
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 169 👎 0


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
    public List<Integer> largestValues(TreeNode root) {

        List<Integer> ans = new ArrayList<>();
        lookForLevelMax(ans, root);
        return ans;
    }

    private void lookForLevelMax(List<Integer> ans, TreeNode root) {
        if (root == null) {
            return;
        }
        // 1 初始化队列
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        // 2 BFS
        while (!q.isEmpty()) {
            int size = q.size();
            int curMax = Integer.MIN_VALUE;
            while (size != 0) {
                TreeNode curnode = q.poll();
                curMax = Math.max(curMax, curnode.val);
                if (curnode.left != null) {
                    q.offer(curnode.left);
                }
                if (curnode.right != null) {
                    q.offer(curnode.right);
                }
                size--;
            }
            ans.add(curMax);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
