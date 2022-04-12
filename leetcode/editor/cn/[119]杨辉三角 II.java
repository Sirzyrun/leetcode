//给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。 
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
//输入: rowIndex = 3
//输出: [1,3,3,1]
// 
//
// 示例 2: 
//
// 
//输入: rowIndex = 0
//输出: [1]
// 
//
// 示例 3: 
//
// 
//输入: rowIndex = 1
//输出: [1,1]
// 
//
// 
//
// 提示: 
//
// 
// 0 <= rowIndex <= 33 
// 
//
// 
//
// 进阶： 
//
// 你可以优化你的算法到 O(rowIndex) 空间复杂度吗？ 
// Related Topics 数组 动态规划 👍 384 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {   //  滚动数组
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex == 0) {
            return new ArrayList<>(Arrays.asList(1));
        }
        if (rowIndex == 1) {
            return new ArrayList<>(Arrays.asList(1, 1));
        }
        int[] dp = new int[rowIndex+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < rowIndex+1; i++) {  // i = 2 表示第3行
            dp[i] = 1;
            for (int j = i - 1; j > 0; j--) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < rowIndex+1; i++) {
            ans.add(dp[i]);
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
