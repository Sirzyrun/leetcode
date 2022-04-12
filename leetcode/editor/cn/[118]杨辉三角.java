//给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。 
//
// 在「杨辉三角」中，每个数是它左上方和右上方的数的和。 
//
// 
//
// 
//
// 示例 1: 
//
// 
//输入: numRows = 5
//输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
// 
//
// 示例 2: 
//
// 
//输入: numRows = 1
//输出: [[1]]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= numRows <= 30 
// 
// Related Topics 数组 动态规划 👍 733 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        if (numRows == 0) {
            return ans;
        }
        if (numRows == 1) {
             ans.add(new ArrayList<>(Arrays.asList(1)));
            return ans;
        }

        if (numRows == 2) {
            ans.add(new ArrayList<>(Arrays.asList(1)));
             ans.add(new ArrayList<>(Arrays.asList(1,1)));
             return ans;
        }
        ans.add(new ArrayList<>(Arrays.asList(1)));
        ans.add(new ArrayList<>(Arrays.asList(1,1)));
        for (int i = 3; i <= numRows; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            for(int j = 1;j<i-1;j++){
                list.add(ans.get(ans.size()-1).get(j-1)+ans.get(ans.size()-1).get(j));
            }
            list.add(1);
            ans.add(list);
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
