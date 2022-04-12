//给你一个字符串 text，你需要使用 text 中的字母来拼凑尽可能多的单词 "balloon"（气球）。 
//
// 字符串 text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词 "balloon"。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：text = "nlaebolko"
//输出：1
// 
//
// 示例 2： 
//
// 
//
// 输入：text = "loonbalxballpoon"
//输出：2
// 
//
// 示例 3： 
//
// 输入：text = "leetcode"
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= text.length <= 10^4 
// text 全部由小写英文字母组成 
// 
// Related Topics 哈希表 字符串 计数 👍 66 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxNumberOfBalloons(String text) {
        int count = 0;
        int[] voc = new int[] {0,0,0,0,0}; // voc[0] 表示a的个数  voc[1]  b
        // voc[2] l  voc[3] n     voc[4] o
        int n = text.length();
        for (int i = 0; i < n; i++) {
            char cur = text.charAt(i);
            if (cur == 'a') {
                voc[0]++;
            }
            if (cur == 'b') {
                voc[1]++;
            }
            if (cur == 'l') {
                voc[2]++;
            }
            if (cur == 'n') {
                voc[3]++;
            }
            if (cur == 'o') {
                voc[4]++;
            }
        }
        while (voc[0] > 0 && voc[1] > 0 && voc[2] > 1 && voc[3] > 0 && voc[4] > 1) {
            count++;
            voc[0]--;
            voc[1]--;
            voc[2] -= 2;
            voc[3]--;
            voc[4] -= 2;
        }
        return count;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
