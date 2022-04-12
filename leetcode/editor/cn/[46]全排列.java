//给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1]
//输出：[[0,1],[1,0]]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 6 
// -10 <= nums[i] <= 10 
// nums 中的所有整数 互不相同 
// 
// Related Topics 数组 回溯 👍 1663 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /* List<List<Integer>> result = new ArrayList<List<Integer>>();
     LinkedList<Integer> path = new LinkedList<>(); //这里用LinkedList 是因为LinkedList有removeLast方法 而ArrayList没有
                                                     //用ArrayList 去出最后一个元素 根据索引 size-1
     boolean[] used;
     public List<List<Integer>> permute(int[] nums) {
             if (nums.length == 0) {
                 return result;
             }
             used = new boolean[nums.length];
             backTrack(nums);
             return result;
     }
     public void backTrack(int[] nums) {
         if( path.size() == nums.length){  //终止条件 遍历到了叶子节点
             result.add(new ArrayList(path));
         }
         for (int i = 0;i < nums.length;i++){
             if(used[i]){
                 continue;
             }
             path.add(nums[i]);  // 做出选择
             used[i] = true;
             backTrack(nums); // 递归 进入下一层决策
             path.removeLast();  //撤销选择
             used[i] = false;
         }
     }*/
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    List<Integer> path = new LinkedList<>(); //这里用LinkedList 是因为LinkedList有removeLast方法 而ArrayList没有

    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0) {
            return result;
        }
        backTrack(nums);
        return result;
    }

    public void backTrack(int[] nums) {
        if (path.size() == nums.length) {  //终止条件 遍历到了叶子节点
            result.add(new ArrayList(path));
        }
        for (int i = 0; i < nums.length; i++) {
            if (path.contains(nums[i])) {
                continue;
            }
            path.add(nums[i]);
            backTrack(nums);
            path.remove(path.size() - 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
