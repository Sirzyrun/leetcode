//给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：[[3],[9,20],[15,7]]
// 
//
// 示例 2： 
//
// 
//输入：root = [1]
//输出：[[1]]
// 
//
// 示例 3： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 2000] 内 
// -1000 <= Node.val <= 1000 
// 
// Related Topics 树 广度优先搜索 二叉树 👍 1195 👎 0


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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        //  层序遍历一遍
        Order(ans,root);
        return ans;
    }

    private void Order(List<List<Integer>> list,TreeNode node) { //层序
        //极端情况
        if (node == null) {   // 空树
            return;
        }
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(node);
        while (!q.isEmpty()) {
            List<Integer> itemList = new ArrayList<>();
            int size = q.size();   // 获取当前行节点数
            while (size > 0) {
                TreeNode cur = q.poll();
                itemList.add(cur.val);
                // 将下一行节点依次加入队列
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
                size--;
            }
            list.add(itemList);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
