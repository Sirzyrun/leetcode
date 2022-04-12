//请实现一个函数，把字符串 s 中的每个空格替换成"%20"。 
//
// 
//
// 示例 1： 
//
// 输入：s = "We are happy."
//输出："We%20are%20happy." 
//
// 
//
// 限制： 
//
// 0 <= s 的长度 <= 10000 
// Related Topics 字符串 👍 218 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String replaceSpace(String s) {
        int n = s.length();  // 0->n-1
        int l = 0, r = 0;
        StringBuilder sb = new StringBuilder();
        while (r < n) {
            if (r == n - 1 && !Character.isSpace(s.charAt(r))) {
                sb.append(s.substring(l, n));
                break;
            }
            if (!Character.isSpace(s.charAt(r))) {
                r++;
                continue;
            } else {
                if (l != r) {
                    sb.append(s.substring(l, r));
                    sb.append("%20");
                    r++;
                    l = r;
                } else {  // l = r
                    sb.append("%20");
                    r++;
                    l = r;
                }
            }
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
