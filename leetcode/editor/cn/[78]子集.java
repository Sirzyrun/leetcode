//给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。 
//
// 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0]
//输出：[[],[0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10 
// -10 <= nums[i] <= 10 
// nums 中的所有元素 互不相同 
// 
// Related Topics 位运算 数组 回溯 👍 1537 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> path = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        backTrack(nums, 0, n);
        return ans;
    }

    private void backTrack(int[] nums, int startIndex, int n) {
        // exit
        // 子集不需要遍历完就应该添加结果
        ans.add(new ArrayList(path));
        if (startIndex == n) {
            return;
        }

        // for
        for (int i = startIndex; i < n; i++) {
            path.add(nums[i]);
            backTrack(nums, i + 1, n);
            path.remove(path.size() - 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
