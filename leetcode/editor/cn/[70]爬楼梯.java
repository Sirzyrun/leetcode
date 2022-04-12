//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 2
//输出：2
//解释：有两种方法可以爬到楼顶。
//1. 1 阶 + 1 阶
//2. 2 阶 
//
// 示例 2： 
//
// 
//输入：n = 3
//输出：3
//解释：有三种方法可以爬到楼顶。
//1. 1 阶 + 1 阶 + 1 阶
//2. 1 阶 + 2 阶
//3. 2 阶 + 1 阶
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 45 
// 
// Related Topics 记忆化搜索 数学 动态规划 👍 2325 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }

//        1 确定dp数组以及下标的含义
//        dp[i]： 爬到第i层楼梯，有dp[i]种方法
        int[] dp = new int[n + 1];
//        2 确定递推公式
//        如果可以推出dp[i]呢？
//        从dp[i]的定义可以看出，dp[i] 可以有两个方向推出来。
//        首先是dp[i - 1]，上i-1层楼梯，有dp[i - 1]种方法，那么再一步跳一个台阶不就是dp[i]了么。
//        还有就是dp[i - 2]，上i-2层楼梯，有dp[i - 2]种方法，那么再一步跳两个台阶不就是dp[i]了么。
//        那么dp[i]就是 dp[i - 1]与dp[i - 2]之和！
//        所以dp[i] = dp[i - 1] + dp[i - 2] 。

//        3 dp数组如何初始化
//        题目中说了n是一个正整数，题目根本就没说n有为0的情况。
//        所以本题其实就不应该讨论dp[0]的初始化！
//        不考虑dp[0]如果初始化，只初始化dp[1] = 1，dp[2] = 2，然后从i = 3开始递推，这样才符合dp[i]的定义。
        dp[1] = 1;
        dp[2] = 2;
//        4 从递推公式dp[i] = dp[i - 1] + dp[i - 2];中可以看出，遍历顺序一定是从前向后遍历的
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
//        举例推导dp数组

    }
}
//leetcode submit region end(Prohibit modification and deletion)
