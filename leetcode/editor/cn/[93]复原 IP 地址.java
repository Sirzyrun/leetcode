//有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。 
//
// 
// 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 
//和 "192.168@1.1" 是 无效 IP 地址。 
// 
//
// 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新
//排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "25525511135"
//输出：["255.255.11.135","255.255.111.35"]
// 
//
// 示例 2： 
//
// 
//输入：s = "0000"
//输出：["0.0.0.0"]
// 
//
// 示例 3： 
//
// 
//输入：s = "101023"
//输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 20 
// s 仅由数字组成 
// 
// Related Topics 字符串 回溯 👍 831 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<String> ans = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    int pointNum = 0;

    public List<String> restoreIpAddresses(String s) {
        backTrack(s, 0);
        return ans;
    }

    private void backTrack(String s, int startIndex) {
        // exit
        if (pointNum == 3) {
            if (valid(s, startIndex, s.length() - 1)) {  //todo
                sb.append(s.substring(startIndex, s.length()));
                ans.add(sb.toString());
            }
            return;
        }

        // curr
        for (int i = startIndex; i < s.length(); i++) {
            if (!valid(s, startIndex, i)) {
                break;
            } else {
                String cur = s.substring(startIndex, i + 1);
                int len = sb.length();
                sb.append(cur).append(".");
                pointNum++;
                backTrack(s, i + 1); //这里要注意valid函数越界情况  如果此时的i=startIndex=s.length-1   那么下一个startIndex=s.length
                //  那么判断valid(s,s.length,s.length -1)  就会出现问题
                // 函数valid截取子串 s.substring(s.length,s.length) 有越界问题  所以valid中要判断start>end 的情况
                pointNum--;
                sb.delete(len, sb.length());
            }

        }
    }

    private boolean valid(String s , int start, int end) {
        if(start > end){
            return false;
        }
        String str = s.substring(start, end + 1);
        if (str.charAt(0) == '0' && start != end) {
            return false;
        }
        int num = 0;
        for (int i = 0; i < str.length(); i++) {
            num = num * 10 + (str.charAt(i) - '0');
            if (num > 255) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
