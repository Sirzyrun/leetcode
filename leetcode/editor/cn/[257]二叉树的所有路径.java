//给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。 
//
// 叶子节点 是指没有子节点的节点。 
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,3,null,5]
//输出：["1->2->5","1->3"]
// 
//
// 示例 2： 
//
// 
//输入：root = [1]
//输出：["1"]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数目在范围 [1, 100] 内 
// -100 <= Node.val <= 100 
// 
// Related Topics 树 深度优先搜索 字符串 回溯 二叉树 👍 666 👎 0


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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        List<Integer> path = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(root, ans, path);
        return ans;
    }

    private void dfs(TreeNode root, List<String> ans,List<Integer> path) {
        int curval = root.val;
        path.add(curval);
        // 结束条件
        if (root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int i : path) {
                sb.append(i).append("->");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
            ans.add(sb.toString());
            return;
        }

        // 每层逻辑
        if (root.left != null) {
            dfs(root.left, ans, path);
            // 回溯
            path.remove(path.size() - 1);
        }
        if (root.right != null) {
            dfs(root.right, ans,path);
            // 回溯
            path.remove(path.size() - 1);
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)
