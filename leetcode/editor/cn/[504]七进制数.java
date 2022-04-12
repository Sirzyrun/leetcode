//给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。 
//
// 
//
// 示例 1: 
//
// 
//输入: num = 100
//输出: "202"
// 
//
// 示例 2: 
//
// 
//输入: num = -7
//输出: "-10"
// 
//
// 
//
// 提示： 
//
// 
// -10⁷ <= num <= 10⁷ 
// 
// Related Topics 数学 👍 144 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String convertToBase7(int num) {
        StringBuilder ans = new StringBuilder();
        // edge case(特殊情况)
        if (num == 0) {
            return "0";
        }
        // is neg ? 标记
        boolean isneg = num < 0;
        // convertToBase7
        num = Math.abs(num);
        while (num != 0) {
            int res = num % 7;
            ans.append(res);
            num /= 7;
        }
        return (isneg ? "-" : "") + ans.reverse().toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
