//给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。 
//
// candidates 中的每个数字在每个组合中只能使用 一次 。 
//
// 注意：解集不能包含重复的组合。 
//
// 
//
// 示例 1: 
//
// 
//输入: candidates = [10,1,2,7,6,1,5], target = 8,
//输出:
//[
//[1,1,6],
//[1,2,5],
//[1,7],
//[2,6]
//] 
//
// 示例 2: 
//
// 
//输入: candidates = [2,5,2,1,2], target = 5,
//输出:
//[
//[1,2,2],
//[5]
//] 
//
// 
//
// 提示: 
//
// 
// 1 <= candidates.length <= 100 
// 1 <= candidates[i] <= 50 
// 1 <= target <= 30 
// 
// Related Topics 数组 回溯 👍 876 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int sum = 0;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] flag = new boolean[candidates.length];
        Arrays.sort(candidates);
        backTrack(ans, path, candidates, target, sum, 0, flag);
        return ans;
    }

    public void backTrack(List<List<Integer>> ans,
                          List<Integer> path,
                          int[] candidates,
                          int target,
                          int sum,
                          int startIndex,
                          boolean[] flag) {
        // exit
        if (sum == target) {
            ans.add(new ArrayList(path));
            return;
        }
        if (sum > target) {
            return;
        }
        for (int i = startIndex; i < candidates.length; i++) {
            if (i > 0 && candidates[i] == candidates[i - 1] && !flag[i - 1]) {
                continue;
            }
            flag[i] = true;
            path.add(candidates[i]);
            sum += candidates[i];
            backTrack(ans, path, candidates, target, sum, i + 1, flag);
            path.remove(path.size() - 1);
            sum -= candidates[i];
            flag[i] = false;
        }


    }

}
//leetcode submit region end(Prohibit modification and deletion)
