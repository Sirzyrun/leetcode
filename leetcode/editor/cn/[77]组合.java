                                                                                                                                   //给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
//
// 你可以按 任何顺序 返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 4, k = 2
//输出：
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
//
// 示例 2： 
//
// 
//输入：n = 1, k = 1
//输出：[[1]] 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 20 
// 1 <= k <= n 
// 
// Related Topics 数组 回溯 👍 903 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        backtrack(1, n, k, list, ans);
        return ans;
    }

    public void backtrack(int index, int n, int k, List<Integer> list, List<List<Integer>> ans) {
        if (index <= n + 1 && k == 0) {
            ans.add(new ArrayList(list));
            return;
        }
        if (index > n) {  //这个判断要放后面
            return;
        }
        list.add(index);
        k--;
        backtrack(index + 1, n, k, list, ans);
        list.remove(list.size() - 1);
        k++;
        backtrack(index + 1, n, k, list, ans);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
