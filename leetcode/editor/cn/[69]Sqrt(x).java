//给你一个非负整数 x ，计算并返回 x 的 算术平方根 。 
//
// 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。 
//
// 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。 
//
// 
//
// 示例 1： 
//
// 
//输入：x = 4
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：x = 8
//输出：2
//解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= x <= 2³¹ - 1 
// 
// Related Topics 数学 二分查找 👍 817 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int mySqrt(int x) {
        int left = 0,right = x,result = -1;
        while (left <= right) {
            int middle = left + (right - left)/2;
            if((long )middle*middle <= x ){
                result = middle;
                left = middle + 1;
            }else{
                right = middle - 1;
            }
        }
        return  result;
        // 为何不返回 left 或者 right？？
       /* left = right = middle时：
        middle * middle既有可能 >x 也有可能 <= x
            所以最后值 既有可能是 left指向 也有可能是 right指向
                所以用 result 指向记录合理值并更新*/
    }
}
//leetcode submit region end(Prohibit modification and deletion)
