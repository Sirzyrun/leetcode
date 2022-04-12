//给你两个二进制字符串，返回它们的和（用二进制表示）。 
//
// 输入为 非空 字符串且只包含数字 1 和 0。 
//
// 
//
// 示例 1: 
//
// 输入: a = "11", b = "1"
//输出: "100" 
//
// 示例 2: 
//
// 输入: a = "1010", b = "1011"
//输出: "10101" 
//
// 
//
// 提示： 
//
// 
// 每个字符串仅由字符 '0' 或 '1' 组成。 
// 1 <= a.length, b.length <= 10^4 
// 字符串如果不是 "0" ，就都不含前导零。 
// 
// Related Topics 位运算 数学 字符串 模拟 👍 793 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, carry = 0;
        int curSum = 0;
        int cura, curb;
        while (i >= 0 || j >= 0) {
            if (i < 0) {
                cura = 0;
            } else {
                cura = a.charAt(i) - '0';
            }
            if (j < 0) {
                curb = 0;
            } else {
                curb = b.charAt(j) - '0';
            }
            curSum = carry + cura + curb;
            // 0
            if (curSum == 0) {
                sb.append("0");
                carry = 0;
            } else if (curSum == 1) {  // 1
                sb.append("1");
                carry = 0;
            } else if (curSum == 2) { // 2
                sb.append("0");
                carry = 1;
            } else {        // 3
                sb.append("1");
                carry = 1;
            }
            i--;
            j--;
        }
        if (carry == 1) {
            sb.append("1");
        }
        return sb.reverse().toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
