//给你两个字符串 a 和 b，请返回 这两个字符串中 最长的特殊序列 。如果不存在，则返回 -1 。 
//
// 「最长特殊序列」 定义如下：该序列为 某字符串独有的最长子序列（即不能是其他字符串的子序列） 。 
//
// 字符串 s 的子序列是在从 s 中删除任意数量的字符后可以获得的字符串。 
//
// 
// 例如，"abc" 是 "aebdc" 的子序列，因为删除 "aebdc" 中斜体加粗的字符可以得到 "abc" 。 "aebdc" 的子序列还包括 
//"aebdc" 、 "aeb" 和 "" (空字符串)。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入: a = "aba", b = "cdc"
//输出: 3
//解释: 最长特殊序列可为 "aba" (或 "cdc")，两者均为自身的子序列且不是对方的子序列。 
//
// 示例 2： 
//
// 
//输入：a = "aaa", b = "bbb"
//输出：3
//解释: 最长特殊序列是 "aaa" 和 "bbb" 。
// 
//
// 示例 3： 
//
// 
//输入：a = "aaa", b = "aaa"
//输出：-1
//解释: 字符串 a 的每个子序列也是字符串 b 的每个子序列。同样，字符串 b 的每个子序列也是字符串 a 的子序列。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= a.length, b.length <= 100 
// a 和 b 由小写英文字母组成 
// 
// Related Topics 字符串 👍 151 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 设计一个Set<String> 将a b 所有的子序列加入
    // 先加入a 的，b 的加入过程发线已经存在 就删除
    // 遍历Set 找最大长度
    public int findLUSlength(String a, String b) {
        if (a.equals(b)) {
            return -1;
        }
        Set<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        // 通过dfs 注入a的所有字串
        dfs(a, 0, a.length(), set, sb); // 从0开始  n为字符串长度
        dfs(b, 0, b.length(), set, sb2); // 从0开始  n为字符串长度
        return findMaxSubLen(set);
    }

    public void dfs(String s, int i, int n, Set<String> set, StringBuilder sb) {
        // 1 结束条件
        if (i == n) {
            return;
        }
        // 2 本层逻辑
        char c = s.charAt(i);
        sb.append(c);
        if (set.contains(sb.toString())) {
            set.remove(sb.toString());
        } else {
            set.add(sb.toString());
        }
        // 3 下一层dfs
        dfs(s, i + 1, n, set, sb);
        // 撤销 本层逻辑操作
        sb.deleteCharAt(i);
    }

    public int findMaxSubLen(Set<String> set) {
        int max = 0;
        for (String s : set) {
            max = Math.max(max, s.length());
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
