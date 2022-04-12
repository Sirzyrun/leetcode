//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["flower","flow","flight"]
//输出："fl"
// 
//
// 示例 2： 
//
// 
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 200 
// 0 <= strs[i].length <= 200 
// strs[i] 仅由小写英文字母组成 
// 
// Related Topics 字符串 👍 2163 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestCommonPrefix(String[] strs) {
        int n = strs.length; // 数组长度
        int minlen = Integer.MAX_VALUE; // 字符串最短长度
        for (String str : strs) {
            minlen = Math.min(minlen, str.length()); // 找出最短长度
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < minlen; i++) {
            char cur = strs[0].charAt(i);
            for (int j = 1; j < n; j++) {
                if (strs[j].charAt(i) != cur) {
                    return sb.toString();
                }
            }
            sb.append(cur);
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
