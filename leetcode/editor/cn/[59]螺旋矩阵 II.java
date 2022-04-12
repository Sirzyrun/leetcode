//给你一个正整数 n ，生成一个包含 1 到 n² 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：[[1,2,3],[8,9,4],[7,6,5]]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 20 
// 
// Related Topics 数组 矩阵 模拟 👍 585 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int loop = n / 2;// 定义循环多少次  一次转一圈
        int startX = 0; //其实位置
        int startY = 0;
        int count = 1; //填充数字
        int offset = 1; //定义偏移量 第一次循环偏移n-1 第二次为n-3 第三次为n-5
        while (loop > 0) {  //n奇数还是偶数循环次数一样 为奇数时最中间位置会模拟遗漏需最后判断
            int i = startX;
            int j = startY;
            // 模拟开始 采取左闭右开方式进行模拟
            // 模拟上侧 从左到右
            for (; j < startY + n - offset; j++) {
                result[i][j] = count++;
            } //此时j已经处在此次循环 上边的最右侧位置
            // 模拟右侧 从上到下
            for (; i < startX + n - offset; i++) {
                result[i][j] = count++;
            } //此时 i、j处在此次循环 最右侧和最下侧位置
            // 模拟下侧 从右到左 （i不必动）
            for (; j > startY; j--) {
                result[i][j] = count++;
            }
            // 模拟左侧 从下到上 （j不必动）
            for (; i > startX; i--) {
                result[i][j] = count++;
            }
            loop--;
            startX++;
            startY++;
            offset += 2;
        }
        if (n % 2 == 1) { // 中心坐标为 n/2 + 1 -1 (数组坐标从0开始)
            result[n / 2][n / 2] = count;
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
