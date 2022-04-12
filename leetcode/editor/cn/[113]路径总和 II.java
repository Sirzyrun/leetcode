//给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。 
//
// 叶子节点 是指没有子节点的节点。 
//
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：[[5,4,11,2],[5,8,4,5]]
// 
//
// 示例 2： 
//
// 
//输入：root = [1,2,3], targetSum = 5
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1,2], targetSum = 0
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点总数在范围 [0, 5000] 内 
// -1000 <= Node.val <= 1000 
// -1000 <= targetSum <= 1000 
// 
// 
// 
// Related Topics 树 深度优先搜索 回溯 二叉树 👍 686 👎 0


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
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        List<Integer> path = new LinkedList<>();   //如果时new ArrayList<>() 就不行
        dfs(root, targetSum, path, ans);           // 注意ArrayList 与 LinkedList的区别
        return ans;
    }

    private void dfs(TreeNode root, int targetSum, List<Integer> path, List<List<Integer>> ans) {
        path.add(root.val);   //   写到结束条件之后也不行  会导致越界
        // 写到结束条件之后 会导致7根本就没有加入path 而回溯的时候想要移除7其实移除的是11
        // 导致回溯到4 时 移除的是5   导致回溯到5时 path.size = 0   从而导致path.size -1越界、
        // 结束条件
        if (root.left == null && root.right == null) {  //到达叶子节点
            if (root.val == targetSum) {
                ans.add(new ArrayList<>(path));
            }
            return;// 无论结果符合不符合都要结束
        }
        //没有到达叶子节点
        // 本层逻辑   cur
//        int val = root.val;
        if (root.left != null) {
            dfs(root.left, targetSum - root.val, path, ans);  //path 要回溯
            path.remove(path.size()-1);

        }
        if (root.right != null) {
            dfs(root.right, targetSum - root.val, path, ans);
            path.remove(path.size()-1);

        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
