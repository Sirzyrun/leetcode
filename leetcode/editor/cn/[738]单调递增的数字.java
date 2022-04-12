//当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。 
//
// 给定一个整数 n ，返回 小于或等于 n 的最大数字，且数字呈 单调递增 。 
//
// 
//
// 示例 1: 
//
// 
//输入: n = 10
//输出: 9
// 
//
// 示例 2: 
//
// 
//输入: n = 1234
//输出: 1234
// 
//
// 示例 3: 
//
// 
//输入: n = 332
//输出: 299
// 
//
// 
//
// 提示: 
//
// 
// 0 <= n <= 10⁹ 
// 
// Related Topics 贪心 数学 👍 243 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int monotoneIncreasingDigits(int n) {
        if (n == 0) {
            return 0;
        }
        char[] chars = String.valueOf(n).toCharArray();
        int start = Integer.MAX_VALUE;
        for (int i = chars.length - 1; i > 0; i--) {
            if (chars[i] < chars[i - 1]) {
                chars[i - 1]--;
                start = i;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (i == 0 && chars[i] == 0) {
                continue;
            }
            if (i >= start) {
                sb.append('9');
            } else {
                sb.append(chars[i]);
            }
        }
        return Integer.parseInt(sb.toString());
    }
}
//leetcode submit region end(Prohibit modification and deletion)
