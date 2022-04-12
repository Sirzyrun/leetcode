//给你一个仅包含小写英文字母和 '?' 字符的字符串 s，请你将所有的 '?' 转换为若干小写字母，使最终的字符串不包含任何 连续重复 的字符。 
//
// 注意：你 不能 修改非 '?' 字符。 
//
// 题目测试用例保证 除 '?' 字符 之外，不存在连续重复的字符。 
//
// 在完成所有转换（可能无需转换）后返回最终的字符串。如果有多个解决方案，请返回其中任何一个。可以证明，在给定的约束条件下，答案总是存在的。 
//
// 
//
// 示例 1： 
//
// 输入：s = "?zs"
//输出："azs"
//解释：该示例共有 25 种解决方案，从 "azs" 到 "yzs" 都是符合题目要求的。只有 "z" 是无效的修改，因为字符串 "zzs" 中有连续重复的两
//个 'z' 。 
//
// 示例 2： 
//
// 输入：s = "ubv?w"
//输出："ubvaw"
//解释：该示例共有 24 种解决方案，只有替换成 "v" 和 "w" 不符合题目要求。因为 "ubvvw" 和 "ubvww" 都包含连续重复的字符。
// 
//
// 示例 3： 
//
// 输入：s = "j?qg??b"
//输出："jaqgacb"
// 
//
// 示例 4： 
//
// 输入：s = "??yw?ipkj?"
//输出："acywaipkja"
// 
//
// 
//
// 提示： 
//
// 
// 
// 1 <= s.length <= 100 
// 
// 
// s 仅包含小写英文字母和 '?' 字符 
// 
// 
// Related Topics 字符串 👍 55 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String modifyString(String s) {
        // 只需返回其中一个答案
        // 根据题意进行模拟，尝试对每个 s[i] 进行替换，能够替换的前提是 s[i] 为 ?
        // 且替换字符与前后字符（若存在）不同，由于只需要确保与前后字符不同，因此必然最多在 3 个字符内找到可替换的值
        char[] cs = s.toCharArray();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            for (int c = 0; c < 3 && cs[i] == '?'; c++) {
                boolean ojbk = true;
                if (i-1 >= 0 && cs[i-1] == 'a' + c){
                    ojbk = false;
                }
                if (i+1 < n && cs[i+1] == 'a' + c){
                    ojbk = false;
                }
                if(ojbk){
                    cs[i] = (char)('a' + c);
                }
            }
        }
        return String.valueOf(cs);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
