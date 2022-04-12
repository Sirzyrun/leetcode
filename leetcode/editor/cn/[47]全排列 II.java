//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 8 
// -10 <= nums[i] <= 10 
// 
// Related Topics 数组 回溯 👍 850 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    List<Integer> path = new ArrayList<Integer>();
    boolean[] used; //标记数组

    public List<List<Integer>> permuteUnique(int[] nums) {
        used = new boolean[nums.length];  //布尔类型标记数组
        Arrays.fill(used, false);
        Arrays.sort(nums); // 对数组排序
        backtrack(nums, used);
        return result;
    }

    public void backtrack(int[] nums, boolean[] used) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<Integer>(path));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == true) {
                continue;
            }                              // 1 1 2     第一次选了第一个1 第二次不能选第二个1， 但是 第一次选了第二个1 第二次可以选第一个1
// 把used[i - 1] == true 改为false 依然正确      1 1 2     第一次选了第二个1 第二次不能选第一个1， 但是 第一次选了第一个1 第二次可以选第一个1
            // 与代码随想录 树层去重与树枝去重对应  好好思考
            if(used[i] == false) {
                path.add(nums[i]);
                used[i] = true;
                backtrack(nums, used);
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)
